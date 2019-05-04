package quizApp.view;

import java.util.*;

import javax.swing.JFrame;

import quizApp.controller.QuizController;
import quizApp.model.*;
import utils.UiUtils;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
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
	private List <String> userSelectedAnswers =  new ArrayList<String>();
	
	final private String WELCOME_STRING = "Welcome to the subject: ";
	final private String HOMEPAGE_STRING = "Home page";
	final private String NEXT_STRING = "Next";
	
	private JPanel quiztitlePanel = new JPanel();;
	private JLabel welcomeLabel = new JLabel(WELCOME_STRING);;
	private JLabel subjectLabel; 
	
	private JPanel quizHomepagePanel = new JPanel();
	private JButton homepageBtn = new JButton(HOMEPAGE_STRING);
	private JButton nextBtn = new JButton(NEXT_STRING);
	
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
	
	public void setUpQuiz(Quiz quiz) {
		questions = quiz.getQuestions();
		answers = quiz.getAnswers();
		Map <Question, List<Answer>> mapOfQuestionAnswers = mapQuestionAndAnswers (questions, answers);
		
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
					  System.out.println("gotoScoreScreen() method called. userAnswers:  " + userSelectedAnswers);
					  //gotoScoreScreen();
				  }
			   } 
		});
	}
	
	private Map<Question, List<Answer>> mapQuestionAndAnswers(List<Question> questions, List<Answer> answers) {
		Map <Question, List<Answer>> map = new HashMap<Question, List<Answer>>();
		
		for (Question question : questions) {
			List<Answer> questionAnswers =  new ArrayList<>();
			for (Answer answer : answers) {
				if (answer.getQuestionCode() == question.getQuestionCode()) {
					questionAnswers.add(answer);
				}
				map.put(question, questionAnswers);
			}
		 }
		
		return map;
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
		
		nextBtn.setBounds(258, 256, 117, 29);
		quizQuestionPanel.add(nextBtn);
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
	
	 private void fillUserAnswers(String answerSelected) {
		 if (answerSelected != null) {
			  userSelectedAnswers.add(answerSelected);
		  } else  {
			  userSelectedAnswers.add("");
		  }
	 }
}
