package quizApp;

import database.Controller;
import database.DatabaseHandler;
import quizApp.model.Subject;
import quizApp.model.User;
import quizApp.view.QuizView;
import quizApp.view.WelcomeView;


public class Main {

	public static void main(String[] args) {
		Controller.handler = DatabaseHandler.getInstance();
		new WelcomeView();
		
<<<<<<< HEAD
//		Subject dummySubject = new Subject("Database Systems", 1);
//		new QuizView(dummySubject);
		
=======
		Subject dummySubject = new Subject("Database Systems", 2);
		User dummyUser = new User(216012678, "dummy user", 0);
		new QuizView(dummySubject, dummyUser);
>>>>>>> ea62fa6098857b3f7601f9b0dcb1bf784de5eb55
	}
}
