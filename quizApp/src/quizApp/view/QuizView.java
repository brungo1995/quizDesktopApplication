package quizApp.view;

import java.util.*;

import javax.swing.JFrame;

import quizApp.controller.QuizController;
import quizApp.model.*;
import utils.UiUtils;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JRadioButton;


public class QuizView extends JFrame {
	private final QuizController controller = new QuizController(this); 
	
	private Subject subject;
	private List<Question> questions; 
	private List <Answer> answers;
	private List <Answer> userSelectedAnswers;
	
	final private String WELCOME_STRING = "Welcome to the subject: ";
	final private String HOMEPAGE_STRING = "Home page";
	
	private JPanel quiztitlePanel = new JPanel();;
	private JLabel welcomeLabel = new JLabel(WELCOME_STRING);;
	private JLabel subjectLabel; 
	
	private JPanel quizHomepagePanel = new JPanel();
	private JButton homepageBtn = new JButton(HOMEPAGE_STRING);
	
	private JPanel quizQuestionPanel = new JPanel();
	private JLabel questionNumberLabel = new JLabel("Question 1. ");
	private JLabel questionDescriptionLabel = new JLabel("Dummy question Dummy question ? ");
	private int currentQuestion = 0;
	
	private final JRadioButton answerRadioBtn1 = new JRadioButton("New radio button");
	private final JRadioButton answerRadioBtn2 = new JRadioButton("New radio button");
	private final JRadioButton answerRadioBtn3 = new JRadioButton("New radio button");
	private final JRadioButton answerRadioBtn4 = new JRadioButton("New radio button");
	
	public QuizView(Subject subject) {
		this.subject = subject;
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
		
        setQuizTitle(subject.getSubjectName());
        setHomepageUi();
        setQuestionUi();
        
		fetchQuiz(this.subject.getSubjectCode());
		
		UiUtils.closeWindow(this);
		setVisible(true);
		
     }
	
	private void fetchQuiz(Integer subjectCode) {
		if (controller != null &&  subjectCode != null) {
			controller.fetchQuiz(subject.getSubjectCode());
		}
	}
	
	public void displayQuiz(Quiz quiz) {
		questions = quiz.getQuestions();
		answers = quiz.getAnswers();
		
		if (!questions.isEmpty() && !answers.isEmpty()) {
			int questionNumber = currentQuestion + 1;
			questionNumberLabel.setText("Question " + questionNumber + ".");
			questionDescriptionLabel.setText(questions.get(currentQuestion).getDescription());
			
			answerRadioBtn1.setText(answers.get(currentQuestion).getDescription());
		}
		
		
	}
	
	public void displayErrorMsg() {
		System.out.println("Error while fetching data from db");
	}
	
	private void setQuizTitle (String subjectName) {
	    quiztitlePanel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
	    getContentPane().add(quiztitlePanel, BorderLayout.NORTH);
		
		welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		quiztitlePanel.add(welcomeLabel);
		
		subjectLabel = new JLabel(subjectName);
		quiztitlePanel.add(subjectLabel);
	}
	
	private void setHomepageUi() {
		quizHomepagePanel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		getContentPane().add(quizHomepagePanel, BorderLayout.SOUTH);
		quizHomepagePanel.add(homepageBtn);
	}
	
	private void setQuestionUi() {
		quizQuestionPanel.setBackground(UIManager.getColor("Button.select"));
		getContentPane().add(quizQuestionPanel, BorderLayout.CENTER);
		quizQuestionPanel.setLayout(null);
		
		setUpQuestionDescriptUi();
		setUpRadioBtn();  
	}
	
	private void setUpQuestionDescriptUi() {
		questionNumberLabel.setBounds(90, 8, 77, 16);
		questionNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		questionNumberLabel.setVerticalAlignment(SwingConstants.TOP);
		quizQuestionPanel.add(questionNumberLabel);
		
		questionDescriptionLabel.setBounds(170, 8, 450, 16);
		quizQuestionPanel.add(questionDescriptionLabel);
	}
	
	private void setUpRadioBtn() {
		answerRadioBtn1.setBounds(90, 50, 500, 23);
		quizQuestionPanel.add(answerRadioBtn1);
		
		answerRadioBtn2.setBounds(90, 100, 141, 23);
		quizQuestionPanel.add(answerRadioBtn2);
		
	    answerRadioBtn3.setBounds(90, 150, 141, 23);
		quizQuestionPanel.add(answerRadioBtn3);
		
		answerRadioBtn4.setBounds(90, 200, 141, 23);
		quizQuestionPanel.add(answerRadioBtn4);
		
		
	}
}
