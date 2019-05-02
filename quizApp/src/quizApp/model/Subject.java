package quizApp.model;

public class Subject {
	private String subjectName;
	private int subjectCode; 
	
	public Subject(String subjectName, int subjectCode) {
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
    }
	
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public int getSubjectCode() {
		return subjectCode;
	}
}
