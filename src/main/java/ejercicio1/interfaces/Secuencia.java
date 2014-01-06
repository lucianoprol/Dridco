package ejercicio1.interfaces;

/**
 * La estructura recibe elementos de forma secuencial y 
 * permite recuperar cada elemeto mediante su indice.
 */
public interface Secuencia<E> {

	/**
	 * Incializa la estructura para poder comenzar a 
	 * utilizarla 
	 */
	void inicializar();
	
	/**
	 * PRECONDICION: secuencia inicializada
	 * Agrega el elemento elem al final de la secuencia
	 */
	void agregar(E elem);
	
	/**
	 * PRECONDICION: secuencia inicializada
	 * Recupera el valor del elemento en el indice i
	 */
	E obtener(int i) throws Exception;

	/**
	 * Elimina el ultimo elemento de la secuencia
	 */
	void sacar() throws Exception;

	/**
	 * Elimina un elemento de la secuencia dada una posición
	 */
	public void eliminarElemento(int posicion) throws Exception;
	
	/**
	 * Devuelve true si la secuencia no tiene elementos y false en caso contratrio
	 */
	boolean vacia();

	/**
	 * Devuelve la longitud de la secuencia
	 */
	public int longitud();
}
