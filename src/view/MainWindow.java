package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	//Constantes
	public static final int WIDTH = 750;
	public static final int HEIGHT = (int) ((int)WIDTH*0.65);
	public static final Color BACKGROUND = new Color(217, 217, 219);
	public static String APP_NAME = "Problem Solver for Uva 558 - Wormholes";
	//Relaciones con Paneles
	private PanelPrincipal pPrincipal;
	private PanelGenerate pGenerate;
	private PanelFile pFile;
	
	//Constructor
	public MainWindow() {
		init();
	}
	
	private void init() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(new BorderLayout());
		this.setTitle(APP_NAME);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setLocation(((int)(dm.getWidth()/2)-(WIDTH/2)),((int)(dm.getHeight()/2))-(HEIGHT/2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		addPanels();
		this.pack();
	}
	
	private void addPanels() {
		pGenerate = new PanelGenerate(this);
		pFile = new PanelFile(this);
		pPrincipal = new PanelPrincipal(this);
		JPanel aux = new JPanel(new GridLayout(2,1));
		aux.add(pFile);
		aux.add(pGenerate);
		add(aux,BorderLayout.EAST);
		add(pPrincipal,BorderLayout.CENTER);
	}
	
	public void openJDialogGraphic(int numberVertexes, int[][][] edges) {
		this.setVisible(false);
		new JDialogGraphic(numberVertexes,edges,this);
	}
	
	public void changeTime(long tiempo) {
		pPrincipal.changeTime(tiempo);
	}
	
	public void solveByBellmanMatrix() {
		long time = 0;
		changeTime(time);
	}
	
	public void solveByBellmanList() {
		long time = 0;
		changeTime(time);
	}
	
	public void solveByDijkstraMatrix() {
		long time = 0;
		changeTime(time);
	}
	
	public void solveByDijkstraList() {
		long time = 0;
		changeTime(time);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainWindow main = new MainWindow();
	}

}
