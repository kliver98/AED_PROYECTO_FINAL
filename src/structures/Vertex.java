package structures;

/**
 */
public class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

	/**
	 * Atributo de tipo generico que representa la informaci�n del v�rtice
	 */
	private T data;

	/**
	 * M�todo constructor de V�rtice
	 * 
	 * @param data
	 *            : Valor que entra al constructor para inicializar el atributo.
	 */
	public Vertex(T data) {
		this.data = data;
	}

	/**
	 * M�todo que sirve para obtener el valor generico del v�rtice
	 * 
	 * @return
	 */
	public T getData() {
		return data;
	}

	/**
	 * Este m�todo modifica la informaci�n del v�rtice de acuerdo al par�metro
	 * que le entra
	 * 
	 * @param data
	 *            : Valor que entra como par�metro para modificar el valor T del
	 *            vertice
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * M�todo que extiende de Comparable y sirve para comparar el valor T del
	 * v�rtice actual con el valor T del vertice que entra como par�metro
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