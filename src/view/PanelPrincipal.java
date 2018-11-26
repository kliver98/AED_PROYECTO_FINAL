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
	public static final String[] SOLUTION_TYPE = {"Seleccione tipo de solución","Bellman Ford con Matriz de adyacencias","Dijkstra con Lista de adyacencias"};
	private JTextArea input;
	private JTextArea output;
	private JLabel infTime;
	private JComboBox<String> solutionType;
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
	    JPanel aux2 =new JPanel(new GridLayout(2,1));
	    aux2.setBorder(BorderFactory.createMatteBorder(10,50,10,50,main.getBackground()));
	    DefaultComboBoxModel<String> solutionType = new DefaultComboBoxModel<String>(SOLUTION_TYPE);
	    this.solutionType = new JComboBox<String>(solutionType);
	    infTime = new JLabel("Tiempo que tardo el algoritmo en encontrar la solución: NA");
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
			infTime.setForeground(Color.BLACK);
			infTime.setText("Tiempo que tardo el algoritmo en encontrar la solución: NA");
		} else if (str.equals(SOLVE)) {
			if (input.getText().isEmpty()) {
				error("ENTRADA VACÍA");
				return;
			}
			if (!SOLUTION_TYPE[solutionType.getSelectedIndex()].equals(SOLUTION_TYPE[0])) {
				boolean BM = SOLUTION_TYPE[1].equals(SOLUTION_TYPE[solutionType.getSelectedIndex()]);
				boolean DL = SOLUTION_TYPE[2].equals(SOLUTION_TYPE[solutionType.getSelectedIndex()]);
				if (BM)
					main.solveByBellmanMatrix(input.getText());
				else if (DL)
					main.solveByDijkstraList(input.getText());
			} else
				error("SELECCIONE TIPO DE SOLUCIÓN");
		} else if (str.equals(GRAPHIC)) {
			main.openJDialogGraphic(0,new int[][][] {});
		}
	}
	
}
