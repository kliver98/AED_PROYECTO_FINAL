package view;

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

@SuppressWarnings("serial")
public class PanelFile extends JPanel implements ActionListener {

	public static final String SEARCH = "BUSCAR";
	private MainWindow main;
	private JFileChooser fileChooser;

	public PanelFile(MainWindow main) {
		super();
		this.main = main;
		init();
	}
	
	private void init() {
		this.setBorder(BorderFactory.createMatteBorder(10,5,5,10,MainWindow.BACKGROUND));
		this.setLayout(new GridLayout(3,1));
		this.setPreferredSize(new Dimension((int)(MainWindow.WIDTH/3.5),(int)(MainWindow.HEIGHT/2)));
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto","txt","in"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		JLabel informacion = new JLabel("Cargar archivo de entrada");
		informacion.setHorizontalAlignment(SwingConstants.CENTER);
		JButton buscar = new JButton(SEARCH);
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
		if (str.equals(SEARCH)) {
			int res = fileChooser.showOpenDialog(this);
			if (res == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
			}
		}
	}
	
}
