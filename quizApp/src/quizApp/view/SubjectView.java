package quizApp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import quizApp.controller.SubjectController;
import quizApp.model.Answer;
import quizApp.model.Subject;
import quizApp.model.User;
import utils.StringUtils;
import utils.UiUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubjectView extends JFrame{
	public User user;
	public ArrayList<Subject> subjects;
	public SubjectController controller =new SubjectController();
	
	private final JRadioButton rdbtnSubject1 = new JRadioButton();
	private final JRadioButton rdbtnSubject2 = new JRadioButton();
	private final JRadioButton rdbtnSubject3 = new JRadioButton();
	
	private final List<JRadioButton> radioBtns =  Arrays.asList(rdbtnSubject1, rdbtnSubject2, rdbtnSubject3);
	private final ButtonGroup radioGroup = new ButtonGroup();
	
	public SubjectView(User user){
		this.user = user;
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
		subjects = fetchSubjects();
		configSubjectPanel();
		fillRadioBtnWithSubjects();
		initSubjectView();
		setVisible(true);
	}
	
	private void configSubjectPanel(){
	
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(171, 121, 378, 211);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		rdbtnSubject1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnSubject1.setBounds(6, 31, 336, 23);
		panel_2.add(rdbtnSubject1);
		
		rdbtnSubject2.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnSubject2.setBounds(6, 72, 336, 23);
		panel_2.add(rdbtnSubject2);
		
		rdbtnSubject3.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnSubject3.setBounds(6, 115, 336, 23);
		panel_2.add(rdbtnSubject3);
		
		radioGroup.add(rdbtnSubject3);
		radioGroup.add(rdbtnSubject1);
		radioGroup.add(rdbtnSubject2);
		
	}
	
	
	public ArrayList<Subject> fetchSubjects(){
		return controller.fetchSubjects();
	}
	
	
	public void fillRadioBtnWithSubjects() {
		System.out.println(radioBtns.get(0).getText() + "text from radio btn ");
		
		if (subjects != null && !subjects.isEmpty()) {
			for (int position = 0; position < subjects.size(); ++position) {
				radioBtns.get(position).setText(subjects.get(position).getSubjectName());
			}
		}
		
		
	}
	
	public void initSubjectView() {
		getContentPane().setBackground(new Color(255, 0, 153));
		setBounds(100, 100, 703, 467);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(0, 0, 733, 43);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(0, 377, 733, 43);
		getContentPane().add(panel_1);
		
		JLabel lblSelectASubject = new JLabel("Select a subject you want to play");
		lblSelectASubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSelectASubject.setBounds(191, 54, 335, 43);
		getContentPane().add(lblSelectASubject);

		JButton btnNewButton = new JButton("Play");
		

		btnNewButton.addActionListener(new ActionListener() {
			Subject subject = null;
			public void actionPerformed(ActionEvent arg0) {
				String subjectName = UiUtils.getSelectedButtonText(radioGroup);
				if(subjectName != null){
					subject = subjects.stream()
				            .filter(x -> x.getSubjectName().equals(subjectName) )
				            .findFirst()
				            .get();
					
					dispose();
					new QuizView(subject, user);
				
				}else{
					displayErrorMsg();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(321, 343, 89, 23);
		getContentPane().add(btnNewButton);
		UiUtils.closeWindow(this);
	}


public void displayErrorMsg() {
	JOptionPane.showMessageDialog(this, StringUtils.USER_SELECT_SUBJECT);
}

}
