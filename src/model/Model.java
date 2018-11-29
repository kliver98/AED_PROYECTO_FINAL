package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import solutions.SolutionGraphList.GraphDijkstraList;
import solutions.SolutionGraphMatrix.GraphBellmanMatrix;
import structures.GraphMatrix;
import test.CasesGenerator;

public class Model {

	/**
	 * Construye un Model
	 */
	public Model() {
		
	}
	
	/**
	 * Método que devuelve la conversión de un String a una Lista con los datos del String<br>
	 * @param convert String para convertir en lista<br>
	 * @return lista con datos del parametro recibido<br>
	 */
	public List<String> convertInput(String convert) {
		try {			
			ArrayList<String> data = new ArrayList<String>();
			String[] s = convert.split("\n");
			int cont = Integer.parseInt(s[0].trim()), aux = 1;
			for (int i = 0; i < s.length; i++) {
				if (aux>0 && i>0 && s[i].split(" ").length!=3)
					return null;
				if (aux==0 && i>0) {
					aux = Integer.parseInt(s[i].split(" ")[1])+1;
					cont--;
				}
				data.add(s[i]);
				aux--;
			}
			if (aux==0 && cont==0)
				return data;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Método que devuelve la conversión de un archivo es un string con los datos<br>
	 * @param path del archivo donde esta en el equipo<br>
	 * @return conversión del archivo a String<br>
	 */
	public String convertFileToString(String path) {
		try {
			FileReader reader = new FileReader(new File(path));
			BufferedReader br = new BufferedReader(reader);
			StringBuffer convert = new StringBuffer();
			String line = "";
			while((line = br.readLine()) != null)
				convert.append(line+"\n");
			br.close();
			return convert.toString();
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Método que devuelve las aristas del primer caso de entrada que se halla introducido<br>
	 * @param data de las entradas que halla actualmente<br>
	 * @return arreglo bidimensional con las aristas del primer caso de entrada introducido<br>
	 */
	public int[][] getFirstEdges(String data) {
		List<String> d = convertInput(data);
		if (d==null)
			return null;
		int e = Integer.parseInt(d.get(1).split(" ")[1]);
		int[][] edges = new int[e+1][3];
		edges[0][0] = Integer.parseInt(d.get(1).split(" ")[0]); //Número de Vertices
		for (int j = 1; j <= e; j++) {
			String[] line = d.get(j+1).split(" ");
			for (int k = 0; k < edges[j].length; k++) {
				edges[j][k] = Integer.parseInt(line[k].trim());
			}
		}
		return edges;
	}
	
	/**
	 * Metodo que devuelve las aristas que llevan hacia el ciclo infinito
	 * @param data de las entradas que halla actualmente<br>
	 * @return arreglo bidimensional con las aristas del primer caso de entrada introducido<br>
	 */
	public int[][] getCycleNegative(String data){
		String firstGraph = firstInput(data);
		String[] rst = solveByBellmanMatrix(firstGraph);
		int rs[][] = null;
		if (!rst[0].equals("-1")) {
			if (rst[1].equals("possible\n")) {
				List<String> d = convertInput(data);
				int v = Integer.parseInt(d.get(1).split(" ")[0]);
				int e = Integer.parseInt(d.get(1).split(" ")[1]);
				GraphMatrix<Integer, String> graphMatrix = new GraphMatrix<>(v, true);
				
				for (int i = 0; i < e; i++) {
					int src = Integer.parseInt(d.get(2+i).split(" ")[0]);
					int dst = Integer.parseInt(d.get(2+i).split(" ")[1]);
					int w = Integer.parseInt(d.get(2+i).split(" ")[2]);
					
					graphMatrix.addEdge(src, dst, w);
				}
				
				graphMatrix.BellmanFord(0);
				
				rs = new int[graphMatrix.getCycle().size()][3];
				
				for (int i = 0; i < graphMatrix.getCycle().size(); i++) {
					rs[i][0] = graphMatrix.getCycle().get(i).getStart().getData();
					rs[i][1] = graphMatrix.getCycle().get(i).getEnd().getData();
					rs[i][2] = (int) graphMatrix.getCycle().get(i).getWeight();
				}
			}
		}
		return rs;
	}
	
	
	/**
	 * Metodo que se encarga de obtener el primer grafo de las entradas
	 * @param data de las entradas que halla actualmente<br>
	 * @return 
	 */
	public String firstInput(String data) {
		String[] arr = data.split("\n");
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("1\n");
		int v = Integer.parseInt(arr[1].split(" ")[0]);
		int e = Integer.parseInt(arr[1].split(" ")[1]);
		
		sBuffer.append(v+" "+e+"\n");
		for (int i = 0; i < e; i++) 
			sBuffer.append(arr[2+i]+"\n");
		
		return sBuffer.toString();
	}
	
	/**
	 * Método que da solución a una entrada mediante el algoritmo de Bellman Ford y este implementado con Matriz de adyacencias<br>
	 * @param data de las entrada que halla actualmente<br>
	 */
	public String[] solveByBellmanMatrix(String data) {
		List<String> d = convertInput(data);
		String[] rst = new String[2];
		StringBuffer sb = new StringBuffer();
		if (d==null)
			return new String[] {"-1",""};
		long ini = System.nanoTime();
		int c = Integer.parseInt(d.get(0).trim()), i = 0;
		while (c-->0) {
			i++;
			String[] line = d.get(i).split(" ");
			int V = Integer.parseInt(line[0]), E = Integer.parseInt(line[1]);
			GraphBellmanMatrix<Integer, String> GraphBellmanMatrix = new GraphBellmanMatrix<>(V, true);
			while (E-->0) {
				i++;
				line = d.get(i).split(" ");
				
				int s = Integer.parseInt(line[0]);
				int e = Integer.parseInt(line[1]);
				int w = Integer.parseInt(line[2]);
				
				GraphBellmanMatrix.addEdge(s, e, w);
			}
			sb.append((GraphBellmanMatrix.BellmanFord(0)?"possible\n":"not possible\n"));
		}
		rst[0] = (System.nanoTime()-ini)+"";
		rst[1] = sb.toString();
		return rst;
	}
	
	/**
	 * Método que da solución a una entrada mediante el algoritmo de Dijkstra y este implementado con Lista de adyacencias<br>
	 * @param data de las entrada que halla actualmente<br>
	 */
	public String[] solveByDijkstraList(String data) {
		List<String> d = convertInput(data);
		String[] rst = new String[2];
		StringBuffer sb = new StringBuffer();
		if (d==null)
			return new String[] {"-1",""};
		long ini = System.nanoTime();
		int c = Integer.parseInt(d.get(0).trim()), i = 0;
		while (c-->0) {
			i++;
			String[] line = d.get(i).split(" ");
			int E = Integer.parseInt(line[1]);
			GraphDijkstraList<Integer, String> GraphDijkstraList = new GraphDijkstraList<>(true);
			while (E-->0) {
				i++;
				line = d.get(i).split(" ");
				
				int s = Integer.parseInt(line[0]);
				int e = Integer.parseInt(line[1]);
				int w = Integer.parseInt(line[2]);
				
				GraphDijkstraList.addEdge(s, e, w);
			}
			sb.append((GraphDijkstraList.Dijkstra(0)?"possible\n":"not possible\n"));
		}
		rst[0] = (System.nanoTime()-ini)+"";
		rst[1] = sb.toString();
		return rst;
	}
	
	public boolean generateTestCases(String name) {
		try {
			return CasesGenerator.generator(name);
		} catch (IOException e) {
			return false;
		}
	}
	
}
