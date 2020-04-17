package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import constants.Constants;
import constants.Tetraminos;


public class Brick {

	private int rotation;
	private Point position;
	private int typeOfBrick;
	private Point[][] brickPoints;
	private static List<Integer> nextBrick = new ArrayList<>();

	private final int defaultNewX = 4;
	private final int defaultNewY = 0;

	public static void generateNexBrick() {
		if (nextBrick.isEmpty()) {
			nextBrick = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
			Collections.shuffle(nextBrick);
		}
	}

	public static int findOutNextBrick() {
		generateNexBrick();
		return nextBrick.get(0);
	}

	public static Integer getNextBrick() {
		return nextBrick.remove(0);
	}

	Brick() {
		this.position = new Point(defaultNewX, defaultNewY);
		this.rotation = 0;

		generateNexBrick();
		this.typeOfBrick = getNextBrick();

		// create 4 arrays of tetra with 4 rotations from class Tetraminos
		this.brickPoints = new Point[4][4];
		for (int i = 0; i < 4; i++)
			for (int r = 0; r < 4; r++) {
				this.brickPoints[r][i] = new Point();
				this.brickPoints[r][i].x = Tetraminos.arrayTetra[this.typeOfBrick][r][i][0];
				this.brickPoints[r][i].y = Tetraminos.arrayTetra[this.typeOfBrick][r][i][1];

			}
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	public void moveOn(int deltaX, int deltaY) {
		this.position.x += deltaX;
		this.position.y += deltaY;
	}

	public Point getRealBrickPosition(int index) {
		Point point = new Point();
		point.x = (brickPoints[this.rotation][index].x + this.position.x);
		point.y = (brickPoints[this.rotation][index].y + this.position.y);
		return point;
	}

	public Point getRealBrickPosition(int index, int rotation) {
		Point point = new Point();
		point.x = (brickPoints[rotation][index].x + this.position.x);
		point.y = (brickPoints[rotation][index].y + this.position.y);
		return point;
	}

	public Color getBrickColor() {
		return Constants.tetraminoColors[this.typeOfBrick];
	}

	public static Point[] getPointsAnotherTetraminos(int typeOfBrick) {
		Point[] brickPoints = new Point[4];
		for (int i = 0; i < 4; i++) {
			brickPoints[i] = new Point();
			brickPoints[i].x = Tetraminos.arrayTetra[typeOfBrick][0][i][0];
			brickPoints[i].y = Tetraminos.arrayTetra[typeOfBrick][0][i][1];
		}
		return brickPoints;

	}

}
