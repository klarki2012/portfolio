package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import constants.Constants;
import model.Brick;
import model.Model;
import model.Point;



public class TetrisPanel extends JPanel {

	public Model model;

	private Color colorBorderDraw = new Color(0x00, 0x00, 0x00);
	private Color colorBorderFill = new Color(0x69, 0x69, 0x69); // dimgray
	private Color backGroundDraw = new Color(0xFF, 0xDE, 0xAD); // navajowhite (brown)
	public Color backGroundFill;// = new Color(0xFF,0xF8,0xDC); // cornsilk (brown)

	TetrisPanel() {
		setBackGroundFill(true);
	}

	Color toDarkenTheColor(Color col, int effect) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), hsb);
		col = Color.getHSBColor(hsb[0], hsb[1], hsb[2] += effect);
		return col;

	}

	public void setBackGroundFill(boolean b) {
		if (b)
			this.backGroundFill = new Color(0xFF, 0xF8, 0xDC); // cornsilk (brown)
		else
			this.backGroundFill = new Color(0xD5, 0xDB, 0xDB);
	}

	protected void paintComponent(Graphics g) {
		if (model == null)
			return;

		super.paintComponent(g);
		int size = Constants.SQUARE_SIZE;
		int borderSizeX, borderSizeY;

		/// at first Paint only BACKGRAOUND
		for (int i = 0; i < model.getMap().getWidth(); i++) {
			for (int j = 0; j < model.getMap().getHeight(); j++) {

				if (model.getMap().getPixelsMatrix()[i][j] == Color.WHITE) {
					g.setColor(backGroundFill);
					g.fillRect(size * i - size / 2, size * j, size, size);
					g.setColor(backGroundDraw);
					g.drawRect(size * i - size / 2, size * j, size, size);

				}

			}
		}

		/// then paint Border and Bricks that on the Map
		for (int i = 0; i < model.getMap().getWidth(); i++) {
			for (int j = 0; j < model.getMap().getHeight(); j++) {

				// paint BORDER
				if (model.getMap().getPixelsMatrix()[i][j] == Color.BLACK) {

					if (j == model.getMap().getHeight() - 1)
						borderSizeY = size / 2;
					else
						borderSizeY = size;

					if ((i == 0) || (i == model.getMap().getWidth() - 1))
						borderSizeX = size / 2;
					else
						borderSizeX = size;

					// the first border line is twice as narrow
					if (i == 0) { // first linie
						g.setColor(toDarkenTheColor(colorBorderFill, j * 2));
						g.fillRect(size * i, size * j, borderSizeX, borderSizeY);
						g.setColor(colorBorderDraw);
						g.drawRect(size * i, size * j, borderSizeX, borderSizeY);

					} else { // other normal linies
						g.setColor(toDarkenTheColor(colorBorderFill, j * 2));
						g.fillRect(size * i - size / 2, size * j, borderSizeX, borderSizeY);
						g.setColor(colorBorderDraw);
						g.drawRect(size * i - size / 2, size * j, borderSizeX, borderSizeY);

					}

					// paint BRICKS on BackGround (tatraminos)
				} else if ((model.getMap().getPixelsMatrix()[i][j] != Color.WHITE)) {

					g.setColor(model.getMap().getPixelsMatrix()[i][j]);
					g.fillRect(size * i - size / 2, size * j, size, size);
					g.setColor(Color.BLACK);
					g.drawRect(size * i - size / 2, size * j, size, size);

				}
			}
		}

		// draw the next one Brick
		drawNextBrick(g, (model.getMap().getWidth() - 2) * size, 0);

		// draw the real Brick = Tertamino
		drawPiece(g);

		// show SCORE
		g.setColor(colorBorderFill);
		g.setFont(new Font("Tahoma", Font.PLAIN, size / 2 + size / 3));
		g.drawString("SCORE:" + model.getScore() + " SPEED:" + model.getPaceOfPlay(), 1 * size + 1, size - size / 6);

	}

	private void drawPiece(Graphics g) {
		if (model == null)
			return;
		int size = Constants.SQUARE_SIZE;
		Point point;

		for (int i = 0; i < 4; i++) {
			point = model.getBrick().getRealBrickPosition(i);
			g.setColor(model.getBrick().getBrickColor());
			g.fillRect(point.x * size - size / 2, point.y * size, size, size);
			g.setColor(Color.BLACK);
			g.drawRect(point.x * size - size / 2, point.y * size, size, size);
		}

	}

	private void drawNextBrick(Graphics g, int x, int y) {
		int size = Constants.SQUARE_SIZE / 5;

		int typeOfNextBrick = Brick.findOutNextBrick();
		Point[] points = Brick.getPointsAnotherTetraminos(typeOfNextBrick);

		for (int i = 0; i < 4; i++) {
			g.setColor(Constants.tetraminoColors[typeOfNextBrick]);
			g.fillRect((points[i].x) * size + x, (points[i].y) * size + y, size, size);
			g.setColor(Color.BLACK);
			g.drawRect((points[i].x) * size + x, (points[i].y) * size + y, size, size);
		}

	}

}
