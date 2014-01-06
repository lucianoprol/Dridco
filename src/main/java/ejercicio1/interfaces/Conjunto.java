package ejercicio1.interfaces;

/**
 * Conjunto de elementos que no permite elementos repetidos
 *
 * @param <E>
 */
public interface Conjunto<E> {

	/**
	 * Incializa la estructura para poder comenzar a 
	 * utilizarla 
	 */
	void inicializar();
	
	/**
	 * PRECONDICION: conjunto Inicializado
	 * Devuelve true si el conjunto no tiene elementos y false en caso contrario
	 */
	boolean vacio();

	/**
	 * PRECONDICION: conjunto Inicializado	
	 * Devuelve true si el elemento pertenece al conjunto y false en caso contrario
	 * @param elemento
	 * @return
	 */
	boolean pertenece(E elemento);
	
	/**
	 * PRECONDICION: conjunto No Vacio	
	 * Devuelve un elemento del conjunto en forma aleatoria
	 * @return
	 */
	E elegir();

	/**
	 * PRECONDICION: conjunto Inicializado	
	 * Agrega el elemento al conjunto siempre que ya no exista en el mismo
	 * @param elemento
	 */
	void agregar(E elemento);
	
	/**
	 * PRECONDICION: conjunto Inicializado
	 * Elimina del conjunto un elemento dado
	 */	
	void sacar(E elemento);	
}
