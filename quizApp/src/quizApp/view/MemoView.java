package quizApp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import quizApp.model.Answer;
import quizApp.model.Question;
import utils.QuizUtils;
import utils.StringUtils;
import utils.UiUtils;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MemoView extends JFrame {
    private Map<Question, List<Answer>> mapOfQuestionAnswers;
    List<Answer> correctAnswers =  new ArrayList<>(); 
    
    private JTextArea textArea = new JTextArea();
	
	public MemoView(Map<Question, List<Answer>> mapOfQuestionAnswers) {
		this.mapOfQuestionAnswers = mapOfQuestionAnswers;
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width / 3, screenSize.height / 3);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		scrollPane.setViewportView(textArea);
		
		generateMemo();
		textArea.setEditable(false);
		
		setVisible(true);
     }
	
	private void generateMemo() {
		correctAnswers = QuizUtils.retrieCorrectAnswersFromMap(mapOfQuestionAnswers);
		displayMemo(new ArrayList<Question>(mapOfQuestionAnswers.keySet()));
	}
	
	private void displayMemo (ArrayList <Question> questions) {
		if ((correctAnswers != null && !correctAnswers.isEmpty()) && (questions != null && !questions.isEmpty()))
			for (int position = 0; position < questions.size(); ++position) {
			    int questionNumber = (position) + 1;
				textArea.append(StringUtils.QUESTION_STRING + questionNumber + ". " + questions.get(position).getDescription());
				
				if (!correctAnswers.isEmpty()) {
					textArea.append(StringUtils.ANSWER_STRING + correctAnswers.get(position).getDescription());
				}
				
				textArea.append("\n\n");
		}
	}
}
