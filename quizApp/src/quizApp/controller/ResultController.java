package quizApp.controller;

import java.util.List;
import java.util.Map;

import quizApp.model.*;
import quizApp.view.*;

public class ResultController {
	private ResultView view;
	
	public ResultController(ResultView view) {
		this.view = view;
	}
	
	public void calculateScore(List<String> userSelection, List<Question> questions, Map<Question, List<Answer>>  mapOfQuestionAnswers) {
	    Integer score = 0;
		
		for (int position = 0; position < questions.size(); ++position) {
			Question question = questions.get(position);
			List<Answer> questionAnswer = mapOfQuestionAnswers.get(question);
			
			for (Answer answer : questionAnswer) {
				if (answer.getAnswerCode() == question.getAnswerCode()) {
					if (answer.getDescription() == userSelection.get(position)) {
						score += 1;
					}
				}
			}
		}
		
		view.displayResult(score);
	}
	
	public void updateUserScore(User user, Integer score) {
		if (user != null && score != null) {
			
		}
	}
}
