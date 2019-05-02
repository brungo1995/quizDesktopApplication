package quizApp.model;

import java.util.List;

public class Quiz {
	
	private List <Question> questions;  
	private List <Answer> answers;
	private int subjectCode; 
	private List <Answer> userSelectedAnswers; 
	
	
	public Quiz(List<Question> questions, List<Answer> answers, int subjectCode) {
		this.questions = questions;
		this.answers = answers;
		this.subjectCode = subjectCode;
    }
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public List<Answer> getAnswers(){
		return answers;
	}
	
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public int getSubjectCode() {
		return subjectCode;
	}
	
}
