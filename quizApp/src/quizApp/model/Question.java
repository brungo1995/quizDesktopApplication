package quizApp.model;

public class Question {

	private String description; 
	private int answerCode;
	private int subjectCode; 
	private int questionCode; 
	
	
	public Question(String description, int answerCode, int subjectCode, int questionCode) {
		this.description = description;
		this.answerCode = answerCode; 
		this.subjectCode = subjectCode;
		this.questionCode = questionCode;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	
	public void setAnswerCode(int answerCode) {
		this.answerCode = answerCode;
	}
	
	public int getAnswerCode() {
		return answerCode;
	}
	
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public int getSubjectCode() {
		return subjectCode;
	}
	
	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}
	
	public int getQuestionCode() {
		return questionCode;
	}
}
