package structures;

import java.util.*;

/**
 */
@SuppressWarnings("unchecked")
public class GraphMatrix<V extends Comparable<V>, A extends Comparable<A>> {
	/**
	 * Constante que representa el Integer.MAX_VALUE
	 */
	public static final int INFINITO = Integer.MAX_VALUE;
	/**
	 * Matriz de Adyacencia. Compuesta por un TreeSet de Aristas
	 */
	private TreeSet<Edge<V, A>>[][] adjacencyMatriz;
	/**
	 * Conjunto de vértices
	 */
	private HashSet<Vertex<V>> vertexes;
	/**
	 * Indice
	 */
	private HashMap<Vertex<V>, Integer> indVer;
	/**
	 * Indice
	 */
	private TreeMap<Integer, Vertex<V>> verInd;
	/**
	 * Atributo que representa el siguiente vértice
	 */
	private int nextVertex;
	/**
	 * Atributo que indica si el grafo es o no dirigido
	 */
	private boolean directed;
	/**
	 * Matriz de Caminos del Floyd Warshall
	 */
	private List<Edge<V, A>>[][] caminosFloyd;

	/**
	 * Método constructor del Grafo Matriz
	 */
	public GraphMatrix(int v) {
		directed = false;
		nextVertex = 0;
		adjacencyMatriz = new TreeSet[v][v];
		vertexes = new HashSet<>();
		indVer = new HashMap<>();
		verInd = new TreeMap<>();
	}

	/**
	 * Método constructor del Grafo Matriz sobrecargado
	 * 
	 * @param directed
	 *            : True si el grafo es dirigido o false en caso contrario
	 */
	public GraphMatrix(int v, boolean directed) {
		this.directed = directed;
		nextVertex = 0;
		adjacencyMatriz = new TreeSet[v][v];
		vertexes = new HashSet<>();
		indVer = new HashMap<>();
		verInd = new TreeMap<>();
	}

	/**
	 * Este método agrega un vértice
	 * 
	 * @param object
	 *            : Valor del vértice
	 * @return True si el vértice fue agregado o false en caso contrario
	 */
	public boolean addVertex(V object) {

		boolean added = false;

		if (searchVertex(object) == null) {

			Vertex<V> toAdd = new Vertex<V>(object);

			verInd.put(nextVertex, toAdd);

			indVer.put(toAdd, nextVertex);

//			if (nextVertex >= getNumberVertexes()) {
//				ampliarMatriz();
//			}

			nextVertex++;

			vertexes.add(toAdd);

			added = true;

		}

		return added;
	}

	/**
	 * Este método se encarga de agregar una arista
	 * 
	 * @param in
	 *            : Valor del vértice inicial
	 * @param out
	 *            : Valor del vértice final
	 * @param w
	 *            : Peso de la arista
	 * @param name
	 *            : Nombre o valor de la arista
	 * @return
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
	 * Este método se encarga de agregar una arista
	 * 
	 * @param in
	 *            : Valor del vértice inicial
	 * @param out
	 *            : Valor del vértice final
	 * @param w
	 *            : Peso de la arista
	 * @return True si la arista fue agregada o false en caso contrario
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
	 * Este método se encarga de agregar una arista
	 * 
	 * @param in
	 *            : Valor del vértice inicial
	 * @param out
	 *            : Valor del vértice final
	 * @param name
	 *            : Nombre o etiqueta de la arista
	 * @return True si la arista fue agregada o false en caso contrario
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
	 * Este método se encarga de agregar una arista
	 * 
	 * @param in
	 *            : Valor del vértice inicial
	 * @param out
	 *            : Valor del vértice final
	 * @return True si la arista fue agregada o false en caso contrario
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
	 * Este método se encarga de busca una arista
	 * 
	 * @param start
	 *            : Vértice inicial de la arista
	 * @param end
	 *            : Vértice final de la arista
	 * @return La arista si la encuentra o de lo contrario retorna null
	 */
	private Edge<V, A> searchEdge(Vertex<V> start, Vertex<V> end) {

		int i = indVer.get(start);
		int j = indVer.get(end);

		Edge<V, A> sought = null;

		if (adjacencyMatriz[i][j] != null) {
			for (Edge<V, A> ed : adjacencyMatriz[i][j]) {
				sought = ed;
				break;
			}

		}

		return sought;

	}

