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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelGenerate extends JPanel implements ActionListener {

	/**
	 * Constante que representa el botón de generar casos de prueba<br>
	 */
	public static final String GENERATE = "Generar";
	/**
	 * Atributo que representa el JTextField del nombre del arhcivo a generar<br>
	 */
	private JTextField file_name;
	/**
	 * Relación con la ventana principal<br>
	 */
	private MainWindow main;

	/**
	 * Construye un PanelGenerate<br>
	 * @param main con la referencia de la ventana principal<br>
	 */
	public PanelGenerate(MainWindow main) {
		super();
		this.main = main;
		init();
	}
	
	/**
	 * Método que inicializa las propiedades de la ventana principal<br>
	 */
	private void init() {
		this.setBorder(BorderFactory.createMatteBorder(5,5,10,10,MainWindow.BACKGROUND));
		this.setLayout(new GridLayout(3,1)); //4 con cantidad
		this.setPreferredSize(new Dimension((int)(MainWindow.WIDTH/3.5),(int)(MainWindow.HEIGHT/2)));
		JLabel informacion = new JLabel("Generar casos de prueba");
		informacion.setHorizontalAlignment(SwingConstants.CENTER);
		JButton generar = new JButton(GENERATE);
		generar.addActionListener(this);
		generar.setBorder(BorderFactory.createMatteBorder(5,10,5,10,getBackground()));
//		JTextField cantidad = new JTextField();
		file_name = new JTextField();
//		TextPrompt tp1 = new TextPrompt("Cantidad",cantidad);
//		tp1.changeAlpha(0.75f);
//	    tp1.changeStyle(Font.PLAIN);
	    TextPrompt tp2 = new TextPrompt("Nombre del archivo",file_name);
		tp2.changeAlpha(0.75f);
	    tp2.changeStyle(Font.PLAIN);
//		cantidad.setBorder(generar.getBorder());
	    file_name.setBorder(generar.getBorder());
		add(informacion);
//		add(cantidad);
		add(file_name);
		add(generar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(GENERATE)) {
			String name = file_name.getText().isEmpty() ? System.currentTimeMillis()+"":file_name.getText();
			main.generateTestCases(name);
		}
	}
	
}
