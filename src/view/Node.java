package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Vector;

public class Node {
	
	public static final int d = 60;
	private int x,y,id;
	private Random rd = new Random(System.nanoTime());
	private Vector<Join> joins;
	
	public Node(int x,int y, int id) {
		joins = new Vector<Join>();
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public void pintar(Graphics g) {
		int a = rd.nextInt(255),b = rd.nextInt(255),c = rd.nextInt(255);
		g.setColor(new Color(a,b,c));
		g.fillOval(this.x-d/2, this.y - d/2, d, d);
		g.setColor(Color.WHITE);
		g.fillRect(x-10, y-10, 20, 12);
		g.setColor(Color.BLACK);
		g.drawString(id+"",x,y);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Vector<Join> getJoins() {
		return joins;
	}
	
}
