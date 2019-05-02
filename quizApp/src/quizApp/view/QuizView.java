package quizApp.view;

import java.util.*;

import quizApp.controller.QuizController;
import quizApp.model.*;


public class QuizView {
	private final QuizController controller = new QuizController(this); 
	
	private Subject subject;
	private List<Question> questions; 
	private List <Answer> answers;
	private List <Answer> userSelectedAnswers;
	
	public QuizView(Subject subject) {
		this.subject = subject;
		fetchQuiz(this.subject.getSubjectCode());
		
     }
	
	private void fetchQuiz(Integer subjectCode) {
		if (controller != null &&  subjectCode != null) {
			controller.fetchQuiz(subject.getSubjectCode());
		}
	}
	
	public void displayQuiz(Quiz quiz) {
		System.out.println("Quiz" + quiz);
		
		questions = quiz.getQuestions();
		answers = quiz.getAnswers();
		
		System.out.println("Quiz" + quiz + " questions : " + questions + " answers : " + answers);
		
	}
	
	public void displayErrorMsg() {
		System.out.println("Error while fetching data from db");
	}
	
}
