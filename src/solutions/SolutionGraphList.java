package solutions;

import java.io.*;
import java.util.*;

public class SolutionGraphList {
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(in.readLine());
		
		while (t-->0) {
			String line[] = in.readLine().split(" ");
			
			int E = Integer.parseInt(line[1]);
			
			GraphDijkstraList<Integer, String> GraphDijkstraList = new GraphDijkstraList<>(true);
			
			
			while (E-->0) {
				line = in.readLine().split(" ");
				
				int s = Integer.parseInt(line[0]);
				int e = Integer.parseInt(line[1]);
				int w = Integer.parseInt(line[2]);
				
				GraphDijkstraList.addEdge(s, e, w);
				
//				graph.addEdge(s, e, w);
			}
			
			out.write((GraphDijkstraList.Dijkstra(0)?"possible\n":"not possible\n"));
		}
		
		in.close();
		out.close();
	}
	
	
	
	/**
	 */
	public static class GraphDijkstraList<V extends Comparable<V>, A extends Comparable<A>> {
		
		/**
		 * Lista de Adyacencia. Nota: Modelado como multigrafo (Se utiliza ArrayList
		 * / TreeSet y no se utiliza un atributo de último indice)
		 */
		private HashMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V, A>>>> adjacencyList;
		
		/**
		 * Conjunto de vértices
		 */
		private HashSet<Vertex<V>> vertexes;
		/**
		 * Atributo para determinar si el grafo es dirigido
		 */
		private boolean directed;
		
		/**
		 * Método constructor del grafo de listas adyacentes
		 */
		public GraphDijkstraList() {
			
			adjacencyList = new HashMap<>();
			vertexes = new HashSet<>();
			directed = false;
			
		}
		
		/**
		 * Método constructor con sobrecarga
		 * 
		 * @param directed
		 *            Parámetro de entrada que define si es un grafo dirigido o no
		 */
		public GraphDijkstraList(boolean directed) {
			
			adjacencyList = new HashMap<>();
			vertexes = new HashSet<>();
			this.directed = directed;
			
		}
		
		/**
		 * Método que dice si el grafo es dirigido o no
		 * 
		 * @return true si es dirigido o false en caso contrario
		 */
		public boolean isDirected() {
			return directed;
		}
		
		/**
		 * Método que da el número de vértices que tiene el grafo
		 * 
		 * @return Cantidad de vertices agregados
		 */
		public int getNumeroVertices() {
			return vertexes.size();
		}
		
		/**
		 * Método que agrega el vértice
		 * 
		 * @param object
		 *            : Valor del vértice
		 * @return True si el vértice fue agregado o false en caso contrario
		 */
		public boolean addVertex(V object) {
			
			boolean added = false;
			
			if (searchVertex(object) == null) {
				
				vertexes.add(new Vertex<V>(object));
				added = true;
				
			}
			return added;
		}
		
		/**
		 * Método que se encarga de agregar una arista, con peso, nombre o etiqueta
		 * de la arista y sus dos vertices
		 * 
		 * @param in
		 *            : Vértice inicial en la arista
		 * @param out
		 *            : Vértice final en la arista
		 * @param w
		 *            : Peso de la arista
		 * @param name
		 *            : Nombre o etiqueta de la arista
		 * @return True si la arista fue agregada con exito, false en caso contrario
		 */
		public boolean addEdge(V in, V out, long w, A name) {
			
			boolean added = false;
			
			addVertex(in);
			Vertex<V> start = searchVertex(in);
			
			addVertex(out);
			Vertex<V> end = searchVertex(out);
			
			Edge<V, A> edge = searchEdge(start, end, name);
			
			if (edge == null) {
				
				edge = new Edge<>(start, end, w, name);
				
				addEdge(edge);
				
				if (!directed) {
					
					edge = new Edge<>(end, start, w, name);
					
					addEdge(edge);
					
				}
				
				added = true;
				
			}
			
			return added;
			
		}
		
		/**
		 * Método que se encarga de agregar una arista, con su peso y sus vértices
		 * 
		 * @param in
		 *            : Vértice inicial en la arista
		 * @param out
		 *            : Vértice final en la arista
		 * @param w
		 *            : Peso propio de la arista
		 * @return True si la arista fue agregada con exito de acuerdo a sus
		 *         parametros y false en caso contraio
		 */
		public boolean addEdge(V in, V out, long w) {
			
			boolean added = false;
			
			addVertex(in);
			Vertex<V> start = searchVertex(in);
			
			addVertex(out);
			Vertex<V> end = searchVertex(out);
			
			Edge<V, A> edge = searchEdge(start, end, w);
			
			if (edge == null) {
				
				edge = new Edge<>(start, end, w);
				
				addEdge(edge);
				
				if (!directed) {
					
					edge = new Edge<>(end, start, w);
					
					addEdge(edge);
					
				}
				
				added = true;
				
			}
			
			return added;
			
		}
		
		/**
		 * Este método se encarga de agregar una arista con sus dos vértices y la
		 * etiqueta o nombre de la ruta
		 * 
		 * @param in
		 *            : Vértice inicial de la arista
		 * @param out
		 *            : Vértice final de la arista
		 * @param name
		 *            : Nombre o etiqueta de la arista
		 * @return True si la arista fue agregada con exito de acuerdo a sus
		 *         parametros y false en caso contraio
		 */
		public boolean addEdge(V in, V out, A name) {
			
			boolean added = false;
			
			addVertex(in);
			Vertex<V> start = searchVertex(in);
			
			addVertex(out);
			Vertex<V> end = searchVertex(out);
			
			Edge<V, A> edge = searchEdge(start, end, name);
			
			if (edge == null) {
				
				addEdge(edge);
				
				if (!directed) {
					
					edge = new Edge<>(end, start, name);
					
					addEdge(edge);
					
				}
				
				added = true;
				
			}
			
			return added;
			
		}
		
		/**
		 * Este método se encarga de agregar una arista con sus dos vértices
		 * respectivamente
		 * 
		 * @param in
		 *            : Vértice donde comienza la arista
		 * @param out
		 *            : Vértice donde termina la arista
		 * @return True si la arista fue agregada con exito de acuerdo a sus
		 *         parametros y false en caso contraio
		 */
		public boolean addEdge(V in, V out) {
			
			boolean added = false;
			
			addVertex(in);
			Vertex<V> start = searchVertex(in);
			
			addVertex(out);
			Vertex<V> end = searchVertex(out);
			
			Edge<V, A> edge = searchEdge(start, end);
			
			if (edge == null) {
				
				edge = new Edge<>(start, end);
				
				addEdge(edge);
				
				if (!directed) {
					
					edge = new Edge<>(end, start);
					
					addEdge(edge);
					
				}
				
				added = true;
				
			}
			
			return added;
			
		}
		
		/**
		 * Este método se encarga de buscar un vertice en el grafo
		 * 
		 * @param object
		 *            : Valor del vértice a buscar
		 * @return El vértice si lo encontró en el grafo, de lo contrario retorna
		 *         null.
		 */
		private Vertex<V> searchVertex(V object) {
			
			Vertex<V> sought = null;
			
			for (Vertex<V> vertex : vertexes) {
				if (vertex.getData().compareTo(object) == 0) {
					sought = vertex;
					break;
				}
			}
			
			return sought;
			
		}
		
		/**
		 * Método que se encarga de buscar una arista en la lista de adyacencias
		 * 
		 * @param start
		 *            : Vértice inicial por el cual empieza la arista
		 * @param end
		 *            : Vértice final en el cual termina la arista
		 * @return La arista buscada si existe en la lista de adyacencias de lo
		 *         contrario retorna null
		 */
		private Edge<V, A> searchEdge(Vertex<V> start, Vertex<V> end) {
			
			Edge<V, A> sought = null;
			
			if (adjacencyList.get(start) != null) {
				if (adjacencyList.get(start).get(end) != null) {
					for (Edge<V, A> ed : adjacencyList.get(start).get(end)) {
						sought = ed;
						break;
					}
				}
			}
			
			return sought;
			
		}
		
		/**
		 * Método que sirve para buscar una arista de acuerdo a sus dos vértices y
		 * el peso de la arista
		 * 
		 * @param start
		 *            : Vértice inicial de la arista
		 * @param end
		 *            : Vértice final de la arista
		 * @param w
		 *            : Peso de la arista de tipo long
		 * @return La arista si la encontro de lo contrario retorna null.
		 */
		private Edge<V, A> searchEdge(Vertex<V> start, Vertex<V> end, long w) {
			
			Edge<V, A> sought = null;
			
			if (adjacencyList.get(start) != null) {
				if (adjacencyList.get(start).get(end) != null) {
					for (Edge<V, A> ed : adjacencyList.get(start).get(end)) {
						if (ed.getWeight() == w) {
							sought = ed;
							break;
						}
					}
				}
			}
			
			return sought;
			
		}
		
		/**
		 * Este método se encarga de buscar una arista en la lista de adyacencias de
		 * acuerdo a sus dos vértices y la etiqueta de nombre o ruta de la arista
		 * 
		 * @param start
		 *            : Vérice inicial de la arista
		 * @param end
		 *            : Vértice final de la arista
		 * @param name
		 *            : Etiqueta o nombre de la arista
		 * @return La arista si la encontro en la lista de adyacencias de lo
		 *         contrario retorna null
		 */
		private Edge<V, A> searchEdge(Vertex<V> start, Vertex<V> end, A name) {
			
			Edge<V, A> sought = null;
			
			if (adjacencyList.get(start) != null) {
				if (adjacencyList.get(start).get(end) != null) {
					for (Edge<V, A> ed : adjacencyList.get(start).get(end)) {
						if (ed.getName().equals(name)) {
							sought = ed;
							break;
						}
					}
				}
			}
			
			return sought;
			
		}
		
		/**
		 * Método que se encarga de agregar una arista de acuerdo al parametro que
		 * le entra
		 * 
		 * @param aAgregar
		 *            : Arista que se desea agregar
		 */
		private void addEdge(Edge<V, A> aAgregar) {
			
			Vertex<V> start = aAgregar.getStart();
			
			Vertex<V> end = aAgregar.getEnd();
			
			if (adjacencyList.get(start) == null) {
				adjacencyList.put(start, new TreeMap<>());
			}
			
			if (adjacencyList.get(start).get(end) == null) {
				adjacencyList.get(start).put(end, new TreeSet<>());
			}
			
			adjacencyList.get(start).get(end).add(aAgregar);
			
		}
		
		/**
		 * Este método se encarga de recorrer el grafo mediante sus nodos adyacentes
		 * partiendo de un nodo inicial y usando una de Queue
		 * 
		 * @param start
		 *            : Nodo por donde se empieza a recorrer el grafo
		 * @return Una lista de los nodos del grafo recorridos en amplitud
		 */
		public List<V> BreadthFirstSearch(V start) {
			
			List<V> list = null;
			ArrayDeque<Vertex<V>> queue = new ArrayDeque<>();
			Vertex<V> current = searchVertex(start);
			
			if (current != null) {
				
				list = new ArrayList<>();
				
				HashSet<Vertex<V>> visitados = new HashSet<>();
				
				queue.add(current);
				
				while (!queue.isEmpty()) {
					
					current = queue.poll();
					
					if (!visitados.contains(current)) {
						visitados.add(current);
						list.add(current.getData());
						
						if (adjacencyList.get(current) != null) {
							for (Vertex<V> ver : adjacencyList.get(current).keySet()) {
								if (!visitados.contains(ver)) {
									queue.add(ver);
								}
							}
						}
					}
				}
			}
			
			return list;
			
		}
		
		/**
		 * Este método se encarga de recorrer el grafo mediante sus nodos en
		 * profundidad partiendo de un nodo inicial y usando una Stack
		 * 
		 * @param start
		 *            : Nodo inicial por donde se empieza a recorrer el grafo
		 * @return Una lista con los nodos del grafo recorridos en profundidad.
		 */
		public List<V> DepthFirstSearch(V start) {
			
			List<V> list = null;
			Stack<Vertex<V>> stack = new Stack<>();
			Vertex<V> current = searchVertex(start);
			
			if (current != null) {
				
				list = new ArrayList<>();
				
				HashSet<Vertex<V>> visitados = new HashSet<>();
				
				stack.add(current);
				
				while (!stack.isEmpty()) {
					
					current = stack.pop();
					
					if (!visitados.contains(current)) {
						visitados.add(current);
						list.add(current.getData());
						
						if (adjacencyList.get(current) != null) {
							for (Vertex<V> ver : adjacencyList.get(current).keySet()) {
								stack.add(ver);
							}
						}
					}
				}
			}
			
			return list;
			
		}
		
		/**
		 * Este metodo se debe en honor a Edsger Wybe Dijkstra y se encarga de
		 * encontrar el camino de menor costo o mas corto entre un par de nodos en
		 * el grafo o entre un nodo y todos los demas nodos del grafo.
		 * 
		 * @param start
		 *            : Nodo inicial, donde se empieza a hacer el Dijkstra
		 * @return Una lista de aristas que representa el menor camino entre dos vértices
		 */
		public boolean Dijkstra(V start) {

			int conta = 0;

			Vertex<V> inicio = searchVertex(start);

			if (inicio != null && adjacencyList.get(inicio) != null) {
				
				HashMap<V, Long> dist = new HashMap<>();

				for (Vertex<V> ver : vertexes) {
					dist.put(ver.getData(), (long) Integer.MAX_VALUE);
				}

				dist.replace(start, 0l);

				PriorityQueue<Edge<V, A>> cola = new PriorityQueue<>(new Comparator<Edge<V, A>>() {

					@Override
					public int compare(Edge<V, A> o1, Edge<V, A> o2) {
						return (int) Math.signum(o1.getWeight() - o2.getWeight());
					}

				});

				for (Vertex<V> v : adjacencyList.get(inicio).keySet()) {
					cola.addAll(adjacencyList.get(inicio).get(v));
				}

				while (!cola.isEmpty()) {

					Edge<V, A> actual = cola.poll();

					V u = actual.getStart().getData();
					V v = actual.getEnd().getData();
					long w = actual.getWeight();

					if (dist.get(u) != Integer.MAX_VALUE) {

						if (dist.get(u) + w < dist.get(v) && conta <= vertexes.size()) {
							conta++;
							dist.replace(v, dist.get(u) + w);

							if (adjacencyList.containsKey(actual.getEnd())) {
								for (Vertex<V> ver : adjacencyList.get(actual.getEnd()).keySet()) {
									cola.addAll(adjacencyList.get(actual.getEnd()).get(ver));
								}
							}
						}

					}

				}
			}
			return conta>vertexes.size();
		}
		
		/**
		 * Este método se encarga de devolver la lista de adyacencia.
		 * 
		 * @return La lista de adyacencia
		 */
		public HashMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V, A>>>> getAdjacencyList() {
			return adjacencyList;
		}
		
		/**
		 * Este método se encarga de reemplzar la lista actual con la que entra como
		 * parámetro
		 * 
		 * @param adjacencyList
		 *            : Nueva lista de adyacencia
		 */
		public void setAdjacencyList(HashMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V, A>>>> adjacencyList) {
			this.adjacencyList = adjacencyList;
		}
		
		/**
		 * Este método se encarga de obtener el conjunto de vértices del grafo
		 * 
		 * @return El conjunto de vertices del grafo
		 */
		public HashSet<Vertex<V>> getVertexes() {
			return vertexes;
		}
		
		/**
		 * Este método se encarga de modificar el conjunto de vértices del grafo.
		 * 
		 * @param vertexes
		 *            : Nuevo conjunto de vertices que entra como parametro
		 */
		public void setVertexes(HashSet<Vertex<V>> vertexes) {
			this.vertexes = vertexes;
		}
		
	}
	
	/**
	 */
	public static class Edge<V extends Comparable<V>, A extends Comparable<A>> implements Comparable<Edge<V, A>> {
		/**
		 * Relación al vértice inicial
		 */
		private Vertex<V> start;

		/**
		 * Relación al vértice final
		 */
		private Vertex<V> end;

		/**
		 * Atributo de tipo long que representa el peso de la arista
		 */
		private long weight;

		/**
		 * Atributo de tipo A generico que representa el nombre o valor de la arista
		 */
		private A name;

		/**
		 * Método constructor de la arista
		 * 
		 * @param start
		 *            : Parámetro que representa el vértice inicial
		 * @param end
		 *            : Parámetro que representa el vértice final
		 */
		public Edge(Vertex<V> start, Vertex<V> end) {
			this.setStart(start);
			this.setEnd(end);
			this.setWeight(1);
		}

		/**
		 * Método constructor con polimorfismo
		 * 
		 * @param start
		 *            : Parámetro que representa el vértice inicial de la arista
		 * @param end
		 *            : Parámetro que representa el vértice final de la arista
		 * @param weight
		 *            : Parámetro que representa el peso de la arista que uno a los
		 *            dos vertices
		 */
		public Edge(Vertex<V> start, Vertex<V> end, long weight) {
			this.setStart(start);
			this.setEnd(end);
			this.setWeight(weight);
		}

		/**
		 * Método constructor con polimorfismo
		 * 
		 * @param start
		 *            : Parámetro que representa el vértice inicial de la arista
		 * @param end
		 *            : Parámetro que representa el vértice final de la arista
		 * @param name
		 *            : Parámetro que representa el nombre de la arista o etiqueta
		 *            de la arista
		 */
		public Edge(Vertex<V> start, Vertex<V> end, A name) {
			this.setStart(start);
			this.setEnd(end);
			this.setName(name);
		}

		/**
		 * Método constructor con polimorfismo
		 * 
		 * @param start
		 *            : Parámetro que representa el vértice inicial de la arista
		 * @param end
		 *            : Parámetro que representa el vértice final de la arista
		 * @param weight
		 *            : Parámetro que representa el peso de la arista que une a los
		 *            dos nodos
		 * @param name
		 *            : Nombre o etiqueta que se le da a la arista
		 */
		public Edge(Vertex<V> start, Vertex<V> end, long weight, A name) {
			this.setStart(start);
			this.setEnd(end);
			this.setWeight(weight);
			this.setName(name);
		}

		/**
		 * Método que me sirve para devolver el vértice inicial de la arista
		 * 
		 * @return El vértice inicial de la arista
		 */
		public Vertex<V> getStart() {
			return start;
		}

		/**
		 * Método que modifica el vértice inicial de la arista
		 * 
		 * @param start
		 *            : Vértice que entra como parámetro para modificar el vértice
		 *            actual
		 */
		public void setStart(Vertex<V> start) {
			this.start = start;
		}

		/**
		 * Método que sirve para devolver el vértice final de la arista
		 * 
		 * @return El vértice final de la arista
		 */
		public Vertex<V> getEnd() {
			return end;
		}

		/**
		 * Método que modifica el vértice final de la arista
		 * 
		 * @param end
		 *            : Vértice que entra como parámetro para modificar el vértice
		 *            final de la arista
		 */
		public void setEnd(Vertex<V> end) {
			this.end = end;
		}

		/**
		 * Método que se encarga de dar el peso de la arista
		 * 
		 * @return el peso de la arista
		 */
		public long getWeight() {
			return weight;
		}

		/**
		 * Método que modifica el peso de la arista
		 * 
		 * @param weight
		 *            : Representa el nuevo peso de la arista
		 */
		public void setWeight(long weight) {
			this.weight = weight;
		}

		/**
		 * Método que devuelve el nombre o la etiqueta de la arista
		 * 
		 * @return el nombre
		 */
		public A getName() {
			return name;
		}

		/**
		 * Método que modifica el nombre o la etiqueta de la arista
		 * 
		 * @param name
		 *            : Nuevo nombre o etiqueta que entra como parametro
		 */
		public void setName(A name) {
			this.name = name;
		}

		/**
		 */
		@Override
		public int compareTo(Edge<V, A> o) {
				return 0;
		}
		
		@Override
		public String toString() {
			return start.toString() + " " +  end.toString() + " " + weight + " " + name.toString();
		}
	}
	
	/**
	 */
	public static class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

		/**
		 * Atributo de tipo generico que representa la información del vértice
		 */
		private T data;

		/**
		 * Método constructor de Vértice
		 * 
		 * @param data
		 *            : Valor que entra al constructor para inicializar el atributo.
		 */
		public Vertex(T data) {
			this.data = data;
		}

		/**
		 * Método que sirve para obtener el valor generico del vértice
		 * 
		 * @return
		 */
		public T getData() {
			return data;
		}

		/**
		 * Este método modifica la información del vértice de acuerdo al parámetro
		 * que le entra
		 * 
		 * @param data
		 *            : Valor que entra como parámetro para modificar el valor T del
		 *            vertice
		 */
		public void setData(T data) {
			this.data = data;
		}

		/**
		 * Método que extiende de Comparable y sirve para comparar el valor T del
		 * vértice actual con el valor T del vertice que entra como parámetro
		 */
		@Override
		public int compareTo(Vertex<T> arg0) {
			return data.compareTo(arg0.getData());
		}
		
		@Override
		public String toString() {
			return data.toString();
		}

	}
}
