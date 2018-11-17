package structures;

/**
 */
public class Edge<V extends Comparable<V>, A extends Comparable<A>> implements Comparable<Edge<V, A>> {
	/**
	 * Relaci�n al v�rtice inicial
	 */
	private Vertex<V> start;

	/**
	 * Relaci�n al v�rtice final
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
	 * M�todo constructor de la arista
	 * 
	 * @param start
	 *            : Par�metro que representa el v�rtice inicial
	 * @param end
	 *            : Par�metro que representa el v�rtice final
	 */
	public Edge(Vertex<V> start, Vertex<V> end) {
		this.setStart(start);
		this.setEnd(end);
		this.setWeight(1);
	}

	/**
	 * M�todo constructor con polimorfismo
	 * 
	 * @param start
	 *            : Par�metro que representa el v�rtice inicial de la arista
	 * @param end
	 *            : Par�metro que representa el v�rtice final de la arista
	 * @param weight
	 *            : Par�metro que representa el peso de la arista que uno a los
	 *            dos vertices
	 */
	public Edge(Vertex<V> start, Vertex<V> end, long weight) {
		this.setStart(start);
		this.setEnd(end);
		this.setWeight(weight);
	}

	/**
	 * M�todo constructor con polimorfismo
	 * 
	 * @param start
	 *            : Par�metro que representa el v�rtice inicial de la arista
	 * @param end
	 *            : Par�metro que representa el v�rtice final de la arista
	 * @param name
	 *            : Par�metro que representa el nombre de la arista o etiqueta
	 *            de la arista
	 */
	public Edge(Vertex<V> start, Vertex<V> end, A name) {
		this.setStart(start);
		this.setEnd(end);
		this.setName(name);
	}

	/**
	 * M�todo constructor con polimorfismo
	 * 
	 * @param start
	 *            : Par�metro que representa el v�rtice inicial de la arista
	 * @param end
	 *            : Par�metro que representa el v�rtice final de la arista
	 * @param weight
	 *            : Par�metro que representa el peso de la arista que une a los
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
	 * M�todo que me sirve para devolver el v�rtice inicial de la arista
	 * 
	 * @return El v�rtice inicial de la arista
	 */
	public Vertex<V> getStart() {
		return start;
	}

	/**
	 * M�todo que modifica el v�rtice inicial de la arista
	 * 
	 * @param start
	 *            : V�rtice que entra como par�metro para modificar el v�rtice
	 *            actual
	 */
	public void setStart(Vertex<V> start) {
		this.start = start;
	}

	/**
	 * M�todo que sirve para devolver el v�rtice final de la arista
	 * 
	 * @return El v�rtice final de la arista
	 */
	public Vertex<V> getEnd() {
		return end;
	}

	/**
	 * M�todo que modifica el v�rtice final de la arista
	 * 
	 * @param end
	 *            : V�rtice que entra como par�metro para modificar el v�rtice
	 *            final de la arista
	 */
	public void setEnd(Vertex<V> end) {
		this.end = end;
	}

	/**
	 * M�todo que se encarga de dar el peso de la arista
	 * 
	 * @return el peso de la arista
	 */
	public long getWeight() {
		return weight;
	}

	/**
	 * M�todo que modifica el peso de la arista
	 * 
	 * @param weight
	 *            : Representa el nuevo peso de la arista
	 */
	public void setWeight(long weight) {
		this.weight = weight;
	}

	/**
	 * M�todo que devuelve el nombre o la etiqueta de la arista
	 * 
	 * @return el nombre
	 */
	public A getName() {
		return name;
	}

	/**
	 * M�todo que modifica el nombre o la etiqueta de la arista
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