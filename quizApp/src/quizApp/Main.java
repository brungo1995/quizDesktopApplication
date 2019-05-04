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
	}
}
