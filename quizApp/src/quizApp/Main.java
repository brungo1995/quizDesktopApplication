package quizApp;

import database.Controller;
import database.DatabaseHandler;
import quizApp.model.Subject;
import quizApp.model.User;
import quizApp.view.QuizView;

public class Main {

	public static void main(String[] args) {
		Controller.handler = DatabaseHandler.getInstance();
		
		Subject dummySubject = new Subject("Database Systems", 2);
		User dummyUser = new User(216012678, "dummy user", 0);
		new QuizView(dummySubject, dummyUser);
	}
}
