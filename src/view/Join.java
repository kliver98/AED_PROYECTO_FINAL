package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Join {

	/**
	 * Representa la información de la ubicación desde el origen de la arista hasta su fin, al igual que los nodos que conecta<br>
	 */
	private int x1,y1,x2,y2, id1, id2;
	/**
	 * Atributo para obtener un número random<br>
	 */
	private Random rd = new Random(System.nanoTime());
	/**
	 * Atributo que representa el valor o información que contiene la arista<br>
	 */
	private String value;
	/**
	 * Atributo que representa el color de la arista<br>
	 */
	private Color color = new Color(rd.nextInt(200)+55,rd.nextInt(200)+55,rd.nextInt(200)+55);
	/**
	 * Atributo que representa el nodo origen de la arista<br>
	 */
	private Node node1;
	/**
	 * Atributo que representa el nodo destino de la arista<br>
	 */
	private Node node2;
	
	/**
	 * Construye un Join, representación gráfica de una arista<br>
	 * @param v valor o información de la arsita<br>
	 * @param n1 nodo origen de la arista<br>
	 * @param n2 nodo destino de la arista<br>
	 */
	public Join(String v, Node n1, Node n2) {
		super();
		value = v;
		node1 = n1;
		node2 = n2;
		this.x1 = n1.getX();
		this.y1 = n1.getY();
		this.x2 = n2.getX();
		this.y2 = n2.getY();
	}
	
	/**
	 * Pinta la arista sobre el lienzo<br>
	 * @param g
	 * @param paintOvals booleano que indica si pinta los ovalos su nodo origen<br>
	 */
	public void paintLines(Graphics g, boolean paintOvals) {
		if (getId1()==getId2()){ // Si tiene relación a hacia si misma
			g.setColor(Color.BLACK);
			g.drawArc(getX1(), getY1(), (int)(Node.d/(1.3)), (int)(Node.d/(1.3)), 00, 360);
			//Pintando el peso
			g.drawString(value, x1+(int)(Node.d/1.3), y1+(int)(Node.d/1.3));
		} else {			
			g.setColor(color);
			g.drawLine(x1, y1, x2, y2);
			g.setColor(color);
			//Pintando el peso
			int tx = 0, ty = 0;
			if (x1 > x2 && y1 > y2) {
				tx = x1-Math.abs((x1-x2)/2);
				ty = y1-Math.abs((y1-y2)/2);
				g.drawString(value,tx,ty);
			} else if (x1 > x2 && y1 < y2) {
				tx = x1-Math.abs((x1-x2)/2);
				ty = y2-Math.abs((y1-y2)/2);
				g.drawString(value,tx,ty);
			} else if (x1 < x2 && y1 > y2) {
				tx = x2-Math.abs((x1-x2)/2);
				ty = y1-Math.abs((y1-y2)/2);
				g.drawString(value,tx,ty);
			} else if (x1 < x2 && y1 < y2) {
				tx = x2-Math.abs((x1-x2)/2);
				ty = y2-Math.abs((y1-y2)/2);
				g.drawString(value,tx,ty);
			}
			if (paintOvals) {
				g.setColor(node1.getColor());
				int[] points = node2.avaiblePosition(x2+Node.d/2, y2+Node.d/2);
				g.fillOval(points[0], points[1], Node.d/5, Node.d/5);
			}
		}
	}

	//GETTER AND SETTER
	
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

	public int getId1() {
		return id1;
	}

	public int getId2() {
		return id2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Node getNode1() {
		return node1;
	}

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public Node getNode2() {
		return node2;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;
	}
	
}
