package quizApp;

import database.DatabaseHandler;
import quizApp.model.Subject;
import quizApp.view.QuizView;

public class Main {

	public static void main(String[] args) {
		DatabaseHandler.getInstance();
		
		Subject dummySubject = new Subject("dummySubject", 0);
		new QuizView(dummySubject);

	}

}
