package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import model.Model;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Kliver Girón - Joe Hernandez - Christian Tamayo
 * @version 0.9
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	//Constantes
	/**
	 * Constante que representa el ancho de la aplicación<br>
	 */
	public static final int WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().width/1.68);//1.82);
	/**
	 * Constante que representa el alto de la aplicación<br>
	 */
	public static final int HEIGHT = (int) ((int)WIDTH*0.65);
	/**
	 * Constante que representa el color de fondo de la aplicación<br>
	 */
	public static final Color BACKGROUND = new Color(217, 217, 219);
	/**
	 * Constante que representa el nombre de la aplicación<br>
	 */
	public static final String APP_NAME = "Problem Solver for Uva 558 - Wormholes";
	//Relaciones con Paneles
	/**
	 * Relación con el panel principal de la aplicación<br>
	 */
	private PanelPrincipal pPrincipal;
	/**
	 * Relación con el panel generate de la aplicación<br>
	 */
	private PanelGenerate pGenerate;
	/**
	 * Relación con el panel file de la aplicación<br>
	 */
	private PanelFile pFile;
	//Relacion con Modelo
	/**
	 * Relación con el modelo del mundo de la aplicación<br>
	 */
	private Model model;
	//Constructor
	/**
	 * Construye la aplicación<br>
	 */
	public MainWindow() {
		init();
	}
	/**
	 * Método que inicializa las propiedades de la ventana de la aplicación<br>
	 */
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
	/**
	 * Método que agrega los paneles de la aplicación<br>
	 */
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
	/**
	 * Método que abre la ventana donde se muestra el gráfico del grafo para el primer caso de entrada introducido<br>
	 * @param edges
	 */
	public void openJDialogGraphic(int[][] edges) {
		this.setVisible(false);
		new JDialogGraphic(edges,this);
	}
	/**
	 * Método que cambia el tiempo que la aplicación demoro en resolver una entrada especifica<br>
	 * @param tiempo que tomo<br>
	 */
	public void changeTime(long tiempo) {
		pPrincipal.changeTime(tiempo);
	}
	/**
	 * Método que devuelve las aristas del primer caso de entrada que se halla introducido<br>
	 * @param data de las entradas que halla actualmente<br>
	 * @return arreglo bidimensional con las aristas del primer caso de entrada introducido<br>
	 */
	public int[][] getFirstEdges(String data) {
		return model.getFirstEdges(data);
	}
	/**
	 * Método que da solución a una entrada mediante el algoritmo de Bellman Ford y este implementado con Matriz de adyacencias<br>
	 * @param data de las entrada que halla actualmente<br>
	 */
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
	/**
	 * Método que da solución a una entrada mediante el algoritmo de Dijkstra y este implementado con Lista de adyacencias<br>
	 * @param data de las entrada que halla actualmente<br>
	 */
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
	/**
	 * Método que devuelve la conversión de un archivo es un string con los datos<br>
	 * @param path del archivo donde esta en el equipo<br>
	 * @return conversión del archivo a String<br>
	 */
	public String convertFileToString(String path) {
		return model.convertFileToString(path);
	}
	/**
	 * Método que cambia la entrada en el panel principal<br>
	 * @param data nuevos datos por el que se cambiara<br>
	 */
	public void changeInput(String data) {
		pPrincipal.changeInput(data);
	}
	/**
	 * Método que reinicia el campo de archivo seleccionado del panel file<br>
	 */
	public void resetFileSelected() {
		pFile.setArchivoSeleccionado("Ningún archivo seleccionado aún");
	}
	/**
	 * Método que genera casos de prueba<br>
	 * @param name del caso de prueba que se generara<br>
	 * @return booleano indicando si se logro crear el caso de prueba<br>
	 */
	public boolean generateTestCases(String name) {
		return model.generateTestCases(name);
	}
	/**
	 * Método que inicializa la aplicación<br>
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainWindow main = new MainWindow();
	}

}
