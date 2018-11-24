package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelArchivo extends JPanel implements ActionListener {

	public static final String BUSCAR = "BUSCAR";
	private VentanaPrincipal vent;
	private JFileChooser fileChooser;

	public PanelArchivo(VentanaPrincipal vent) {
		super();
		this.vent = vent;
		this.setBorder(BorderFactory.createMatteBorder(10,5,5,10,VentanaPrincipal.COLOR_FONDO));
		init();
	}
	
	private void init() {
		this.setLayout(new GridLayout(3,1));
		this.setPreferredSize(new Dimension((int)(VentanaPrincipal.ANCHO/3.5),(int)(VentanaPrincipal.ALTO/2)));
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Únicamente archivos de texto","txt","in"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		JLabel informacion = new JLabel("Cargar archivo de entrada");
		informacion.setHorizontalAlignment(SwingConstants.CENTER);
		JButton buscar = new JButton(BUSCAR);
		buscar.addActionListener(this);
		buscar.setBorder(BorderFactory.createMatteBorder(5,10,5,10,getBackground()));
		JLabel archivoSeleccionado = new JLabel("Ningún archivo seleccionado aún");
		archivoSeleccionado.setHorizontalAlignment(SwingConstants.CENTER);
		add(informacion);
		add(buscar);
		add(archivoSeleccionado);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(BUSCAR)) {
			int res = fileChooser.showOpenDialog(this);
			if (res == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
			}
		}
	}
	
}
