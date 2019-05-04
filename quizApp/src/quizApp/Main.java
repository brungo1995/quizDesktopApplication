package quizApp;

import database.Controller;
import database.DatabaseHandler;
import quizApp.model.Subject;
import quizApp.view.QuizView;
import quizApp.view.WelcomeView;


public class Main {

	public static void main(String[] args) {
		Controller.handler = DatabaseHandler.getInstance();
		new WelcomeView();
		
//		Subject dummySubject = new Subject("Database Systems", 1);
//		new QuizView(dummySubject);
		
	}

}
