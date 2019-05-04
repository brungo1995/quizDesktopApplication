package quizApp.controller;

import database.DatabaseHandler;
import database.Controller;
import quizApp.model.User;
import quizApp.view.UserView;
public class UserController {
	private UserView view;
	public DatabaseHandler handler;
	public Controller dbController =new Controller();
	
	public UserController (UserView view) {
		this.view = view;
     }

	public User fectUser(User user) {
		
		if(dbController.getUser(user.getUserId()) == null){
			System.out.println("user is null ");
			dbController.addUser(user);
		}else{
			System.out.println("user exists allready");
		}
		return user;
	}

}
