package database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private boolean  isInserted = false;
	
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

	//	User(int userId, String name, int score)
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
			//table does not exist
			statement.execute();
			System.out.println(TABLE_NAME + " created");
			
		}else{
			System.out.println(TABLE_NAME + " exist already");
		}
	} catch (Exception e) {
		System.out.println(e);
		
	}
}
	
//	public Answer (String description, int answerCode, int subjectCode, int questionCode)
	private void createTableAnswer() {
	String TABLE_NAME = "answer";
	
	try {                                     
		statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
				+ " description varchar(30),\n"
				+ " answerCode int,\n"
				+ " questionCode int,\n"
				+ " subjectCode int,\n"
				+ " PRIMARY KEY (answerCode)"
				+ " )"
				);
		
		DatabaseMetaData dbm = connect.getMetaData();
		ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
		
		if(!tables.next()){
			//table does not exist
			statement.execute();
			System.out.println(TABLE_NAME + " created");
			populateAnswer();
		}else{
			System.out.println(TABLE_NAME + " exist already");
		}
	} catch (Exception e) {
		System.out.println(e);
		
	}
}
	
//	Question(String description, int answerCode, int subjectCode, int questionCode)
	private void createTableQuestion() {
		String TABLE_NAME = "question";
		
		try {                                     
			statement = connect.prepareStatement("CREATE TABLE "+ TABLE_NAME + "("
					+ " description varchar(30),\n"
					+ " answerCode int,\n"
					+ " questionCode int,\n"
					+ " subjectCode int,\n"
					+ " PRIMARY KEY (questionCode)"
					+ " )"
					);
			
			DatabaseMetaData dbm = connect.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(!tables.next()){
				//table does not exist
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
	
//	public Subject(String subjectName, int subjectCode)
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
				//table does not exist
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
	
//	User(int userId, String name, int score)
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
	
//	public Answer (String description, int answerCode, int subjectCode, int questionCode)
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
	
//	Question(String description, int answerCode, int subjectCode, int questionCode)
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
	
//	public Subject(String subjectName, int subjectCode)
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

//	User(int userId, String name, int score)
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

//	Question(String description, int answerCode, int subjectCode, int questionCode)
	public boolean insertQuestion(String query, ArrayList<Question> questions){
		
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
				isInserted = false;
			}
		});
		
		if(!isInserted){
			return false;
		}else{
			return true;
		}
		
	}

//	public Answer (String description, int answerCode, int subjectCode, int questionCode)
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
				isInserted = false;
			}
		});
		
		if(!isInserted){
			return false;
		}else{
			return true;
		}
		
	}

//	public Subject(String subjectName, int subjectCode)
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
		
		if(!isInserted){
			return false;
		}else{
			return true;
		}
		
	}

//	private void populateTables(){
//		populateSubject();
//		populateQuestion();
//		populateAnswer();
//	}
	
	private void populateSubject(){
		
	}

	private void populateQuestion(){
	
	}
	
	private void populateAnswer(){
		
	}
	
	
}