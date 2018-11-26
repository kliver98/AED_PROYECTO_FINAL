package view;

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

	public static final String RESOLVER = "Resolver";
	public static final String BORRAR = "Borrar";
	public static final String GRAFICA = "Gráfica";
	public static final String TIPO_ALG = "Tipo de algoritmo a usar";
	public static final String VERSION_GRAFO = "Versión del grafo a usar";
	private JTextArea input;
	private JTextArea output;
	private JLabel infTiempo;
	private VentanaPrincipal vent;

	public PanelPrincipal(VentanaPrincipal vent) {
		super();
		this.vent = vent;
		init();
	}
	
	private void init() {
		this.setBorder(BorderFactory.createMatteBorder(10,10,10,5,VentanaPrincipal.COLOR_FONDO));
		this.setLayout(new GridLayout(5,1));
		this.setPreferredSize(new Dimension((int)(VentanaPrincipal.ANCHO/3.5),(int)(VentanaPrincipal.ALTO/2)));
		JLabel informacion = new JLabel("<html><center>En el primer campo ingrese la entrada de acuerdo al formato del enunciado"
				+ "<br/>En el segundo campo podra visualizar la respuesta o salida esperada</center></html>",SwingConstants.CENTER);
		input = new JTextArea();
		output = new JTextArea();
		int upDown = 3;
		int leftRight = 10;
		input.setBorder(BorderFactory.createMatteBorder(upDown,leftRight,upDown,leftRight,vent.getBackground()));
		output.setBorder(BorderFactory.createMatteBorder(upDown,leftRight,upDown,leftRight,vent.getBackground()));
		JButton resolver = new JButton(RESOLVER);
		JButton borrar = new JButton(BORRAR);
		JButton grafica = new JButton(GRAFICA);
		resolver.addActionListener(this);
		borrar.addActionListener(this);
		grafica.addActionListener(this);
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
	    JComboBox<String> tipoAlg = new JComboBox<String>();
	    JComboBox<String> versionGrafo = new JComboBox<String>();
	    aux2.add(tipoAlg);
	    aux2.add(versionGrafo);
	    aux2.setBorder(BorderFactory.createMatteBorder(10,50,10,50,vent.getBackground()));
	    DefaultComboBoxModel<String> tipoAlgData = new DefaultComboBoxModel<String>(new String[] {"Tipo de Algoritmo","Bellman Ford","Dijkstra"});
	    DefaultComboBoxModel<String> versionGrafoData = new DefaultComboBoxModel<String>(new String[] {"Versión del grafo","Lista de adyacencias","Matriz de adyacencias"});
	    tipoAlg.setModel(tipoAlgData);
	    versionGrafo.setModel(versionGrafoData);
	    infTiempo = new JLabel("Tiempo que tardo el algoritmo en encontrar la solución: NA");
	    infTiempo.setHorizontalAlignment(SwingConstants.CENTER);
	    aux2.add(infTiempo);
	    add(aux2);
	    aux.add(borrar);
	    aux.add(grafica);
	    aux.add(resolver);
	    aux.setBorder(BorderFactory.createMatteBorder(10,10,10,10,vent.getBackground()));
	    add(aux);
	}
	
	public void cambiarTiempo(int tiempo) {
		infTiempo.setText("Tiempo que tardo el algoritmo en encontrar la solución: "+tiempo+" ns");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(BORRAR)) {
			input.setText("");
			output.setText("");
			infTiempo.setText("Tiempo que tardo el algoritmo en encontrar la solución: NA");
		} else if (str.equals(RESOLVER)) {
			
		} else if (str.equals(GRAFICA)) {
			vent.abrirJDialogGrafica(0,new int[][][] {});
		}
	}
	
}
