package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;

import structures.*;

public class testGraph {

//    private GraphList<String, String> relacionAGrafo;
//    private GraphMatrix<String, String> relacionAGraMatriz;
    private GraphList<Integer, String> relacionAGrafo;
    private GraphMatrix<Integer, String> relacionAGraMatriz;
    
    //Agregando aristas
	private void setupEscenario1(){
	
		relacionAGrafo = new GraphList<>(true);
		//Arista sin Ruta pero con peso
		relacionAGrafo.addEdge(0, 1, 1000, "Arista1");
		relacionAGrafo.addEdge(1, 2, 15, "Arista2");
		relacionAGrafo.addEdge(2, 1, -42, "Arista3");
		
	}
	//Agregando Vértices
	private void setupEscenario2(){
		
		relacionAGrafo = new GraphList<>();
		
		relacionAGrafo.addVertex(0);
		relacionAGrafo.addVertex(1);		
	}
	
	//Dijsktra
	private void setupEscenario3(){	
		
		relacionAGrafo =new GraphList<>(true);
	
		//Agregando Vértices
		relacionAGrafo.addVertex(0);
		relacionAGrafo.addVertex(1);
		relacionAGrafo.addVertex(2);
		relacionAGrafo.addVertex(3);
			
		//Agregando Aristas con los vértices
		relacionAGrafo.addEdge(0, 1, 10);
		relacionAGrafo.addEdge(1, 2, 20);
		relacionAGrafo.addEdge(2, 3, 30);
		relacionAGrafo.addEdge(3, 0, -60);
	}
	
	//Número de vértices
	private void setupEscenario4(){
		relacionAGrafo= new GraphList<>();
		
		relacionAGrafo.addVertex(0);
		relacionAGrafo.addVertex(1);
		relacionAGrafo.addVertex(2);
		relacionAGrafo.addVertex(3);
	}
	
	//Recorrido con BFS
	private void setupEscenario5(){
		relacionAGrafo=new GraphList<>();
		
		//Agregando Vértices
		relacionAGrafo.addVertex(0);
		relacionAGrafo.addVertex(1);
		relacionAGrafo.addVertex(2);
		relacionAGrafo.addVertex(3);
		relacionAGrafo.addVertex(4);
		relacionAGrafo.addVertex(5);
		relacionAGrafo.addVertex(6);
					
		//Agregando Aristas con los vértices
		relacionAGrafo.addEdge(0, 1, 110, "Arista1");
		relacionAGrafo.addEdge(0, 2, 40, "Arista2");
		relacionAGrafo.addEdge(0, 3, 50, "Arista3");
		relacionAGrafo.addEdge(0, 4, 35, "Arista4");
		relacionAGrafo.addEdge(1, 6, 25, "Arista5");
		relacionAGrafo.addEdge(4, 6, 13, "Arista6");
		relacionAGrafo.addEdge(6, 5, 100, "Arista9");
	}
	
	//Recorrido con DFS
	private void setupEscenario6(){
		relacionAGrafo=new GraphList<>();
		
		//Agregando Vértices
		relacionAGrafo.addVertex(0);
		relacionAGrafo.addVertex(1);
		relacionAGrafo.addVertex(2);
		relacionAGrafo.addVertex(3);
		relacionAGrafo.addVertex(4);
		relacionAGrafo.addVertex(5);
		relacionAGrafo.addVertex(6);
					
		//Agregando Aristas con los vértices
		relacionAGrafo.addEdge(0, 1, 110, "Arista1");
		relacionAGrafo.addEdge(0, 2, 40, "Arista2");
		relacionAGrafo.addEdge(0, 3, 50, "Arista3");
		relacionAGrafo.addEdge(0, 4, 35, "Arista4");
		relacionAGrafo.addEdge(1, 6, 25, "Arista5");
		relacionAGrafo.addEdge(4, 6, 13, "Arista6");
		relacionAGrafo.addEdge(6, 5, 100, "Arista9");
	}
	
