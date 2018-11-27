package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Join {

	private int x1,y1,x2,y2, id1, id2;
	private Random rd = new Random(System.nanoTime());
	private String value;
	
	public Join(int x1, int y1, int x2, int y2, String v) {
		super();
		value = v;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void pintar(Graphics g) {
		Color color = new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255));
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(color);
		if (x1 > x2 && y1 > y2) {
			g.drawString(value,x1-Math.abs((x1-x2)/2),y1-Math.abs((y1-y2)/2));
		} else if (x1 > x2 && y1 < y2) {
			g.drawString(value,x1-Math.abs((x1-x2)/2),y2-Math.abs((y1-y2)/2));
		} else if (x1 < x2 && y1 > y2) {
			g.drawString(value,x2-Math.abs((x1-x2)/2),y1-Math.abs((y1-y2)/2));
		} else if (x1 < x2 && y1 < y2) {
			g.drawString(value,x2-Math.abs((x1-x2)/2),y2-Math.abs((y1-y2)/2));
		}
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}
	
	public String getValue() {
		return value;
	}

	public boolean same(int id1, int id2) {
		return this.id1==id1 && this.id2==id2;
	}
	
}
