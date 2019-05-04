package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quizApp.model.Answer;
import quizApp.model.Question;

public class QuizUtils {

	
	private QuizUtils() {
		
		
	}
	
	static public Map<Question, List<Answer>> mapQuestionAndAnswers(List<Question> questions, List<Answer> answers) {
		Map <Question, List<Answer>> map = new HashMap<Question, List<Answer>>();
		
		for (Question question : questions) {
			List<Answer> questionAnswers =  new ArrayList<>();
			for (Answer answer : answers) {
				if (answer.getQuestionCode() == question.getQuestionCode()) {
					questionAnswers.add(answer);
				}
				map.put(question, questionAnswers);
			}
		 }
		return map;
	}
	
	static public List<Answer> retrieCorrectAnswersFromMap(Map<Question, List<Answer>> mapOfQuestionAnswers) {
		ArrayList <Question> questions = new ArrayList<Question>(mapOfQuestionAnswers.keySet());
		List<Answer> correctAnswers =  new ArrayList<>(); 
		
		for (Question question : questions) {
			List<Answer> questionAnswers = mapOfQuestionAnswers.get(question);
			
			for (Answer answer : questionAnswers) {
				
				if (answer.getAnswerCode() == question.getAnswerCode()) {
					correctAnswers.add(answer);
				}
			}
		}
		return  correctAnswers;
	}
}
