package database;

import java.util.ArrayList;

import quizApp.model.Answer;
import quizApp.model.Question;
import quizApp.model.Subject;
import quizApp.model.User;

public class Controller {
	public static DatabaseHandler handler;
	
//	User(int userId, String name, int score)
	public User addUser(User user){
		String query = "insert into users(userId, name, score) values(?,?,?)";
		if(handler.insertUser(query, user)){
			System.out.println("ïnserted new user");
			return getUser(user.getUserId());
		}else{
			return null;
		}
	}
	
//	User(int userId, String name, int score)
	public User getUser(int userId){
		String query = "select * from users where userId = "+userId;
		return handler.getUser(query);
	}
	
	

//	User(int userId, String name, int score)
	public boolean updateScore(User user){
		String query =" update users set "
						+ " score = ? "
						+ " where userId = ?";
		if(handler.updateScore(query, user)){
			return true;
		}else{
			return false;
		}
		
	}
	
//	public Subject(String subjectName, int subjectCode)
	public ArrayList<Subject> getSubjects(){
		ArrayList<Subject> subjects = null;
		String query = "select * from subject ";
		subjects = handler.getSubjects(query);
		if(subjects != null){
			return subjects;
		}else{
			return null;
		}
	}
	
//	Question(String description, int answerCode, int subjectCode, int questionCode)
	public ArrayList<Question> getQuestions(Subject subject){
		ArrayList<Question> questions = null;
		String query = "select * from question where subjectCode= "+subject.getSubjectCode();
		questions = handler.getQuestions(query);
		if(questions != null){
			return questions;
		}else{
			return null;
		}
	}
	
//	Answer (String description, int answerCode, int subjectCode, int questionCode)
	public ArrayList<Answer> getAnswers(Subject subject){
		ArrayList<Answer> answers = null;
		String query = "select * from answer where subjectCode= "+subject.getSubjectCode();
		answers = handler.getAnswers(query);
		if(answers != null){
			return answers;
		}else{
			return null;
		}
	}

}
