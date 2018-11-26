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

	public static final String SOLVE = "SOLUCIONAR";
	public static final String DELETE = "BORRAR";
	public static final String GRAPHIC = "Gráfica";
	public static final String[] OPTIONS_TIPE_ALG = {"Tipo de Algoritmo","Bellman Ford","Dijkstra"};
	public static final String[] OPTIONS_VERSION_GRAPH = {"Versión del grafo","Lista de adyacencias","Matriz de adyacencias"};
	private JTextArea input;
	private JTextArea output;
	private JLabel infTime;
	private JComboBox<String> tipeAlg;
    private JComboBox<String> versionGraph;
	private MainWindow main;

	public PanelPrincipal(MainWindow main) {
		super();
		this.main = main;
		init();
	}
	
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
	    JPanel aux2 =new JPanel(new GridLayout(3,1));
	    tipeAlg = new JComboBox<String>();
	    versionGraph = new JComboBox<String>();
	    aux2.add(tipeAlg);
	    aux2.add(versionGraph);
	    aux2.setBorder(BorderFactory.createMatteBorder(10,50,10,50,main.getBackground()));
	    DefaultComboBoxModel<String> tipoAlgData = new DefaultComboBoxModel<String>(OPTIONS_TIPE_ALG);
	    DefaultComboBoxModel<String> versionGrafoData = new DefaultComboBoxModel<String>(OPTIONS_VERSION_GRAPH);
	    tipeAlg.setModel(tipoAlgData);
	    versionGraph.setModel(versionGrafoData);
	    infTime = new JLabel("Tiempo que tardo el algoritmo en encontrar la solución: NA");
	    infTime.setHorizontalAlignment(SwingConstants.CENTER);
	    aux2.add(infTime);
	    add(aux2);
	    aux.add(delete);
	    aux.add(graphic);
	    aux.add(solve);
	    aux.setBorder(BorderFactory.createMatteBorder(10,10,10,10,main.getBackground()));
	    add(aux);
	}
	
	public void changeTime(long tiempo) {
		infTime.setForeground(Color.BLACK);
		infTime.setText("Tiempo que tardo el algoritmo en encontrar la solución: "+tiempo+" ns");
	}
	
	public void error(String message) {
		infTime.setText(message);
		infTime.setForeground(Color.RED);
	}
	
	public void changeOutput(String data) {
		output.setText(data);
	}
	
	public void changeInput(String data) {
		input.setText(data);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(DELETE)) {
			input.setText("");
			output.setText("");
			infTime.setText("Tiempo que tardo el algoritmo en encontrar la solución: NA");
		} else if (str.equals(SOLVE)) {
			if (!input.getText().isEmpty() && 
					!OPTIONS_TIPE_ALG[tipeAlg.getSelectedIndex()].equals(OPTIONS_TIPE_ALG[0]) && 
					!OPTIONS_VERSION_GRAPH[versionGraph.getSelectedIndex()].equals(OPTIONS_VERSION_GRAPH[0])) {
				boolean BM = OPTIONS_TIPE_ALG[tipeAlg.getSelectedIndex()].equals(OPTIONS_TIPE_ALG[1]) && OPTIONS_VERSION_GRAPH[versionGraph.getSelectedIndex()].equals(OPTIONS_VERSION_GRAPH[2]); //Bellman y Matrix
				boolean BL = OPTIONS_TIPE_ALG[tipeAlg.getSelectedIndex()].equals(OPTIONS_TIPE_ALG[1]) && OPTIONS_VERSION_GRAPH[versionGraph.getSelectedIndex()].equals(OPTIONS_VERSION_GRAPH[1]); //Bellman y List
				boolean DL = OPTIONS_TIPE_ALG[tipeAlg.getSelectedIndex()].equals(OPTIONS_TIPE_ALG[2]) && OPTIONS_VERSION_GRAPH[versionGraph.getSelectedIndex()].equals(OPTIONS_VERSION_GRAPH[1]); //Dijkstra y List
				boolean DM = OPTIONS_TIPE_ALG[tipeAlg.getSelectedIndex()].equals(OPTIONS_TIPE_ALG[2]) && OPTIONS_VERSION_GRAPH[versionGraph.getSelectedIndex()].equals(OPTIONS_VERSION_GRAPH[2]); //Dijkstra y Matrix
				if (BM)
					main.solveByBellmanMatrix(input.getText());
				else if (BL)
					main.solveByBellmanList(input.getText());
				else if (DM)
					main.solveByDijkstraMatrix(input.getText());
				else if (DL)
					main.solveByDijkstraList(input.getText());
			} else
				error("SELECCIONE TIPO DE ALGORITMO Y VERSION DEL GRAFO A USAR");
		} else if (str.equals(GRAPHIC)) {
			main.openJDialogGraphic(0,new int[][][] {});
		}
	}
	
}
