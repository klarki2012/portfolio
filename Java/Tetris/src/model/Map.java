package model;

import java.awt.Color;

import constants.Constants;

public class Map {
	private Color[][] pixelsMatrix;
	private int width;
	private int height;

	Map() {
		this.init();
	}

	void init() {

		width = Constants.PIXEL_MATRIX_WIDTH;
		height = Constants.PIXEL_MATRIX_HEIGHT;

		pixelsMatrix = new Color[width][height];

		makeMatrixDefault();

	}

	public void makeMatrixDefault() {

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				if (i == 0 || i == (width - 1) || j == (height - 1)) {
					pixelsMatrix[i][j] = Color.BLACK;

				} else {
					pixelsMatrix[i][j] = Color.WHITE;
				}
			}
		}

	}

	public int deleteCompletedLines() {

		int numberDeletedRows = 0;
		boolean completed;

		for (int j = Constants.PIXEL_MATRIX_HEIGHT - 2; j > 0; j--) {

			completed = false;
			for (int i = 1; i < Constants.PIXEL_MATRIX_WIDTH; i++) {

				if (this.pixelsMatrix[i][j] == Color.WHITE) {

					completed = true;
					break;
				}

			}

			if (!completed) {

				deleteRow(j);
				j++;
				numberDeletedRows++;

			}

		}

		return numberDeletedRows;
	}

	void deleteRow(int row) {

		for (int j = row - 1; j > 0; j--) {
			for (int i = 1; i < Constants.PIXEL_MATRIX_WIDTH - 1; i++) {

				this.pixelsMatrix[i][j + 1] = this.pixelsMatrix[i][j];

			}
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color[][] getPixelsMatrix() {
		Color[][] colorMatrix = new Color[this.pixelsMatrix.length][this.pixelsMatrix[0].length];
		colorMatrix = this.pixelsMatrix.clone();
		//return this.pixelsMatrix;
		return colorMatrix;
	}

}
