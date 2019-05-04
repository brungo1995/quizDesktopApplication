package quizApp.controller;

import java.util.ArrayList;


import database.Controller;
import database.DatabaseHandler;
import quizApp.view.*;
import quizApp.model.*;

public class SubjectController {
	private SubjectView view;
	public DatabaseHandler handler;
	ArrayList<Subject> subjects = null;
	public Controller dbController = new Controller();

//	public SubjectController (SubjectView view) {
//		this.view = view;
//     }

	public ArrayList<Subject> fetchSubjects() {
		subjects = dbController.getSubjects();
		return subjects;
	
	}

}