	//FLOYD-WARSHALL
	private void setupEscenario7(){

		relacionAGraMatriz = new GraphMatrix<>(5);
		
		//Agregando Vertices
		relacionAGraMatriz.addVertex(0);
		relacionAGraMatriz.addVertex(1);
		relacionAGraMatriz.addVertex(2);
		relacionAGraMatriz.addVertex(3);
		relacionAGraMatriz.addVertex(4);
									
		//Agregando Aristas con los vertices
		relacionAGraMatriz.addEdge(0, 1, 110, "Arista1");
		relacionAGraMatriz.addEdge(0, 2, 40, "Arista2");
		relacionAGraMatriz.addEdge(0, 3, 50, "Arista3");
		relacionAGraMatriz.addEdge(0, 4, 35, "Arista4");
		relacionAGraMatriz.addEdge(4, 1, 15, "Arista5");
	}
	
	//Es o no grafo dirijido, IsDirected()
	private void setupEscenario8(){
		relacionAGrafo= new GraphList<>();		
		
		//Agregando Vértices
		relacionAGrafo.addVertex(0);
		relacionAGrafo.addVertex(1);
		relacionAGrafo.addVertex(2);
		relacionAGrafo.addVertex(3);
		relacionAGrafo.addVertex(4);
		relacionAGrafo.addVertex(5);
		relacionAGrafo.addVertex(6);
			
		//Agregando Aristas con los vértices
		relacionAGrafo.addEdge(0, 1, 117, "arista1");
		relacionAGrafo.addEdge(1, 2, 40, "Arista2");
		relacionAGrafo.addEdge(1, 3, 50, "Arista3");
		relacionAGrafo.addEdge(2, 3, 35, "Arista4");
		relacionAGrafo.addEdge(3, 4, 25, "Arista5");
		relacionAGrafo.addEdge(4, 5, 13, "Arista6");
		relacionAGrafo.addEdge(4, 6, 17, "Arista7");
		
	}
	
	@Test
	public void testAgregandoAristas(){
		setupEscenario1();
		
		ArrayList<Edge<Integer, String>> list = new ArrayList<>();
		HashMap<Integer, Edge<Integer, String>> map = new HashMap<>();
		
		for (Map.Entry<Vertex<Integer>, TreeMap<Vertex<Integer>, TreeSet<Edge<Integer, String>>>>  entry : relacionAGrafo.getAdjacencyList().entrySet()) 
			for (Map.Entry<Vertex<Integer>, TreeSet<Edge<Integer, String>>> entry2: entry.getValue().entrySet()) 
				list.addAll(entry2.getValue());
		
		for (Edge<Integer, String> edge : list) 
			map.put(edge.getStart().getData(), edge);
		
		assertEquals(1000, map.get(0).getWeight());
		assertEquals("Arista1", map.get(0).getName());
		
		assertEquals(15, map.get(1).getWeight());
		assertEquals("Arista2", map.get(1).getName());
		
		assertEquals(-42, map.get(2).getWeight());
		assertEquals("Arista3", map.get(2).getName());
	}
	
	@Test
	public void testAgregandoVertices(){
		setupEscenario2();
		
		assertTrue(2 == relacionAGrafo.getNumeroVertices());
		relacionAGrafo.addVertex(2);
		relacionAGrafo.addVertex(2);
		
		assertTrue(3 == relacionAGrafo.getNumeroVertices());
		relacionAGrafo.addVertex(3);
		assertTrue(4==relacionAGrafo.getNumeroVertices());
	}
	
	@Test
	public void testDijkstra(){	
		setupEscenario3();
		
		assertFalse(relacionAGrafo.Dijkstra(0));
		
		setupEscenario1();
		assertTrue(relacionAGrafo.Dijkstra(0));
	}
	
	@Test
	public void testObtenerElNumeroDeVertices(){
		setupEscenario4();		
		assertTrue((4==relacionAGrafo.getNumeroVertices()));

		relacionAGrafo.addVertex(4);
		relacionAGrafo.addVertex(5);
		assertTrue((6==relacionAGrafo.getNumeroVertices()));
		assertEquals(6, relacionAGrafo.getNumeroVertices());
	}
	
