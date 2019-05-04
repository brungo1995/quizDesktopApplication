package quizApp;

import database.Controller;
import database.DatabaseHandler;
import quizApp.model.Subject;
import quizApp.view.QuizView;

public class Main {

	public static void main(String[] args) {
		Controller.handler = DatabaseHandler.getInstance();
		
		Subject dummySubject = new Subject("Database Systems", 2);
		new QuizView(dummySubject);
		
	}

}
