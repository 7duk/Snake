package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import object.PanelSnake;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class home extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNew;
	private JButton btnQuit;
	Timer time;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public home() {
		setTitle("Snake");
		setVisible(true);
		setResizable(false);
		// không cho thay đổi kíck thức của swing (setResizable)
		setLocationRelativeTo(null);
		// set cho khung giao diện vào giữa (setLocationRelativeTo)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 250, 1008, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		PanelSnake pSnake =new PanelSnake();
		time = pSnake.timer;
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		
		JPanel panel = pSnake;
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		
		btnNew = new JButton("New");
		btnNew.setBackground(Color.GREEN);
		
		 btnQuit = new JButton("Quit");
		 btnQuit.setBackground(Color.GREEN);
		 btnNew.addActionListener(new Action());
		 btnQuit.addActionListener(new Action());
		panel_1.add(panel_2);
		
		panel_2.setLayout(new GridLayout(0, 6, 50, 0));
		panel_2.add(btnNew);
		panel_2.add(btnQuit);
		
		JLabel lblNewLabel = new JLabel("Score : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(lblNewLabel);
		
		textField = pSnake.textScore;
		panel_2.add(textField);

	}
	public class Action implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnNew)){
				System.out.println("Đây");
				new home();
			}
			if(e.getSource().equals(btnQuit)){
				System.exit(EXIT_ON_CLOSE);
			}
			
		}
	}
}
