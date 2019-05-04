package quizApp.controller;



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
			view.initQuiz(quiz);
		} else {
			view.displayErrorMsg();
		}
	}
	
}
