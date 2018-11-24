package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

	//Constantes
	public static final int ANCHO = 750;
	public static final int ALTO = (int) ((int)ANCHO*0.65);
	public static final Color COLOR_FONDO = new Color(217, 217, 219);
	public static String APP_NAME = "Problem Solver for Uva 558 - Wormholes";
	//Relaciones con Paneles
	private PanelPrincipal pPrincipal;
	private PanelGenerar pGenerar;
	private PanelArchivo pArchivo;
	
	//Constructor
	public VentanaPrincipal() {
		init();
	}
	
	private void init() {
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLayout(new BorderLayout());
		this.setTitle(APP_NAME);
		this.setPreferredSize(new Dimension(ANCHO,ALTO));
		this.setLocation(((int)(dm.getWidth()/2)-(ANCHO/2)),((int)(dm.getHeight()/2))-(ALTO/2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		addPanels();
		this.pack();
	}
	
	private void addPanels() {
		pGenerar = new PanelGenerar(this);
		pArchivo = new PanelArchivo(this);
		pPrincipal = new PanelPrincipal(this);
		JPanel aux = new JPanel(new GridLayout(2,1));
		aux.add(pArchivo);
		aux.add(pGenerar);
		this.add(aux,BorderLayout.EAST);
		this.add(pPrincipal,BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		VentanaPrincipal main = new VentanaPrincipal();
	}

}
