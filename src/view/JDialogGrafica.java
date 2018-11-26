package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JDialogGrafica extends JDialog implements ActionListener,WindowListener {
	
	public static final String CERRAR = "Cerrar ventana";
	private int cantidadVertices;
	private int[][][] aristas;
	private JPanel canvas;
	private VentanaPrincipal vent;
	
	public JDialogGrafica(int cantidadVertices, int[][][] aristas, VentanaPrincipal vent) {
		super(vent);
		this.cantidadVertices = cantidadVertices;
		this.aristas = aristas;
		this.vent = vent;
		this.addWindowListener(this);
		init();
		pintarGrafo();
	}
	
	private void init() {
		canvas = new JPanel(new GridLayout(1,1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(VentanaPrincipal.ANCHO,VentanaPrincipal.ALTO));
		setResizable(false);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)(dm.getWidth()/2)-(VentanaPrincipal.ANCHO/2)),((int)(dm.getHeight()/2))-(VentanaPrincipal.ALTO/2));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Gráfica auxiliar de los sistemas de estrellas que la cientifica debe realizar");
		JPanel aux = new JPanel(new BorderLayout());
		aux.setBorder(BorderFactory.createMatteBorder(10,0,10,5,VentanaPrincipal.COLOR_FONDO));
		canvas.setBorder(BorderFactory.createMatteBorder(10,10,10,10,VentanaPrincipal.COLOR_FONDO));
		JButton cerrar = new JButton(CERRAR);
		cerrar.addActionListener(this);
		aux.add(cerrar,BorderLayout.SOUTH);
		add(aux,BorderLayout.EAST);
		add(canvas,BorderLayout.CENTER);
		setVisible(true);
		pack();
	}
	
	public void pintarGrafo() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(CERRAR)) {
			this.dispose();
			windowClosing(null);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		vent.setVisible(true);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
