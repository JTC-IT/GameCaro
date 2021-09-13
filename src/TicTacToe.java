import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener{
	
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_pane = new JPanel();
	JPanel button_pane = new JPanel();
	JLabel label = new JLabel();
	JButton btPlay = new JButton();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;
	
	public TicTacToe() {
		frame.setTitle("Tic Tac Toe Game");
		frame.setIconImage(new ImageIcon(TicTacToe.class.getResource("icon.png")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(500, 600));
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		title_pane.setLayout(new BorderLayout());
		title_pane.setBounds(0,0,500,100);
		
		button_pane.setBackground(new Color(150,150,150));
		button_pane.setLayout(new GridLayout(3,3));
		
		frame.add(title_pane,BorderLayout.NORTH);
		frame.add(button_pane);
		
		btPlay.setBackground(new Color(25,25,25));
		btPlay.setBounds(0,0,100,100);
		btPlay.setIcon(new ImageIcon(TicTacToe.class.getResource("start-64.png")));
		btPlay.setToolTipText("Start");
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		title_pane.add(btPlay,BorderLayout.EAST);
		
		label.setBackground(new Color(25,25,25));
		label.setForeground(new Color(25,255,0));
		label.setFont(new Font("Ink Free", Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("Tic-Tac-Toe");
		label.setOpaque(true);
		title_pane.add(label,BorderLayout.CENTER);
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_pane.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 110));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		setEnabledButton(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 9; i++) {
			if(buttons[i] == e.getSource()) {
				if(buttons[i].getText() == "") {
					if(player1_turn) {
						buttons[i].setForeground(new Color(255,0,0));
						buttons[i].setText("X");
						player1_turn = false;
						label.setText("O turn");
					}else {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn = true;
						label.setText("X turn");
					}
					check();
				}
			}
		}
	}
	
	public void start() {
		refeshButtons();
		btPlay.setVisible(false);
		setEnabledButton(true);
		
		if(random.nextInt(2) == 0) {
			player1_turn = true;
			label.setText("X turn");
		}else {
			player1_turn = false;
			label.setText("O turn");
		}
	}
	
	public void check() {
		for(int i = 0; i < 9; i += 3) {
			if(buttons[i].getText() == "")
				continue;
			if(buttons[i].getText() == buttons[i+1].getText()
					&& buttons[i].getText() == buttons[i+2].getText()) {
				if(buttons[i].getText() == "X")
					xWin(i,i+1,i+2);
				else oWin(i,i+1,i+2);
				return;
			}
		}
		for(int i = 0; i < 3; i++) {
			if(buttons[i].getText() == "")
				continue;
			if(buttons[i].getText() == buttons[i+3].getText()
					&& buttons[i].getText() == buttons[i+6].getText()) {
				if(buttons[i].getText() == "X")
					xWin(i,i+3,i+6);
				else oWin(i,i+3,i+6);
				return;
			}
		}
		if(buttons[0].getText() != "" && buttons[0].getText() == buttons[4].getText()
				&& buttons[0].getText() == buttons[8].getText()) {
			if(buttons[0].getText() == "X")
				xWin(0,4,8);
			else oWin(0,4,8);
			return;
		}
		if(buttons[2].getText() != "" && buttons[2].getText() == buttons[4].getText()
				&& buttons[2].getText() == buttons[6].getText()) {
			if(buttons[2].getText() == "X")
				xWin(2,4,6);
			else oWin(2,4,6);
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(buttons[i].getText() == "")
				return;
		}
		label.setText("Drawn!");
		btPlay.setVisible(true);
		setEnabledButton(false);
	}
	
	public void xWin(int a, int b, int c) {
		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);
		setEnabledButton(false);
		label.setText("X win!");
		btPlay.setVisible(true);
	}
	public void oWin(int a, int b, int c) {
		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);
		setEnabledButton(false);
		label.setText("O win!");
		btPlay.setVisible(true);
	}
	
	public void setEnabledButton(boolean k) {
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(k);
		}
	}
	
	public void refeshButtons() {
		for(int i = 0; i < 9; i++) {
			buttons[i].setBackground(new Color(240, 230, 140));
			buttons[i].setText("");
		}
	}
}
