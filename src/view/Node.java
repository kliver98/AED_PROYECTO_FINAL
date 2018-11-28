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
	private Color color;
	
	public Node(int x,int y, int id) {
		int a = rd.nextInt(255),b = rd.nextInt(255),c = rd.nextInt(255);
		joins = new Vector<Join>();
		this.x = x;
		this.y = y;
		this.id = id;
		color = new Color(a,b,c);
	}
	
	public void pintar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x-(d/2)-2, y-(d/2)-2, d+5, d+5);
		g.setColor(color);
		g.fillOval(this.x-d/2, this.y - d/2, d, d);
		g.setColor(Color.WHITE);
		int xt = x, aux = 40;
		if (id<100) {
			aux = 30;
			xt+=5;
		}
		g.fillRect(xt-20, y-10, aux, 12);
		g.setColor(Color.BLACK);
		xt = x-3;
		if (id>99 && id<1000)
			xt-=10;
		else if (id>999)
			xt-=15;
		g.drawString(id+"",xt,y);
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
