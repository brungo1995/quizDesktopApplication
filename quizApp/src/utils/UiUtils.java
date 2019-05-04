package utils;

import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;

import quizApp.Main;
import quizApp.model.Subject;
import quizApp.model.User;
import quizApp.view.QuizView;

import static javax.swing.WindowConstants.*;

public class UiUtils {
	private static String exitMsg = "Do you really want to exit?";
	
	private UiUtils() {
		
		
	}
	
	public static void closeWindow(JFrame frame) {
	      frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	      frame.addWindowListener(new WindowAdapter() {
	           public void windowClosing(WindowEvent ev){
	             if (UiUtils.confirmBeforeExit(frame)){
	                   System.exit(0);
	              }
	          }
	     });
	 }
	 
	 public static boolean confirmBeforeExit(JFrame jframe){
	       if (jframe != null) {
	           if (JOptionPane.showConfirmDialog(jframe, exitMsg, "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == 0){
	               return true;
	           }
	        }
	        return false;
	    }
	 
	
	 public static String getSelectedButtonText(ButtonGroup buttonGroup) {
	        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	                return button.getText();
	            }
	        }

	        return null;
	 }
	 
	 public static void gotoMenuScreen() {
		 Subject dummySubject = new Subject("Database Systems", 2);
		 User dummyUser = new User (216012678, "dummy user", 0);
		 new QuizView(dummySubject, dummyUser);
	 }
}