	@Test
	public void testRecorridoBFS(){
		 setupEscenario5();
	
	//Lista de recorrido del grafo en forma de BFS.
		 Integer[] arregloBFS={0, 1, 2, 3, 4, 6, 5};
		 
		 assertEquals(arregloBFS[0], relacionAGrafo.BreadthFirstSearch(0).get(0)) ;
		 assertEquals(arregloBFS[1], relacionAGrafo.BreadthFirstSearch(0).get(1));
		 assertEquals(arregloBFS[2], relacionAGrafo.BreadthFirstSearch(0).get(2));
		 assertEquals(arregloBFS[3], relacionAGrafo.BreadthFirstSearch(0).get(3));
		 assertEquals(arregloBFS[4], relacionAGrafo.BreadthFirstSearch(0).get(4));

		 for(int i=0; i<relacionAGrafo.BreadthFirstSearch(0).size(); i++){
			 assertEquals(arregloBFS[i], relacionAGrafo.BreadthFirstSearch(0).get(i));
		 }
		 
	}
	
	@Test
	public void testRecorridoDFS(){
		setupEscenario6();
		//Lista de recorrido del grafo en forma de DFS
		Integer[] arregloDFS={0, 4, 6, 5, 1, 3, 2};
		
		for(int i=0; i<relacionAGrafo.BreadthFirstSearch(0).size(); i++){
			 assertEquals(arregloDFS[i], relacionAGrafo.DepthFirstSearch(0).get(i));
		 }
	}

	@Test
	public void testFloydWarshall(){
		setupEscenario7();

		relacionAGraMatriz.ejecutarFloydWarshall();
		assertEquals(35, (relacionAGraMatriz.queryFloydWarshall(0, 4).get(0).getWeight()));
		assertEquals(15, (relacionAGraMatriz.queryFloydWarshall(1, 4).get(0).getWeight()));
		assertEquals(40, (relacionAGraMatriz.queryFloydWarshall(0, 2).get(0).getWeight()));
		assertEquals(50, (relacionAGraMatriz.queryFloydWarshall(0, 3).get(0).getWeight()));
		assertEquals(15, (relacionAGraMatriz.queryFloydWarshall(4, 1).get(0).getWeight()));
		assertEquals(15, (relacionAGraMatriz.queryFloydWarshall(1, 4).get(0).getWeight()));
		
		assertEquals("Arista5", relacionAGraMatriz.queryFloydWarshall(0, 1).get(1).getName());
		assertEquals("Arista4", relacionAGraMatriz.queryFloydWarshall(0, 1).get(0).getName());
		assertEquals(50, relacionAGraMatriz.queryFloydWarshall(1, 0).get(0).getWeight()+ (relacionAGraMatriz.queryFloydWarshall(1, 0).get(1).getWeight()));
		assertEquals(50, relacionAGraMatriz.queryFloydWarshall(0, 1).get(0).getWeight()+ (relacionAGraMatriz.queryFloydWarshall(0, 1).get(1).getWeight()));

		assertEquals(90, (relacionAGraMatriz.queryFloydWarshall(1, 2).get(0).getWeight())+ (relacionAGraMatriz.queryFloydWarshall(1, 2).get(1).getWeight()) +(relacionAGraMatriz.queryFloydWarshall(1, 2).get(2).getWeight()));

		assertEquals(100, relacionAGraMatriz.queryFloydWarshall(1, 3).get(0).getWeight()+ (relacionAGraMatriz.queryFloydWarshall(1, 3).get(1).getWeight())+ (relacionAGraMatriz.queryFloydWarshall(1, 3).get(2).getWeight()));
		assertEquals("Arista5", relacionAGraMatriz.queryFloydWarshall(1, 3).get(0).getName());
		assertEquals("Arista4", relacionAGraMatriz.queryFloydWarshall(1, 3).get(1).getName());
		assertEquals("Arista3", relacionAGraMatriz.queryFloydWarshall(1, 3).get(2).getName());	
	}
	
	@Test
	public void testGrafoDirigido(){
		setupEscenario8();
		assertFalse(relacionAGrafo.isDirected());
	}
}
