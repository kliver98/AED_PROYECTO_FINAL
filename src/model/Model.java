package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import solutions.SolutionGraphMatrix.GraphBellmanMatrix;

public class Model {

	public Model() {
		
	}
	
	public List<String> convertInput(String convert) {
		try {			
			ArrayList<String> data = new ArrayList<String>();
			String[] s = convert.split("\n");
			int cont = Integer.parseInt(s[0].trim()), aux = 1;
			for (int i = 0; i < s.length; i++) {
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
	
	public String convertFileToString(String path) {
		try {
			FileReader reader = new FileReader(new File(path));
			BufferedReader br = new BufferedReader(reader);
			StringBuffer convert = new StringBuffer();
			String line = "";
			while((line = br.readLine()) != null) //Se leen las lineas hasta el final del documento
				convert.append(line+"\n");
			br.close();
			return convert.toString();
		} catch(Exception e) {
			return null;
		}
	}
	
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
	
	public String[] solveByBellmanList(String data) {
		List<String> d = convertInput(data);
		String[] rst = new String[2];
		if (d==null)
			return new String[] {"-1",""};
		long ini = System.nanoTime();
		//Escribir lo que se le pasa a los algoritmos, tal cual una entrada, los datos de
		//entrada estan en la lista tal cual como debe ser la entras (1 linea casos,
		//2 linea empiza caso 1, 3 linea ini, dest y peso, y así...
		//Ver ejemplo de como hice solveByBellmanMatrix
		rst[0] = (System.nanoTime()-ini)+"";
		return rst;
	}
	
	public String[] solveByDijkstraMatrix(String data) {
		List<String> d = convertInput(data);
		String[] rst = new String[2];
		if (d==null)
			return new String[] {"-1",""};
		long ini = System.nanoTime();
		
		rst[0] = (System.nanoTime()-ini)+"";
		return rst;
	}
	
	public String[] solveByDijkstraList(String data) {
		List<String> d = convertInput(data);
		String[] rst = new String[2];
		if (d==null)
			return new String[] {"-1",""};
		long ini = System.nanoTime();
		
		rst[0] = (System.nanoTime()-ini)+"";
		return rst;
	}
	
}