	/**
	 * Este método se encarga de buscar una arista
	 * 
	 * @param start
	 *            : Vértice inicial de la arista
	 * @param end
	 *            : Vértice final de la arista
	 * @param w
	 *            : Peso de la arista
	 * @return La arista si la encuentra o de lo contrario retorna null
	 */
	private Edge<V, A> searchEdge(Vertex<V> start, Vertex<V> end, long w) {

		int i = indVer.get(start);
		int j = indVer.get(end);

		Edge<V, A> sought = null;

		if (adjacencyMatriz[i][j] != null) {
			for (Edge<V, A> ed : adjacencyMatriz[i][j]) {
				if (ed.getWeight() == w) {
					sought = ed;
					break;
				}
			}

		}

		return sought;

	}

	/**
	 * Este método se encarga de buscar una arista
	 * 
	 * @param start
	 *            : Vérice inicial de la arista
	 * @param end
	 *            : Vérice final de la arista
	 * @param name
	 *            : Nombre o etiqueta de la arista
	 * @return La arista si la encuentra o de lo contrario retorna null
	 */
	private Edge<V, A> searchEdge(Vertex<V> start, Vertex<V> end, A name) {

		Edge<V, A> sought = null;

		int i = indVer.get(start);
		int j = indVer.get(end);

		if (adjacencyMatriz[i][j] != null) {
			for (Edge<V, A> ed : adjacencyMatriz[i][j]) {
				if (ed.getName().equals(name)) {
					sought = ed;
					break;
				}
			}

		}

		return sought;

	}

	/**
	 * Este método se encarga de agregar una arista
	 * 
	 * @param aAgregar
	 *            : Arista a agregar
	 */
	private void addEdge(Edge<V, A> aAgregar) {

		int start = indVer.get(aAgregar.getStart());

		int end = indVer.get(aAgregar.getEnd());

		if (adjacencyMatriz[start][end] == null) {
			adjacencyMatriz[start][end] = new TreeSet<>(new Comparator<Edge<V, A>>() {

				@Override
				public int compare(Edge<V, A> o1, Edge<V, A> o2) {
					return (int) Math.signum(o1.getWeight() - o2.getWeight());
				}

			});

		}

		adjacencyMatriz[start][end].add(aAgregar);

	}

	/**
	 * Este método se encarga de buscar un vértice
	 * 
	 * @param object
	 *            : Valor del vértice a buscar
	 * @return El vértice si lo encuentra o de lo contrario retorna null
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

//	/**
//	 * Este método se encarga de aumentar el tamaño de la matriz en caso se que
//	 * se quiera exceder su capacidad actual
//	 */
//	private void ampliarMatriz() {
//
//		TreeSet<Edge<V, A>>[][] nueva = new TreeSet[(int) (adjacencyMatriz.length
//				* 1.5)][(int) (adjacencyMatriz[0].length * 1.5)];
//
//		for (int i = 0; i < adjacencyMatriz.length; i++) {
//			for (int j = 0; j < adjacencyMatriz[0].length; j++) {
//				nueva[i][j] = adjacencyMatriz[i][j];
//			}
//		}
//
//		adjacencyMatriz = nueva;
//
//	}

