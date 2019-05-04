package quizApp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import quizApp.controller.UserController;
import quizApp.model.User;
import utils.StringUtils;
import utils.UiUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserView extends JFrame{
	private final UserController controller = new UserController(this);
	private JTextField username;
	private JTextField studentNumber;
	private User user = null;
	public UserView(){
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
		initUserView();
		setVisible(true);
	}
	
	
	public void initUserView() {
		getContentPane().setBackground(new Color(255, 0, 102));
		setBounds(100, 100, 703, 467);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(0, 0, 800, 41);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(0, 387, 800, 41);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(58, 133, 516, 182);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		username = new JTextField();
		username.setBounds(147, 34, 204, 27);
		panel_2.add(username);
		username.setColumns(10);
		
		studentNumber = new JTextField();
		studentNumber.setColumns(10);
		studentNumber.setBounds(147, 91, 204, 27);
		panel_2.add(studentNumber);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 34, 99, 27);
		panel_2.add(lblNewLabel);
		
		JLabel lblStudentNumber = new JLabel("Student number");
		lblStudentNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentNumber.setBounds(10, 91, 127, 27);
		panel_2.add(lblStudentNumber);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user = validateUserNameAndStudentNumber();
				if(user != null){
					controller.fectUser(user);
					dispose();
					new SubjectView(user);
					
				}else{
					//display error to enter again user name and std num
					displayErrorMsg();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(263, 326, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblUserDetails = new JLabel("User details");
		lblUserDetails.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblUserDetails.setBounds(227, 52, 162, 32);
		getContentPane().add(lblUserDetails);
		UiUtils.closeWindow(this);
	}
	
	private User validateUserNameAndStudentNumber(){
		User user = null;
		int stdNum = 0;
		if(username.getText() != null && studentNumber.getText() != null && studentNumber.getText().length() < 10){
			if(!username.getText().isEmpty()){
				try{
					stdNum = Integer.parseInt(studentNumber.getText().toString());
					if(stdNum != 0 ){
						user = new User(stdNum, username.getText(), 0);
						
					}
				}catch (NumberFormatException ex) {
				}
			}
		}
		return user;
	}
	
	public void displayErrorMsg() {
		JOptionPane.showMessageDialog(this, StringUtils.USER_ERROR);
	}
	
}
