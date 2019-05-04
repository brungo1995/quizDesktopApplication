package quizApp.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.UiUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rules extends JFrame{
	public Rules() {
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width / 2, screenSize.height / 2);
	    initRule();
	    setVisible(true);
	}
	
	private void initRule(){
		getContentPane().setBackground(new Color(255, 0, 153));
		getContentPane().setLayout(null);
		setBounds(100, 100, 703, 467);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(0, 0, 687, 40);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(0, 404, 687, 40);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Instructions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(246, 51, 153, 40);
		getContentPane().add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(73, 102, 531, 252);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("1. Enter your username and student number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 11, 511, 29);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblIf = new JLabel("2. Click Next");
		lblIf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIf.setBounds(10, 51, 511, 29);
		panel_2.add(lblIf);
		
		JLabel lblNewLabel_2 = new JLabel("3. Select Subject");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 87, 485, 29);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("4. Click Play");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 127, 511, 29);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblSeeYour = new JLabel("5. See your score");
		lblSeeYour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeeYour.setBounds(10, 167, 511, 29);
		panel_2.add(lblSeeYour);
		
		JLabel lblSeeMemo = new JLabel("6. See memo");
		lblSeeMemo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeeMemo.setBounds(10, 207, 382, 23);
		panel_2.add(lblSeeMemo);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new UserView();
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNext.setBounds(283, 365, 89, 23);
		getContentPane().add(btnNext);
		UiUtils.closeWindow(this);
	}
}
