package ejercicio1.interfaces;

/**
 * La estructura recibe elementos con una prioridad asociada, y permite
 * recuperar el elementos con mayor prioridad o menor prioridad
 */
public interface ColaPrioridad<E> {
	
	/**
	 * Incializa la estructura para poder comenzar a utilizarla 
	 */
	public void inicializar();
	/**
	 * Agrega el elemento e con la prioridad asociada p
	 */
	public void agregarElemento(E e, double p);
	/**
	 * Recupera el valor del elemento con mayor prioridad	
	 */
	public E recuperarMaxElemento() throws Exception;
	/**
	 * Recupera el valor del elemento con menor prioridad	
	 */
	public E recuperarMinElemento() throws Exception;
	/**
	 * Recupera la prioridad del elemento con mayor prioridad	
	 */
	public double recuperarMaxPrioridad() throws Exception;
	/**
	 * Recupera la prioridad del elemento con menor prioridad	
	 */
	public double recuperarMinPrioridad() throws Exception;
	/**
	 * Elimina el elemento con mayor prioridad	
	 */
	public void eliminarMaxPrioridad();
	/**
	 * Elimina el elemento con menor prioridad	
	 */
	public void eliminarMinPrioridad();
	/**
	 * Devuelve true si la cola no tiene elementos y false en caso contratrio
	 */
	public boolean vacia();

}
