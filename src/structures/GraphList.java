package structures;

import java.util.*;

/**
 */
public class GraphList<V extends Comparable<V>, A extends Comparable<A>> {

	/**
	 * Lista de Adyacencia. Nota: Modelado como multigrafo (Se utiliza ArrayList
	 * / TreeSet y no se utiliza un atributo de �ltimo indice)
	 */
	private HashMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V, A>>>> adjacencyList;

	/**
	 * Conjunto de v�rtices
	 */
	private HashSet<Vertex<V>> vertexes;
	/**
	 * Atributo para determinar si el grafo es dirigido
	 */
	private boolean directed;

	/**
	 * M�todo constructor del grafo de listas adyacentes
	 */
	public GraphList() {

		adjacencyList = new HashMap<>();
		vertexes = new HashSet<>();
		directed = false;

	}

	/**
	 * M�todo constructor con sobrecarga
	 * 
	 * @param directed
	 *            Par�metro de entrada que define si es un grafo dirigido o no
	 */
	public GraphList(boolean directed) {

		adjacencyList = new HashMap<>();
		vertexes = new HashSet<>();
		this.directed = directed;

	}

	/**
	 * M�todo que dice si el grafo es dirigido o no
	 * 
	 * @return true si es dirigido o false en caso contrario
	 */
	public boolean isDirected() {
		return directed;
	}

	/**
	 * M�todo que da el n�mero de v�rtices que tiene el grafo
	 * 
	 * @return Cantidad de vertices agregados
	 */
	public int getNumeroVertices() {
		return vertexes.size();
	}

	/**
	 * M�todo que agrega el v�rtice
	 * 
	 * @param object
	 *            : Valor del v�rtice
	 * @return True si el v�rtice fue agregado o false en caso contrario
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
	 * M�todo que se encarga de agregar una arista, con peso, nombre o etiqueta
	 * de la arista y sus dos vertices
	 * 
	 * @param in
	 *            : V�rtice inicial en la arista
	 * @param out
	 *            : V�rtice final en la arista
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
	 * M�todo que se encarga de agregar una arista, con su peso y sus v�rtices
	 * 
	 * @param in
	 *            : V�rtice inicial en la arista
	 * @param out
	 *            : V�rtice final en la arista
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
	 * Este m�todo se encarga de agregar una arista con sus dos v�rtices y la
	 * etiqueta o nombre de la ruta
	 * 
	 * @param in
	 *            : V�rtice inicial de la arista
	 * @param out
	 *            : V�rtice final de la arista
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
	 * Este m�todo se encarga de agregar una arista con sus dos v�rtices
	 * respectivamente
	 * 
	 * @param in
	 *            : V�rtice donde comienza la arista
	 * @param out
	 *            : V�rtice donde termina la arista
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
	 * Este m�todo se encarga de buscar un vertice en el grafo
	 * 
	 * @param object
	 *            : Valor del v�rtice a buscar
	 * @return El v�rtice si lo encontr� en el grafo, de lo contrario retorna
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
	 * M�todo que se encarga de buscar una arista en la lista de adyacencias
	 * 
	 * @param start
	 *            : V�rtice inicial por el cual empieza la arista
	 * @param end
	 *            : V�rtice final en el cual termina la arista
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
	 * M�todo que sirve para buscar una arista de acuerdo a sus dos v�rtices y
	 * el peso de la arista
	 * 
	 * @param start
	 *            : V�rtice inicial de la arista
	 * @param end
	 *            : V�rtice final de la arista
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
	 * Este m�todo se encarga de buscar una arista en la lista de adyacencias de
	 * acuerdo a sus dos v�rtices y la etiqueta de nombre o ruta de la arista
	 * 
	 * @param start
	 *            : V�rice inicial de la arista
	 * @param end
	 *            : V�rtice final de la arista
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
	 * M�todo que se encarga de agregar una arista de acuerdo al parametro que
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
	 * Este m�todo se encarga de recorrer el grafo mediante sus nodos adyacentes
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
	 * Este m�todo se encarga de recorrer el grafo mediante sus nodos en
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
	 * @param end
	 *            : Nodo final, donde se termina el Dijkstra
	 * @return Una lista de aristas que representa el menor camino entre dos v�rtices
	 */
	public List<Edge<V, A>> Dijkstra(V start, V end) {

		List<Edge<V, A>> camino = null;

		Vertex<V> inicio = searchVertex(start);
		Vertex<V> fin = searchVertex(end);

		if (inicio != null && fin != null && adjacencyList.get(inicio) != null) {

			camino = new ArrayList<>();

			HashMap<V, Long> dist = new HashMap<>();

			HashMap<V, ArrayList<Edge<V, A>>> caminos = new HashMap<>();

			for (Vertex<V> ver : vertexes) {
				dist.put(ver.getData(), (long) Integer.MAX_VALUE);
			}

			dist.replace(start, 0l);
			caminos.put(start, new ArrayList<>());

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

					if (dist.get(u) + w < dist.get(v)) {

						dist.replace(v, dist.get(u) + w);

						ArrayList<Edge<V, A>> temp = new ArrayList<>();

						for (Edge<V, A> ed : caminos.get(u)) {
							temp.add(ed);
						}

						temp.add(actual);
						caminos.put(v, temp);

						if (adjacencyList.containsKey(actual.getEnd())) {
							for (Vertex<V> ver : adjacencyList.get(actual.getEnd()).keySet()) {
								cola.addAll(adjacencyList.get(actual.getEnd()).get(ver));
							}
						}
					}

				}

			}

			camino = caminos.get(end);

		}

		return camino;

	}

	/**
	 * Este m�todo se encarga de devolver la lista de adyacencia.
	 * 
	 * @return La lista de adyacencia
	 */
	public HashMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V, A>>>> getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * Este m�todo se encarga de reemplzar la lista actual con la que entra como
	 * par�metro
	 * 
	 * @param adjacencyList
	 *            : Nueva lista de adyacencia
	 */
	public void setAdjacencyList(HashMap<Vertex<V>, TreeMap<Vertex<V>, TreeSet<Edge<V, A>>>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	/**
	 * Este m�todo se encarga de obtener el conjunto de v�rtices del grafo
	 * 
	 * @return El conjunto de vertices del grafo
	 */
	public HashSet<Vertex<V>> getVertexes() {
		return vertexes;
	}

	/**
	 * Este m�todo se encarga de modificar el conjunto de v�rtices del grafo.
	 * 
	 * @param vertexes
	 *            : Nuevo conjunto de vertices que entra como parametro
	 */
	public void setVertexes(HashSet<Vertex<V>> vertexes) {
		this.vertexes = vertexes;
	}

}
