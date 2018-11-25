package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PanelPrincipal extends JPanel implements ActionListener {

	public static final String RESOLVER = "Resolver";
	public static final String BORRAR = "Borrar";
	public static final String GRAFICA = "Gráfica";
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
	    infTiempo = new JLabel("Tiempo que tardo el algoritmo en encontrar la solución: NA");
	    infTiempo.setHorizontalAlignment(SwingConstants.CENTER);
	    add(infTiempo);
	    JPanel aux = new JPanel(new GridLayout(1,3));
	    aux.add(borrar);
	    aux.add(grafica);
	    aux.add(resolver);
	    aux.setBorder(BorderFactory.createMatteBorder(10,10,10,10,vent.getBackground()));
	    add(aux);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(BORRAR)) {
			
		} else if (str.equals(RESOLVER)) {
			
		} else if (str.equals(GRAFICA)) {
			
		}
	}
	
}
