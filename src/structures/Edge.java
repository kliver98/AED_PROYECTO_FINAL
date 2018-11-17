package structures;

/**
 */
public class Edge<V extends Comparable<V>, A extends Comparable<A>> implements Comparable<Edge<V, A>> {
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