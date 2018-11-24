package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelGenerar extends JPanel implements ActionListener {

	public static final String GENERAR = "Generar";
	private VentanaPrincipal vent;

	public PanelGenerar(VentanaPrincipal vent) {
		super();
		this.vent = vent;
		this.setBorder(BorderFactory.createMatteBorder(5,5,10,10,VentanaPrincipal.COLOR_FONDO));
		init();
	}
	
	private void init() {
		this.setLayout(new GridLayout(4,1));
		this.setPreferredSize(new Dimension((int)(VentanaPrincipal.ANCHO/3.5),(int)(VentanaPrincipal.ALTO/2)));
		JLabel informacion = new JLabel("Generar casos de prueba");
		informacion.setHorizontalAlignment(SwingConstants.CENTER);
		JButton generar = new JButton(GENERAR);
		generar.addActionListener(this);
		generar.setBorder(BorderFactory.createMatteBorder(5,10,5,10,getBackground()));
		JTextField cantidad = new JTextField();
		JTextField nombre_archivo = new JTextField();
		TextPrompt tp1 = new TextPrompt("Cantidad",cantidad);
		tp1.changeAlpha(0.75f);
	    tp1.changeStyle(Font.PLAIN);
	    TextPrompt tp2 = new TextPrompt("Nombre del archivo",nombre_archivo);
		tp2.changeAlpha(0.75f);
	    tp2.changeStyle(Font.PLAIN);
		cantidad.setBorder(generar.getBorder());
		nombre_archivo.setBorder(generar.getBorder());
		add(informacion);
		add(cantidad);
		add(nombre_archivo);
		add(generar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(GENERAR)) {
			System.out.println("Generar");
		}
	}
	
}
