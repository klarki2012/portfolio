package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.Constants;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	public TetrisPanel tetrisPanel;

	public MainView() {

		initialize();

	}

	private void initialize() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize((Constants.PIXEL_MATRIX_WIDTH - 1) * (Constants.SQUARE_SIZE) + 6,
				Constants.SQUARE_SIZE * (Constants.PIXEL_MATRIX_HEIGHT) + 28 - Constants.SQUARE_SIZE / 2);
		this.setLocationRelativeTo(null);

		tetrisPanel = new TetrisPanel();
		this.add(tetrisPanel, BorderLayout.CENTER);

		this.setResizable(false);
		this.setTitle("Tetris v1.6");
		this.setVisible(true);

	}

	public void updateView(Object o) {

		this.tetrisPanel.repaint();

	}

}
