package object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSnake extends JPanel implements ActionListener {
	private final int High_Screen = 500;
	private final int Width_Screen = 980;
	private final int Unit_Size = 20;
	final int GAME_UNITS = (Width_Screen * High_Screen) / (Unit_Size * Unit_Size);
	private boolean running = false;
	public Timer timer;
	static final int DELAY = 160;
	private Random random;
	private int appleX;
	private int appleY;
	private int bodyParts = 1;
	char direction = 'R';
	public JTextField textScore;
	public int score;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];

	public PanelSnake() {
		random = new Random();
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		textScore = new JTextField();
	}

	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		
		timer.start();
//		return timer;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		this.setBackground(Color.black);
		if (running) {

			for (int i = 0; i <= Width_Screen / Unit_Size; i++) {
				g.drawLine(i * Unit_Size, 0, i * Unit_Size, High_Screen);
			}
			for (int i = 0; i <= High_Screen / Unit_Size; i++) {
				g.drawLine(0, i * Unit_Size, Width_Screen, i * Unit_Size);
			}

			g.setColor(Color.red);
			g.fillOval(appleX, appleY, Unit_Size, Unit_Size);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], Unit_Size, Unit_Size);
				} else {
					g.setColor(Color.yellow);
					g.fillRect(x[i], y[i], Unit_Size, Unit_Size);
				}
			}
			textScore.setText(String.valueOf(score));
		} else {
			gameOver(g);
		}

	}

	public void move() {

		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
		case 'U':
			y[0] = y[0] - Unit_Size;
			break;
		case 'D':
			y[0] = y[0] + Unit_Size;
			break;
		case 'L':
			x[0] = x[0] - Unit_Size;
			break;
		case 'R':
			x[0] = x[0] + Unit_Size;
			break;
		}
	}

	public void newApple() {
		appleX = random.nextInt(Width_Screen / Unit_Size) * Unit_Size;
		appleY = random.nextInt(High_Screen / Unit_Size) * Unit_Size;
	}

	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			score++;
			newApple();
		}
	}

	public void checkCollisions() {
		// checks if head collides with body
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// check if head touches left border
		if (x[0] < 0) {
			running = false;
		}
		// check if head touches right border
		if (x[0] > Width_Screen) {
			running = false;
		}
		// check if head touches top border
		if (y[0] < 0) {
			running = false;
		}
		// check if head touches bottom border
		if (y[0] > High_Screen - 2) {
			running = false;
		}

		if (!running) {
			timer.stop();
		}
	}

	public void gameOver(Graphics g) {
		if (!running) {
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 75));
			FontMetrics metrics2 = getFontMetrics(g.getFont());
			g.drawString("Game Over", (Width_Screen - metrics2.stringWidth("Game Over")) / 2, High_Screen / 2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();

	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {

			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				System.out.println("VK_LEFT");
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				System.out.println("VK_RIGHT");
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				System.out.println("VK_UP");
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				System.out.println("VK_DOWN");
				break;
			}

		}
	}

}
