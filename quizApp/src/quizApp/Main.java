package quizApp;

import database.Controller;
import database.DatabaseHandler;

public class Main {

	public static void main(String[] args) {
		Controller.handler = DatabaseHandler.getInstance();
	}

}
