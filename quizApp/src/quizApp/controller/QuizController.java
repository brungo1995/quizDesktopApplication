package quizApp.controller;

import java.util.List;

import database.DatabaseHandler;
import quizApp.model.*;
import quizApp.view.QuizView;

public class QuizController {
	private QuizView view;
	
	public QuizController (QuizView view) {
		this.view = view;
     }
	
	public void fetchQuiz(Integer subjectCode) {
		Quiz quiz = DatabaseHandler.getInstance().getQuiz(subjectCode);
		
		if (quiz != null) {
			view.displayQuiz(quiz);
		} else {
			view.displayErrorMsg();
		}
	}
	
}
