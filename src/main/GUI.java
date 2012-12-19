package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnFile.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		contentPane.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel UserInfoPanel = new JPanel();
		FlowLayout fl_UserInfoPanel = (FlowLayout) UserInfoPanel.getLayout();
		fl_UserInfoPanel.setAlignment(FlowLayout.RIGHT);
		TopPanel.add(UserInfoPanel, BorderLayout.NORTH);
		
		JTextPane txtpnTbthegr = new JTextPane();
		txtpnTbthegr.setText("TBthegr81");
		UserInfoPanel.add(txtpnTbthegr);
		
		JLabel Avatar = new JLabel("");
		UserInfoPanel.add(Avatar);
		Avatar.setIcon(new ImageIcon("/home/tb/Downloads/b555a82b90920fad7f3845bbef963be988ce1ff3_full.jpg"));
		
		JPanel SoftwareListPanel = new JPanel();
		contentPane.add(SoftwareListPanel, BorderLayout.WEST);
		GridBagLayout gbl_SoftwareListPanel = new GridBagLayout();
		gbl_SoftwareListPanel.columnWidths = new int[]{0, 0};
		gbl_SoftwareListPanel.rowHeights = new int[]{0, 0, 0};
		gbl_SoftwareListPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_SoftwareListPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		SoftwareListPanel.setLayout(gbl_SoftwareListPanel);
		
		JButton btnLeftdead = new JButton("Left4Dead");
		GridBagConstraints gbc_btnLeftdead = new GridBagConstraints();
		gbc_btnLeftdead.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLeftdead.insets = new Insets(0, 0, 5, 0);
		gbc_btnLeftdead.gridx = 0;
		gbc_btnLeftdead.gridy = 0;
		SoftwareListPanel.add(btnLeftdead, gbc_btnLeftdead);
		
		JButton btnCrysis = new JButton("Crysis");
		GridBagConstraints gbc_btnCrysis = new GridBagConstraints();
		gbc_btnCrysis.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCrysis.gridx = 0;
		gbc_btnCrysis.gridy = 1;
		SoftwareListPanel.add(btnCrysis, gbc_btnCrysis);
		
		JPanel MainPanel = new JPanel();
		contentPane.add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(null);
		
		JPanel SoftwareNameField = new JPanel();
		SoftwareNameField.setBackground(Color.GRAY);
		SoftwareNameField.setBounds(100, 50, 500, 73);
		MainPanel.add(SoftwareNameField);
		SoftwareNameField.setLayout(null);
		
		JLabel SoftwareName = new JLabel("Left 4 Dead");
		SoftwareName.setHorizontalAlignment(SwingConstants.LEFT);
		SoftwareName.setBounds(10, 0, 169, 36);
		SoftwareNameField.add(SoftwareName);
		SoftwareName.setBackground(Color.WHITE);
		SoftwareName.setFont(new Font("Ubuntu", Font.BOLD, 30));
		SoftwareName.setForeground(Color.WHITE);
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.setBounds(10, 36, 117, 25);
		SoftwareNameField.add(btnPlayGame);
		
		JLabel lblHoursPlayed = new JLabel("7 Hours Played");
		lblHoursPlayed.setBounds(135, 32, 146, 15);
		SoftwareNameField.add(lblHoursPlayed);
		
		JLabel lblLastPlayedYesterday = new JLabel("Last Played Yesterday");
		lblLastPlayedYesterday.setBounds(135, 46, 182, 15);
		SoftwareNameField.add(lblLastPlayedYesterday);
		
		JLabel SoftwareBG = new JLabel("GameBG");
		SoftwareBG.setBounds(0, 0, 1000, 600);
		MainPanel.add(SoftwareBG);
		SoftwareBG.setIcon(new ImageIcon(GUI.class.getResource("/main/l4dbg.png")));
		
		JPanel ProgressPanel = new JPanel();
		contentPane.add(ProgressPanel, BorderLayout.SOUTH);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(50);
		ProgressPanel.add(progressBar);
	}
}
