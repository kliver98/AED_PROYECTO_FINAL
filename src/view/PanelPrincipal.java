package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelPrincipal extends JPanel implements ActionListener {

	/**
	 * Constante que representa el bot�n de solucionar problema<br>
	 */
	public static final String SOLVE = "SOLUCIONAR";
	/**
	 * Constante que representa el bot�n de borrar campos<br>
	 */
	public static final String DELETE = "BORRAR";
	/**
	 * Constante que representa el bot�n de gr�fica del 1mer caso<br>
	 */
	public static final String GRAPHIC = "GR�FICA 1mer caso";
	/**
	 * Constante que representa los tipos de soluciones para el problema<br>
	 */
	public static final String[] SOLUTION_TYPE = {"Seleccione tipo de soluci�n","Dijkstra con Lista de adyacencias","Bellman Ford con Matriz de adyacencias"};
	/**
	 * Atributo que representa el JTextArea de input del problema<br>
	 */
	private JTextArea input;
	/**
	 * Atributo que representa el JTextArea del output del problema<br>
	 */
	private JTextArea output;
	/**
	 * Atributo que representa la informaci�n del tiempo que demoro el algoritmo en solucionar la input<br>
	 */
	private JLabel infTime;
	/**
	 * Atributo tipo JComboBox que representa el tipo de soluci�n que el usuario eligi�<br>
	 */
	private JComboBox<String> solutionType;
	/**
	 * Atributo con la referencia de la ventana principal<br>
	 */
	private MainWindow main;

	/**
	 * Constuye un PanelPrincipal
	 * @param main con la referencia de la ventana principal<br>
	 */
	public PanelPrincipal(MainWindow main) {
		super();
		this.main = main;
		init();
	}
	
	/**
	 * M�todo que inicializa las porpiedades y atributos del panel<br>
	 */
	private void init() {
		this.setBorder(BorderFactory.createMatteBorder(10,10,10,5,MainWindow.BACKGROUND));
		this.setLayout(new GridLayout(5,1));
		this.setPreferredSize(new Dimension((int)(MainWindow.WIDTH/3.5),(int)(MainWindow.HEIGHT/2)));
		JLabel informacion = new JLabel("<html><center>En el primer campo ingrese la entrada de acuerdo al formato del enunciado"
				+ "<br/>En el segundo campo podra visualizar la respuesta o salida esperada</center></html>",SwingConstants.CENTER);
		input = new JTextArea();
		output = new JTextArea();
		int upDown = 3;
		int leftRight = 10;
		input.setBorder(BorderFactory.createMatteBorder(upDown,leftRight,upDown,leftRight,main.getBackground()));
		output.setBorder(BorderFactory.createMatteBorder(upDown,leftRight,upDown,leftRight,main.getBackground()));
		JButton solve = new JButton(SOLVE);
		JButton delete = new JButton(DELETE);
		JButton graphic = new JButton(GRAPHIC);
		solve.addActionListener(this);
		delete.addActionListener(this);
		graphic.addActionListener(this);
		TextPrompt tp1 = new TextPrompt("Input",input);
		tp1.changeAlpha(0.75f);
	    tp1.changeStyle(Font.PLAIN);
	    TextPrompt tp2 = new TextPrompt("Output",output);
		tp2.changeAlpha(0.75f);
	    tp2.changeStyle(Font.PLAIN);
	    add(informacion);
	    JScrollPane auxIn = new JScrollPane(input);
	    JScrollPane auxOut = new JScrollPane(output);
	    auxIn.setBorder(null);
	    auxOut.setBorder(null);
	    add(auxIn);
	    add(auxOut);
	    JPanel aux = new JPanel(new GridLayout(1,3));
	    JPanel aux2 =new JPanel(new GridLayout(2,1));
	    aux2.setBorder(BorderFactory.createMatteBorder(10,50,10,50,main.getBackground()));
	    DefaultComboBoxModel<String> solutionType = new DefaultComboBoxModel<String>(SOLUTION_TYPE);
	    this.solutionType = new JComboBox<String>(solutionType);
	    infTime = new JLabel("Tiempo que tardo el algoritmo en encontrar la soluci�n: NA");
	    infTime.setHorizontalAlignment(SwingConstants.CENTER);
	    aux2.add(this.solutionType);
	    aux2.add(infTime);
	    add(aux2);
	    aux.add(delete);
	    aux.add(graphic);
	    aux.add(solve);
	    aux.setBorder(BorderFactory.createMatteBorder(10,10,10,10,main.getBackground()));
	    add(aux);
	}
	
	/**
	 * M�todo que cambai la informaci�n del tiempo que el algoritmo demoro en resolver la input<br>
	 * @param tiempo con la informaci�n del nuevo tiempo<br>
	 */
	public void changeTime(long tiempo) {
		infTime.setForeground(Color.BLACK);
		infTime.setText("Tiempo que tardo el algoritmo en encontrar la soluci�n: "+tiempo+" ns");
	}
	
	/**
	 * M�todo que indicada medinate un mensaje, una acci�n m�l ejecutada por parte del usuario<br>
	 * @param message con la informaci�n del mensaje a mostrar<br>
	 */
	public void error(String message) {
		infTime.setText(message);
		infTime.setForeground(Color.RED);
	}
	
	/**
	 * M�todo que cambia el JTextField de output<br>
	 * @param data con la nueva informaci�n para cambair<br>
	 */
	public void changeOutput(String data) {
		output.setText(data);
	}
	
	/**
	 * M�todo que cambia la informaci�n de la entrada<br>
	 * @param data con la informaci�n que se cambiara<br>
	 */
	public void changeInput(String data) {
		input.setText(data);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(DELETE)) {
			input.setText("");
			output.setText("");
			infTime.setForeground(Color.BLACK);
			infTime.setText("Tiempo que tardo el algoritmo en encontrar la soluci�n: NA");
			main.resetFileSelected();
		} else if (str.equals(SOLVE)) {
			if (input.getText().isEmpty()) {
				error("ENTRADA VAC�A");
				return;
			}
			if (!SOLUTION_TYPE[solutionType.getSelectedIndex()].equals(SOLUTION_TYPE[0])) {
				boolean BM = SOLUTION_TYPE[2].equals(SOLUTION_TYPE[solutionType.getSelectedIndex()]);
				boolean DL = SOLUTION_TYPE[1].equals(SOLUTION_TYPE[solutionType.getSelectedIndex()]);
				if (BM)
					main.solveByBellmanMatrix(input.getText());
				else if (DL)
					main.solveByDijkstraList(input.getText());
			} else {
				error("SELECCIONE TIPO DE SOLUCI�N");
			}
		} else if (str.equals(GRAPHIC)) {
			if (input.getText().isEmpty()) {
				error("ENTRADA VAC�A");
				return;
			}
			int[][] edges = main.getFirstEdges(input.getText());
			if (edges==null) {
				error("ERROR DE ENTRADA");
				return;
			}
			main.openJDialogGraphic(edges);
		}
	}
	
}
