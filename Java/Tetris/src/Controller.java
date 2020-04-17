

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import model.Model;
import view.MainView;

public class Controller implements KeyListener, Observer {

	private MainView mainView;
	private Model model;
	private boolean gameOver;

	private final int SLEEP_TIME = 1300;
	private int currentSleepTime;
	private boolean pause;

	public Controller() throws InterruptedException {

		model = new Model();
		model.addObserver(this);
		
		mainView = new MainView();
		mainView.addKeyListener(this);
		mainView.tetrisPanel.model = model;

		gameInit();

		while (!gameOver) {

			Thread.sleep(currentSleepTime);

			if (pause)
				makePause();

			model.play();

			if (gameOver) {

				mainView.tetrisPanel.setBackGroundFill(false);
				
				int result = JOptionPane.showConfirmDialog((Component) null,
						"Congratulations! You got " + model.getScore() + " points. Start new Game?", "GAME OVER",
						JOptionPane.OK_CANCEL_OPTION);
				
				if (result == 0) 
					gameInit();
				
			}
		}

	}
	
	public void gameInit() {
		model.init();
		gameOver = false;
		mainView.tetrisPanel.setBackGroundFill(true);
		calculateSleepTime();
		pause = false;
		
	}

	private void makePause() throws InterruptedException {
		JOptionPane.showMessageDialog((Component) null, "PAUSE! ... press OK button to continue ", "Tetris",
				JOptionPane.DEFAULT_OPTION);
		pause = false;
		Thread.sleep(currentSleepTime);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Model) {

			if (arg != null) {

				switch ((String) arg) {

				case "GAME OVER":
					gameOver = true;
					break;

				case "change the pace of the game":
					calculateSleepTime();
					break;

				default:
					;

				}
			}
			mainView.updateView(arg); //
		}
		// TODO Auto-generated method stub increase the pace of the game

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			this.pause = true;
			break;

		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			if (!gameOver)
				model.rotateTetra();
			break;

		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			if (!gameOver)
				model.play();
			break;

		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			if (!gameOver)
				model.move(-1, 0);
			break;

		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			if (!gameOver)
				model.move(1, 0);
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void calculateSleepTime() {

		this.currentSleepTime = SLEEP_TIME;
		int temp = model.getPaceOfPlay();
		int step = 10;

		for (int i = 0; i < Math.abs(temp); i++) {
			if (temp > 0)
				this.currentSleepTime -= this.currentSleepTime / step;
			else
				this.currentSleepTime += this.currentSleepTime / step;

		}
	}

}
