package structures;

/**
 */
public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

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