	/**
	 * Este método se encarga de hacer un recorrido en amplitud sobre el grafo
	 * utilizando como base una Queue
	 * 
	 * @param start
	 *            : Vértice donde se inicia a recorrer
	 * @return Lista de vértices
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

					int i = indVer.get(current);

					for (Vertex<V> vert : indVer.keySet()) {

						int j = indVer.get(vert);

						if (i != j && adjacencyMatriz[i][j] != null && !adjacencyMatriz[i][j].isEmpty()) {
							Edge<V, A> ver = adjacencyMatriz[i][j].first();
							if (!visitados.contains(ver.getEnd())) {
								queue.add(ver.getEnd());
							}
						}

					}
				}
			}
		}

		return list;

	}

	/**
	 * Este método se encarga de hacer un recorrido en profundidad usando como
	 * base una Stack
	 * 
	 * @param start:
	 *            Vértice donde se inicia el recorrido
	 * @return Lista de vértices
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

					int i = indVer.get(current);

					for (Vertex<V> vert : indVer.keySet()) {

						int j = indVer.get(vert);

						if (i != j && adjacencyMatriz[i][j] != null && !adjacencyMatriz[i][j].isEmpty()) {
							Edge<V, A> ver = adjacencyMatriz[i][j].first();
							if (!visitados.contains(ver.getEnd())) {
								stack.add(ver.getEnd());
							}
						}

					}

				}
			}
		}

		return list;

	}

	/**
	 * Este método se encarga de inicializar la matriz de Pesos y Caminos del
	 * Floy Warshall. Luego realiza los respectivos cambios en ambas matrices
	 */
	public void ejecutarFloydWarshall() {

		long pesos[][] = new long[getNumberVertexes()][getNumberVertexes()];
		caminosFloyd = new ArrayList[getNumberVertexes()][getNumberVertexes()];

		// Inicializa la matriz
		for (int i = 0; i < getNumberVertexes(); i++) {
			for (int j = 0; j < getNumberVertexes(); j++) {
				caminosFloyd[i][j] = new ArrayList<Edge<V, A>>();
				if (i == j) {
					pesos[i][j] = 0;
				} else if (adjacencyMatriz[i][j] != null && !adjacencyMatriz[i][j].isEmpty()) {

					Edge<V, A> camino = adjacencyMatriz[i][j].first();
					pesos[i][j] = camino.getWeight();
					caminosFloyd[i][j].add(camino);
				} else {
					pesos[i][j] = INFINITO;
				}
			}
		}

		// Modifica la matriz

		for (int k = 0; k < getNumberVertexes(); k++) {
			for (int i = 0; i < getNumberVertexes(); i++) {
				for (int j = 0; j < getNumberVertexes(); j++) {
					if (pesos[i][k] != INFINITO && pesos[k][j] != INFINITO && pesos[i][k] + pesos[k][j] < pesos[i][j]) {
						pesos[i][j] = pesos[i][k] + pesos[k][j];
						caminosFloyd[i][j] = new ArrayList<Edge<V, A>>();
						for (Edge<V, A> ed : caminosFloyd[i][k]) {
							caminosFloyd[i][j].add(ed);
						}
						for (Edge<V, A> ed : caminosFloyd[k][j]) {
							caminosFloyd[i][j].add(ed);
						}
					}
				}
			}
		}

	}
	
	/**
	 * Algoritmo de Bellman Ford
	 * Algoritmo que se encarga de mirar si existe ciclos negativos en el grafo
	 * 
	 * @param start 
	 * 				V parametro generico que representa el inicio del grafo
	 * @return booleano 
	 * 					representa si hay ciclos o no
	 */
	public boolean BellmanFord(V start) {
		Vertex<V> inicio = searchVertex(start);
		boolean st = false;
		
		if (inicio != null) {
			HashMap<V, Long> dist = new HashMap<>();
			
			for (Vertex<V> vertex : vertexes) 
				dist.put(vertex.getData(), (long) Integer.MAX_VALUE);
			
			dist.replace(inicio.getData(), 0l);
			
			for (int i = 0; i < adjacencyMatriz.length; i++) {
				for (int j = 0; j < adjacencyMatriz[i].length; j++) {
					if (adjacencyMatriz[i][j]!=null) {
						for (Edge<V, A> edge: adjacencyMatriz[i][j]) {
							Vertex<V> u = edge.getStart();
							Vertex<V> v = edge.getEnd();
							long w = edge.getWeight();
							
							if (dist.get(u.getData())!=Integer.MAX_VALUE && dist.get(u.getData())+w<dist.get(v.getData()))
								dist.replace(v.getData(), dist.get(u.getData())+w);
						}
					}
				}
			}
			
			for (int i = 0; i < adjacencyMatriz.length; i++) {
				for (int j = 0; j < adjacencyMatriz[i].length; j++) {
					if (adjacencyMatriz[i][j]!=null) {
						for (Edge<V, A> edge: adjacencyMatriz[i][j]) {
							Vertex<V> u = edge.getStart();
							Vertex<V> v = edge.getEnd();
							long w = edge.getWeight();
							
							if (dist.get(u.getData())!=Integer.MAX_VALUE && dist.get(u.getData())+w<dist.get(v.getData()))
								st = true;
						}
					}
				}
			}
		}
		return st;
	}

	/**
	 * Este método se encarga de dar el mejor camino entre dos vértices
	 * 
	 * @param start
	 *            : Vértice inicial
	 * @param end
	 *            : Vértice final
	 * @return Retorna una lista con el mejor camino entre dos vértices
	 */
	public List<Edge<V, A>> queryFloydWarshall(V start, V end) {

		int i = indVer.get(searchVertex(start));
		int j = indVer.get(searchVertex(end));

		return caminosFloyd[i][j];

	}

	/**
	 * Este método se encarga de dar el número de vértices agregados
	 * 
	 * @return Número de vértices
	 */
	public int getNumberVertexes() {
		return vertexes.size();
	}

	/**
	 * Este método se encarga de decir si el grafo es dirigido o no
	 * 
	 * @return True si es dirigido o false en caso contrario
	 */
	public boolean isDirected() {
		return directed;
	}

}
