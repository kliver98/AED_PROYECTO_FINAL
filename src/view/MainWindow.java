package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import model.Model;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	//Constantes
	public static final int WIDTH = 750;
	public static final int HEIGHT = (int) ((int)WIDTH*0.65);
	public static final Color BACKGROUND = new Color(217, 217, 219);
	public static final String APP_NAME = "Problem Solver for Uva 558 - Wormholes";
	public static final String DESKTOP_PATH = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()+"\\";
	//Relaciones con Paneles
	private PanelPrincipal pPrincipal;
	private PanelGenerate pGenerate;
	private PanelFile pFile;
	//Relacion con Modelo
	private Model model;
	//Constructor
	public MainWindow() {
		init();
	}
	
	private void init() {
		model = new Model();
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
	
	public void solveByBellmanMatrix(String data) {
		String[] solved = model.solveByBellmanMatrix(data); 
		long time = Long.parseLong(solved[0]);
		if (time==-1) {
			String message = "ERROR DE ENTRADA";
			pPrincipal.changeOutput(message);
			pPrincipal.error(message);
		} else {
			pPrincipal.changeOutput(solved[1]);
			changeTime(time);
		}
	}
	
	public void solveByDijkstraList(String data) {
		String[] solved = model.solveByDijkstraList(data); 
		long time = Long.parseLong(solved[0]);
		if (time==-1)
			pPrincipal.changeOutput("ERROR DE ENTRADA");
		else {
			pPrincipal.changeOutput(solved[1]);
			changeTime(time);
		}
	}
	
	public String convertFileToString(String path) {
		return model.convertFileToString(path);
	}
	
	public void changeInput(String data) {
		pPrincipal.changeInput(data);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainWindow main = new MainWindow();
	}

}
