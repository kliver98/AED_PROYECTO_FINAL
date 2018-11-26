package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Model {

	public Model() {
		
	}
	
	public List<String> convertInput(String convert) throws Exception {
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
			if (aux>0 && cont>0)
				return data;
			else
				return null;
		} catch(Exception e) {
			return null;
		}
	}
	
	public List<String> convertFileInput(File file) throws Exception {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			StringBuffer convert = new StringBuffer();
			String line = "";
			while((line = br.readLine()) != null){ //Se leen las lineas hasta el final del documento
				convert.append(line);
			}
			br.close();
			return convertInput(convert.toString());
		} catch(Exception e) {
			return null;
		}
	}
	
	public long solveByBellmanMatrix() {
		long ini = System.nanoTime();
		
		return System.nanoTime()-ini;
	}
	
	public long solveByBellmanList() {
		long ini = System.nanoTime();
		
		return System.nanoTime()-ini;
	}
	
	public long solveByDijkstraMatrix() {
		long ini = System.nanoTime();
		
		return System.nanoTime()-ini;
	}
	
	public long solveByDijkstraList() {
		long ini = System.nanoTime();
		
		return System.nanoTime()-ini;
	}
	
}
