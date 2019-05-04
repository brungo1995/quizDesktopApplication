package database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.apache.derby.impl.sql.catalog.SYSROUTINEPERMSRowFactory;
import quizApp.model.*;
import java.util.Properties;
import quizApp.model.Answer;
import quizApp.model.Question;
import quizApp.model.Subject;
import quizApp.model.User;


public class DatabaseHandler {
	private Connection connect = null;
	private PreparedStatement statement = null;
	private static DatabaseHandler handler = null;
	private Properties props;
	private boolean  isInserted;
	
	private DatabaseHandler(){
		settings();
		boolean isConnected = false;
		isConnected = createConnection();
		if(isConnected){
			createTables();			
		}
	}
	
	public static DatabaseHandler getInstance(){
		if(handler == null){
			handler = new DatabaseHandler();
		}
		return handler;
	}

	private void settings() {
		props = new Properties();
		try(FileInputStream file = new FileInputStream("configdb.properties")) {		
			props.load(file);
		
		} catch (Exception e) {

		}		
	}

	private boolean createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			connect = DriverManager.getConnection("jdbc:derby:quizDB;create=true");
			System.out.println("database created 1");
			return true;
		}
		catch (Exception e) {
			System.out.println("couldn't create database");
			return false;
		}
		
	}
	
	private void createTables() {
		createTableUser();
		createTableAnswer();
		createTableQuestion();
		createTablesubject();
	}


	private void createTableUser() {
	String TABLE_NAME = "users";
	
	try {                                     
		statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
				+ " userId int,\n"
				+ " name varchar(30),\n"
				+ " score int,\n"
				+ " PRIMARY KEY (userId)"
				+ " )"
				);
		
		DatabaseMetaData dbm = connect.getMetaData();
		ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
		
		if(!tables.next()){
			statement.execute();
			System.out.println(TABLE_NAME + " created");
			
		}else{
			System.out.println(TABLE_NAME + " exist already");
		}
	} catch (Exception e) {
		System.out.println(e);
		
	}
}
	
	private void createTableAnswer() {
	String TABLE_NAME = "answer";
	
	try {                                     
		statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
				+ " description varchar(200),\n"
				+ " answerCode int,\n"
				+ " questionCode int,\n"
				+ " subjectCode int,\n"
				+ " PRIMARY KEY (answerCode)"
				+ " )"
				);
		
		DatabaseMetaData dbm = connect.getMetaData();
		ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
		
		if(!tables.next()){
			statement.execute();
			System.out.println(TABLE_NAME + " created");
			populateAnswer();
		} else {
			System.out.println(TABLE_NAME + " exist already");
		}
	} catch (Exception e) {
		System.out.println(e);
		
	}
}
	
	private void createTableQuestion() {
		String TABLE_NAME = "question";
		
		try {                                     
			statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
					+ " description varchar(200),\n"
					+ " answerCode int,\n"
					+ " questionCode int,\n"
					+ " subjectCode int,\n"
					+ " PRIMARY KEY (questionCode)"
					+ " )"
					);
			
			DatabaseMetaData dbm = connect.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(!tables.next()){
				statement.execute();
				System.out.println(TABLE_NAME + " created");
				populateQuestion();
				
			}else{
				System.out.println(TABLE_NAME + " exist already");
			}
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	

	private void createTablesubject() {
		String TABLE_NAME = "subject";
		
		try {                                     
			statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
					+ " subjectName varchar(30),\n"
					+ " subjectCode varchar(30),\n"
					+ " PRIMARY KEY (subjectCode)"
					+ " )"
					);
			
			DatabaseMetaData dbm = connect.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(!tables.next()){
				statement.execute();
				System.out.println(TABLE_NAME + " created");
				populateSubject();
			}else{
				System.out.println(TABLE_NAME + " exist already");
			}
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	public User getUser(String query) {
		User user = null;
		try {
			ResultSet result;
			statement =connect.prepareStatement(query);
			result = statement.executeQuery();
			while(result.next()){
				user = new User(
						result.getInt("userId"),
						result.getString("name"),
						result.getInt("score")
						
						);
			}
			
			return user;
		} catch (SQLException e) {
			
			return null;
		}
	}
	
	public Quiz getQuiz(Integer subjectCode) {
		String QUERY_ANSWER = "select * from answer where subjectCode = " + subjectCode;
		String QUERY_QUESTION = "select * from question where subjectCode = " + subjectCode;
		List<Answer> answers = getAnswers(QUERY_ANSWER);
		List <Question> questions = getQuestions(QUERY_QUESTION);
		
		
		if (answers != null && questions != null) {
			return  new Quiz (questions, answers, subjectCode);
		} else  {
			return  null;
		}
	}
	
	public ArrayList<Answer> getAnswers(String query) {
		Answer answer = null;
		ArrayList<Answer> answers = new ArrayList<Answer>();
		try {
			ResultSet result;
			statement =connect.prepareStatement(query);
			result = statement.executeQuery();
			while(result.next()){
				answer = new Answer(
						result.getString("description"),
						result.getInt("answerCode"),
						result.getInt("subjectCode"),
						result.getInt("questionCode")
						);
				answers.add(answer);
			}
			
			return answers;
		} catch (SQLException e) {
			
			return null;
		}
	}
	

	public ArrayList<Question> getQuestions(String query) {
		Question question = null;
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			ResultSet result;
			statement =connect.prepareStatement(query);
			result = statement.executeQuery();
			while(result.next()){
				question = new Question(
						result.getString("description"),
						result.getInt("answerCode"),
						result.getInt("subjectCode"),
						result.getInt("questionCode")
						);
				questions.add(question);
			}
			
			return questions;
		} catch (SQLException e) {
			
			return null;
		}
	}
	

	public ArrayList<Subject> getSubjects(String query) {
		Subject subject = null;
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		try {
			ResultSet result;
			statement =connect.prepareStatement(query);
			result = statement.executeQuery();
			while(result.next()){
				subject = new Subject(
						result.getString("subjectName"),
						result.getInt("subjectCode")
						);
				subjects.add(subject);
			}
			
			return subjects;
		} catch (SQLException e) {
			
			return null;
		}
	}


	public boolean insertUser(String query, User user){
		try {
			statement = connect.prepareStatement(query);
			statement.setString(1, user.getName());
			statement.setFloat(2, user.getScore());
			statement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}


	public boolean insertQuestion(String query, ArrayList<Question> questions){
		
		for(int x = 0; x < questions.size(); x++){
			try {
				statement = connect.prepareStatement(query);
				statement.setString(1, questions.get(x).getDescription());
				statement.setInt(2, questions.get(x).getAnswerCode());
				statement.setInt(3, questions.get(x).getSubjectCode());
				statement.setInt(4, questions.get(x).getQuestionCode());
				statement.execute();
				isInserted = true; 
			} catch (SQLException e) {
				isInserted = false;
			}
		}

		return isInserted;
	}

	public boolean insertAnswer(String query, ArrayList<Answer> questions){
		
		questions.stream().forEach(question ->{
			try {
				statement = connect.prepareStatement(query);
				statement.setString(1, question.getDescription());
				statement.setInt(2, question.getAnswerCode());
				statement.setInt(3, question.getSubjectCode());
				statement.setInt(4, question.getQuestionCode());
				statement.execute();
				isInserted = true; 
			} catch (SQLException e) {
				System.out.println(e);
				isInserted = false;
			}
		});
		
		return isInserted;
		
	}

	public boolean insertSubjecs(String query, ArrayList<Subject> subjects){
		
		subjects.stream().forEach(question ->{
			try {
				statement = connect.prepareStatement(query);
				statement.setString(1, question.getSubjectName());
				statement.setInt(2, question.getSubjectCode());
				statement.execute();
				isInserted = true; 
			} catch (SQLException e) {
				isInserted = false;
			}
		});
		
		return isInserted;
		
	}


     private void populateSubject(){
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		subjects.add(new Subject("Database Systems",1));
		subjects.add(new Subject("Networks",2));
		subjects.add(new Subject("Project Management",3));
		String query = "insert into subject(subjectName, subjectCode) values(?,?)";
		if(insertSubjecs(query, subjects)){
			System.out.println("inserted subjects");
		}else{
			System.out.println("failed to insert subjects");
		}		
	}

	private void populateQuestion(){
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.add(new Question("How many levels are there of data level confidentiality?",2,1,1));		
		questions.add(new Question("Three terms used to define data quality",8,1,2));		
		questions.add(new Question("Functions of the database administrator?",9,1,3));
		questions.add(new Question("Give some examples of ways of inputting raw data",13,1,4));
		questions.add(new Question("Name the DBA�s responsibilities?",20,1,5));
		questions.add(new Question("What is a network?",22,2,6));
		questions.add(new Question("What do you mean by data communication?",28,2,7));
		questions.add(new Question("What do you mean by switching?",29,2,8));
		questions.add(new Question("What does HTTP stand for?",35,2,9));
		questions.add(new Question("What does VPN stand for?",37,2,10));
		questions.add(new Question("What are the 4 stages of team development?",41,3,11));
		questions.add(new Question("What is one of the most important skills a project manager can have?",47,3,12));
		questions.add(new Question("WBS is an excellent and most effective tool that is used for tracking for?",50,3,13));
		questions.add(new Question("What is a project life cycle? ",54,3,14));
		questions.add(new Question("What does a project manager oversee?",57,3,15));

		String query = "insert into question(description, answerCode, subjectCode, questionCode) values(?,?,?,?)";
		if(insertQuestion(query, questions)){
			System.out.println("inserted questions");
		}else{
			System.out.println("failed to insert questions");

		}	
	}
	
	private void populateAnswer(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer("4",1,1,1));
		answers.add(new Answer("3",2,1,1));
		answers.add(new Answer("5",3,1,1));
		answers.add(new Answer("6",4,1,1));
		
		answers.add(new Answer("Mutated, Crafteds",5,1,2));
		answers.add(new Answer("Coding, Abstraction",6,1,2));
		answers.add(new Answer("Drawing, Velocity",7,1,2));
		answers.add(new Answer("Validity, Consistency, Accuracy",8,1,2));
		
		answers.add(new Answer("Database installation, upgrade and patching",9,1,3));
		answers.add(new Answer("Managing developers",10,1,3));
		answers.add(new Answer("Setting up budgets",11,1,3));
		answers.add(new Answer("Developing applications",12,1,3));
			
		answers.add(new Answer("Sensors, User input, Interaction",13,1,4));
		answers.add(new Answer("Screen output",14,1,4));
		answers.add(new Answer("none",15,1,4));
		answers.add(new Answer("streaming video",16,1,4));
		
		answers.add(new Answer("Specialised data handling",17,1,5));
		answers.add(new Answer("Database backup and recovery",18,1,5));
		answers.add(new Answer("Performance monitoring",19,1,5));
		answers.add(new Answer("All of the above",20,1,5));
		
		
		answers.add(new Answer("The apps that let you code",21,2,6));
		answers.add(new Answer("It is a set of devices connected by communication links.",22,2,6));
		answers.add(new Answer("A team of soccer players",23,2,6));
		answers.add(new Answer("Best series on television",24,2,6));
		
		answers.add(new Answer("Processing your data",25,2,7));
		answers.add(new Answer("Calling your friends",26,2,7));
		answers.add(new Answer("Exchanging of cars",27,2,7));
		answers.add(new Answer("It is the exchange of data between two devices via some form of transmission medium such as wire cable",28,2,7));
		
		answers.add(new Answer("It is a method in which communication devices are connected to one another efficiently",29,2,8));
		answers.add(new Answer("Switching on the light at night",30,2,8));
		answers.add(new Answer("Turning of the main switch",31,2,8));
		answers.add(new Answer("Switching lanes on the road",32,2,8));
		
		answers.add(new Answer("Hire Television Trolling Practise",33,2,9));
		answers.add(new Answer("HyperText Markup Language",34,2,9));
		answers.add(new Answer("HyperText Transfer Protocol",35,2,9));
		answers.add(new Answer("HyperText Transmission Product",36,2,9));
		
		answers.add(new Answer("Virtual Private Network",37,2,10));
		answers.add(new Answer("Vantilation Protocol Node",38,2,10));
		answers.add(new Answer("Vacated Pinning Navigation",39,2,10));
		answers.add(new Answer("Virtual Privatized Namespace",40,2,10));
		
		
		answers.add(new Answer("Forming, Storming, Norming, Performing",41,3,11));
		answers.add(new Answer("Enthusiasm, Hope, Panic, Solution",42,3,11));
		answers.add(new Answer("Forming, Solutioning, Normalizing, Communicating",43,3,11));
		answers.add(new Answer("Direction, Motivation, Cooperation, Collaboration",44,3,11));
		
		answers.add(new Answer("Negotiation skills",45,3,12));
		answers.add(new Answer("Influencing skills",46,3,12));
		answers.add(new Answer("Communication skills",47,3,12));
		answers.add(new Answer("Problem Solving skills",48,3,12));
		
		answers.add(new Answer("Project Resources",49,3,13));
		answers.add(new Answer("Project Scope",50,3,13));
		answers.add(new Answer("Project Schedule",51,3,13));
		answers.add(new Answer("Project Risks",52,3,13));
		
		answers.add(new Answer("Repetition of decisions",53,3,14));
		answers.add(new Answer("The logical progress through the several phases",54,3,14));
		answers.add(new Answer("The quality of the manager",55,3,14));
		answers.add(new Answer("The same as a human being",56,3,14));		
		
		answers.add(new Answer("Business processes",57,3,15));
		answers.add(new Answer("The duration of the program",58,3,15));
		answers.add(new Answer("Multiplicity",59,3,15));
		answers.add(new Answer("Cleanliness of the employees",60,3,15));		


		String query = "insert into answer(description, answerCode, subjectCode, questionCode) values(?,?,?,?)";
		if(insertAnswer(query, answers)){
			System.out.println("inserted answers");
		}else{
			System.out.println("failed to insert answers");
		}	
	}
}