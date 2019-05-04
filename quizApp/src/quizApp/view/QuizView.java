package quizApp.view;

import java.util.*;

import javax.swing.JFrame;

import quizApp.controller.QuizController;
import quizApp.model.*;
import utils.QuizUtils;
import utils.StringUtils;
import utils.UiUtils;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JRadioButton;


public class QuizView extends JFrame {
	private final QuizController controller = new QuizController(this); 
	
	private Subject subject;
	private User user;
	private List<Question> questions; 
	private List <Answer> answers;
	private List <String> userSelection =  new ArrayList<String>();

	private JPanel quiztitlePanel = new JPanel();
	private JLabel welcomeLabel = new JLabel(StringUtils.WELCOME_STRING);;
	private JLabel subjectLabel; 
	
	private JPanel quizHomepagePanel = new JPanel();
	private JButton homepageBtn = new JButton(StringUtils.HOMEPAGE_STRING);
	private JButton nextBtn = new JButton(StringUtils.NEXT_STRING);
	
	private JPanel quizQuestionPanel = new JPanel();
	private JLabel questionNumberLabel = new JLabel();
	private JLabel questionDescriptionLabel = new JLabel();
	private int currentQuestion = 0;
	
	private final JRadioButton answerRadioBtn1 = new JRadioButton();
	private final JRadioButton answerRadioBtn2 = new JRadioButton();
	private final JRadioButton answerRadioBtn3 = new JRadioButton();
	private final JRadioButton answerRadioBtn4 = new JRadioButton();
	private final List<JRadioButton> radioBtns =  Arrays.asList(answerRadioBtn1, answerRadioBtn2, answerRadioBtn3, answerRadioBtn4);
	private final ButtonGroup radioGroup = new ButtonGroup();
	
	public QuizView(Subject subject, User user) {
		this.subject = subject;
		this.user = user;
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
		
        setQuizTitle(subject.getSubjectName());
        configHomepagePanel();
        configQuestionPanel();
        
		fetchQuiz(this.subject.getSubjectCode());
		
		UiUtils.closeWindow(this);
		setVisible(true);
		
    }
	
	private void fetchQuiz(Integer subjectCode) {
		if (controller != null &&  subjectCode != null) {
			controller.fetchQuiz(subject.getSubjectCode());
		}
	}
	
	public void initQuiz(Quiz quiz) {
		questions = quiz.getQuestions();
		answers = quiz.getAnswers();
		Map <Question, List<Answer>> mapOfQuestionAnswers = QuizUtils.mapQuestionAndAnswers (questions, answers);
		
		if (!questions.isEmpty() && !answers.isEmpty()) {
			displayQuestion(questions.get(currentQuestion), mapOfQuestionAnswers, currentQuestion);
		}
		
		nextBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  fillUserAnswers(UiUtils.getSelectedButtonText(radioGroup));
				  ++currentQuestion;
				  
				  if (currentQuestion <= 4) {
					  radioGroup.clearSelection();
					  displayQuestion(questions.get(currentQuestion), mapOfQuestionAnswers, currentQuestion);
				  } else {
					  gotoResultScreen(userSelection, questions, mapOfQuestionAnswers);
				  }
			   } 
		});
	}
	
	private void displayQuestion(Question question, Map <Question, List<Answer>> mapOfQuestionAnswers, int position ) {
		if (question != null) {
			int questionNumber = currentQuestion + 1;
			questionNumberLabel.setText("Question " + questionNumber + ".");
			questionDescriptionLabel.setText(questions.get(currentQuestion).getDescription());
			
			List<Answer> questionAnswers = mapOfQuestionAnswers.get(question);
			fillRadioBtnWithAnswers(questionAnswers);
		}
	}
	
	private void fillRadioBtnWithAnswers(List<Answer> answers) {
		if (answers != null && !answers.isEmpty()) {
			for (int position = 0; position < answers.size(); ++position) {
				radioBtns.get(position).setText(answers.get(position).getDescription());
			}
		}
	}
	
	private void fillUserAnswers(String answerSelected) {
		 if (answerSelected != null) {
			  userSelection.add(answerSelected);
		  } else  {
			  userSelection.add("");
		  }
	 }
	
	public void displayErrorMsg() {
		JOptionPane.showMessageDialog(this, StringUtils.ERROR_STRING);
	}
	
	private void setQuizTitle (String subjectName) {
	    quiztitlePanel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
	    getContentPane().add(quiztitlePanel, BorderLayout.NORTH);
		
		welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		quiztitlePanel.add(welcomeLabel);
		
		subjectLabel = new JLabel(subjectName);
		quiztitlePanel.add(subjectLabel);
	}
	
	private void configHomepagePanel() {
		quizHomepagePanel.setBackground(UIManager.getColor("InternalFrame.borderLight"));
		getContentPane().add(quizHomepagePanel, BorderLayout.SOUTH);
		quizHomepagePanel.add(homepageBtn);
		onHomeBtnClick();
	}
	
	private void configQuestionPanel() {
		quizQuestionPanel.setBackground(UIManager.getColor("Button.select"));
		getContentPane().add(quizQuestionPanel, BorderLayout.CENTER);
		quizQuestionPanel.setLayout(null);
		
		configQuestionDescriptUi();
		configRadioBtn();  
		
		nextBtn.setBounds(258, 256, 117, 29);
		quizQuestionPanel.add(nextBtn);
	}
	
	private void configQuestionDescriptUi() {
		questionNumberLabel.setBounds(90, 8, 77, 16);
		questionNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		questionNumberLabel.setVerticalAlignment(SwingConstants.TOP);
		quizQuestionPanel.add(questionNumberLabel);
		
		questionDescriptionLabel.setBounds(170, 8, 450, 16);
		quizQuestionPanel.add(questionDescriptionLabel);
	}
	
	private void configRadioBtn() {
		answerRadioBtn1.setBounds(90, 50, 500, 23);
		quizQuestionPanel.add(answerRadioBtn1);
		
		answerRadioBtn2.setBounds(90, 100, 500, 23);
		quizQuestionPanel.add(answerRadioBtn2);
		
	    answerRadioBtn3.setBounds(90, 150, 500, 23);
		quizQuestionPanel.add(answerRadioBtn3);
		
		answerRadioBtn4.setBounds(90, 200, 500, 23);
		quizQuestionPanel.add(answerRadioBtn4);
		
		radioGroup.add(answerRadioBtn1);
		radioGroup.add(answerRadioBtn2);
		radioGroup.add(answerRadioBtn3);
		radioGroup.add(answerRadioBtn4);
	 }
	
	private void gotoResultScreen(List<String> userSelection, List<Question> questions, Map<Question, List<Answer>> mapOfQuestionAnswers ) {
		 this.dispose();
		 new ResultView (user, subject, userSelection, questions, mapOfQuestionAnswers);
	}
	
	private void onHomeBtnClick() {
		homepageBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) { 
				 dispose();
				 UiUtils.gotoMenuScreen();
			 }
		});
	}
}
