package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

public class Node {
	
	/**
	 * Constante que representa el diametro del Nodo para dibujarlo sobre el lienzo<br>
	 */
	public static final int d = 60;
	/**
	 * Atributos que representa la ubicación del nodo en el lienzo y su identficación de nodo<br>
	 */
	private int x,y,id;
	/**
	 * Atributo para obtener un número random<br>
	 */
	private Random rd = new Random(System.nanoTime());
	/**
	 * Atributo Vector que contiene los joins adyacentes a este nodo<br>
	 */
	private Vector<Join> joins;
	/**
	 * Atributo que representa el color del nodo en el lienzo<br>
	 */
	private Color color;
	/**
	 * Atributo que contiene la información de los ovalos de nodos adyacentes a el<br>
	 */
	private HashSet<String> points; 
	
	/**
	 * COnstruye un Node<br>
	 * @param x con la posición en el eje x del lienzo<br>
	 * @param y con la posición en el eje y del lienzo<br>
	 * @param id con la identificación del nodo<br>
	 */
	public Node(int x,int y, int id) {
		int a = rd.nextInt(255),b = rd.nextInt(255),c = rd.nextInt(255);
		joins = new Vector<Join>();
		this.x = x;
		this.y = y;
		this.id = id;
		color = new Color(a,b,c);
		points = new HashSet<String>();
	}
	
	/**
	 * Mpetodo que pinta el nodo en el lienzo<br>
	 * @param g
	 */
	public void paint(Graphics g) {
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
	
	/**
	 * Método que devuelve una posición disponible para poner ovalos de nodos adyacentes a este<br>
	 * @param x con la posición que se desea poner el ovalo<br>
	 * @param y con la posición que se desea poner el ovalo<br>
	 * @return arreglo con las poiciones en x y y que puede dibujar el ovalo<br>
	 */
	public int[] avaiblePosition(int x, int y) {
		int a = this.x+d/2;
		int b = this.y+d/2;
		while (points.contains(x+"-"+y)) {
			if (x>=a && y>=b) {
				x+=10;
				y+=10;
			} else if (x>=a && y<=b) {
				x+=10;
				y-=10;
			}else if (x<=a && y<=b) {
				x+=10;
				y-=10;
			}else if (x<=a && y>=b) {
				x-=10;
				y-=10;
			}
		}
		points.add(x+"-"+y);
		return new int[] {x,y};
	}
	
	//GETTER AND SETTER
	
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public HashSet<String> getPoints() {
		return points;
	}

	public void setPoints(HashSet<String> points) {
		this.points = points;
	}

	public void setJoins(Vector<Join> joins) {
		this.joins = joins;
	}
	
}
