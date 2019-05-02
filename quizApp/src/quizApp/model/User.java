package quizApp.model;

public class User {
	private int userId;
	private String name;
	private int score;
	
	public User(int userId, String name, int score) {
		this.userId = userId;
		this.name = name;
		this.score = score;
	}
	
	public int getUserId () {
		return userId;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore () {
		return score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
}
