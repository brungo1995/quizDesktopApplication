package quizApp.view;

import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import quizApp.controller.*;
import quizApp.model.Answer;
import quizApp.model.Question;
import quizApp.model.Subject;
import quizApp.model.User;
import utils.StringUtils;
import utils.UiUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultView extends JFrame {
	private final ResultController controller = new ResultController(this); 
	
	private User user;
	private Subject subject;
	private List<String> userSelection;
	private List<Question> questions;
	private Map<Question, List<Answer>> mapOfQuestionAnswers;
	
	private JPanel restartPanel = new JPanel();
	private JPanel scorePanel = new JPanel();
	
	private JLabel thankYouText = new JLabel();
	private JLabel scoreText = new JLabel();
	private JButton restartBtn = new JButton(StringUtils.PLAY_AGAIN_STRING);
	private final JButton btnSeeMemo = new JButton(StringUtils.SEE_MEMO_STRING);
	
	public ResultView(User user, Subject subject, List<String> userSelection, List<Question> questions, Map<Question, List<Answer>> mapOfQuestionAnswers ) {
		this.user = user;
		this.subject = subject;
		this.userSelection = userSelection;
		this.questions = questions;
		this.mapOfQuestionAnswers = mapOfQuestionAnswers;
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
		
        configScorePanel();
        configRestartPanel();
        calculateScore();
		
		setVisible(true);
		UiUtils.closeWindow(this);
	}
	
	private void configScorePanel() {
		 thankYouText.setBounds(129, 5, 500, 15);
		 scoreText.setBounds(234, 69, 500, 15);
		 scorePanel.setBackground(UIManager.getColor("Button.select"));
		 scorePanel.setLayout(null);
		 scorePanel.add(thankYouText);
		 scorePanel.add(scoreText);
		 getContentPane().add(scorePanel, BorderLayout.CENTER);
	}
	
	private void configRestartPanel() {
		restartBtn.setBounds(225, 280, 117, 29);
		restartPanel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		restartPanel.add(restartBtn);
		restartPanel.add(btnSeeMemo);
		getContentPane().add(restartPanel, BorderLayout.SOUTH);
		
		restartBtnClick();
	}
	
	public void calculateScore() {
		if (userSelection != null && questions != null) {
			controller.calculateScore(userSelection, questions, mapOfQuestionAnswers);
		}
	}
	
	public void displayResult(Integer score) {
		System.out.println("score " + score);
		
		if (score != null) {
			thankYouText.setText(StringUtils.THANK_YOU_FOR_LEARNING_STRING + " " + subject.getSubjectName()+ ", " + user.getName() + ".");
			scoreText.setText(StringUtils.YOUR_SCORE_STRING + score);
		} else {
			JOptionPane.showMessageDialog(this, StringUtils.ERROR_STRING);
		}
	}
	
	private void restartBtnClick() {
		restartBtn.addActionListener( new ActionListener() {
			 public void actionPerformed(ActionEvent e) { 
				 dispose();
				 UiUtils.gotoMenuScreen();
			 }
		});
	}
	
	public void generateMemo(Map<Question, List<Answer>> mapOfQuestionAnswers) {
		
	}
}
