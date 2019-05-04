package quizApp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.UiUtils;

public class WelcomeView extends JFrame {
	
	public WelcomeView() {

		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 785, 497);
		initWelcomeView();
		setVisible(true);
		
	}
	
	private void initWelcomeView(){
		getContentPane().setBackground(new Color(255, 0, 153));
		setBounds(100, 100, 703, 467);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(0, 0, 800, 40);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(0, 388, 800, 40);
		getContentPane().add(panel_1);
		
		JLabel label = new JLabel("Welcome to the BTech Quiz Game");
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBounds(80, 90, 511, 37);
		getContentPane().add(label);
		
		JLabel lblWouldYouLike = new JLabel("Would you like to read the rules ?");
		lblWouldYouLike.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblWouldYouLike.setBounds(140, 193, 380, 37);
		getContentPane().add(lblWouldYouLike);
		
		JButton btnShowMeRules = new JButton("Don't show rules");
		btnShowMeRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserView();
//				System.out.println("clicked don't show rules");
			}
		});
		btnShowMeRules.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowMeRules.setBounds(357, 312, 131, 40);
		getContentPane().add(btnShowMeRules);
		
		JButton button = new JButton("Show me rules");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Rules();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(170, 312, 122, 40);
		getContentPane().add(button);
//		setVisible(true);
		UiUtils.closeWindow(this);
	}

}
