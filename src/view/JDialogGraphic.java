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
public class JDialogGraphic extends JDialog implements ActionListener,WindowListener {
	
	public static final String CLOSE = "Cerrar ventana";
	private int numberVertexes;
	private int[][][] edges;
	private JPanel canvas;
	private MainWindow main;
	
	public JDialogGraphic(int numberVertexes, int[][][] edges, MainWindow main) {
		super(main);
		this.numberVertexes = numberVertexes;
		this.edges = edges;
		this.main = main;
		this.addWindowListener(this);
		init();
		pintarGrafo();
	}
	
	private void init() {
		canvas = new JPanel(new GridLayout(1,1));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.HEIGHT));
		setResizable(false);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(((int)(dm.getWidth()/2)-(MainWindow.WIDTH/2)),((int)(dm.getHeight()/2))-(MainWindow.HEIGHT/2));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Gráfica auxiliar de los sistemas de estrellas que la cientifica debe realizar");
		JPanel aux = new JPanel(new BorderLayout());
		aux.setBorder(BorderFactory.createMatteBorder(10,0,10,5,MainWindow.BACKGROUND));
		canvas.setBorder(BorderFactory.createMatteBorder(10,10,10,10,MainWindow.BACKGROUND));
		JButton cerrar = new JButton(CLOSE);
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
		if (str.equals(CLOSE)) {
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
		main.setVisible(true);
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
