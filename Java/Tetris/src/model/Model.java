package model;

import java.awt.Color;
import java.util.Observable;

public class Model extends Observable {

	private Map map;
	private Brick brick;
	private int score;
	private int paceOfPlay;

	public Model() {
		init();
	}

	public void init() {

		this.paceOfPlay = 3;
		this.score = 0;

		map = new Map();
		runNewBrick();

	}

	public void play() {

		if (!checkCollision(brick, map, 0, 1, this.brick.getRotation())) {

			this.move(0, 1);

		} else {

			addBrickToMap();
			int NumOfDeletedRows = this.map.deleteCompletedLines();

			if (NumOfDeletedRows > 0) {
				int extraPoint=0;
				switch(NumOfDeletedRows) {
				case 1 :
					extraPoint = 5;
					break;
				case 2 :
					extraPoint = 12;
					break;
				case 3 :
					extraPoint = 20;
					break;
				case 4 :
					extraPoint = 30;
					break;	
				}
				

				score = score + extraPoint;

				switch (NumOfDeletedRows) {
				case 1:
					this.paceOfPlay++;
					break;
				case 2:
					this.paceOfPlay--;
					break;
				case 3:
					this.paceOfPlay -= 2;
					break;
				case 4:
					this.paceOfPlay -= 3;
				}

				if (this.paceOfPlay < 0)
					this.paceOfPlay = 0;

				this.setChanged();
				this.notifyObservers("change the pace of the game");


			}

			if (!runNewBrick()) {
				this.setChanged();
				this.notifyObservers("GAME OVER");
			}

		}
	}

	public void addBrickToMap() {

		Point point;
		for (int i = 0; i < 4; i++) {

			point = this.brick.getRealBrickPosition(i);
			this.map.getPixelsMatrix()[point.x][point.y] = this.brick.getBrickColor();

		}

	}

	public boolean checkCollision(Brick brick, Map map, int deltaX, int deltaY, int rotation) {
		Point point;

		for (int i = 0; i < 4; i++) {
			point = this.brick.getRealBrickPosition(i, rotation);
			if (map.getPixelsMatrix()[point.x + deltaX][point.y + deltaY] != Color.WHITE)
				return true;
		}

		return false;
	}

	public boolean checkCollision(Brick brick, Map map) {
		Point point;

		for (int i = 0; i < 4; i++) {
			point = brick.getRealBrickPosition(i, brick.getRotation());
			if (map.getPixelsMatrix()[point.x][point.y] != Color.WHITE)
				return true;
		}

		return false;
	}

	public boolean runNewBrick() {
		Brick newBrick = new Brick();
		if (this.checkCollision(newBrick, map))
			return false;

		this.brick = newBrick;
		this.setChanged();
		this.notifyObservers();
		return true;

	}

	public void rotateTetra() {

		int newRotation = (this.brick.getRotation() + 1) % 4;
		if (!checkCollision(brick, map, 0, 0, newRotation)) {

			this.brick.setRotation(newRotation);
			this.setChanged();
			this.notifyObservers();
		}

	}

	public void move(int deltaX, int deltaY) {

		if (!checkCollision(brick, map, deltaX, deltaY, this.brick.getRotation())) {

			this.brick.moveOn(deltaX, deltaY);
			this.setChanged();
			this.notifyObservers();
		}
	}

	public Map getMap() {
		return map;
	}

	public Brick getBrick() {
		return this.brick;
	}

	public int getScore() {
		return this.score;
	}

	public int getPaceOfPlay() {
		return paceOfPlay;
	}

}
