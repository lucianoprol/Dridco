package ejercicio1.interfaces;

/**
 * Estructura que reprenta a un Grafo Dirigido
 *
 */
public interface GrafoDir<E> {
	/**
	 * Permite inicializar la estructura para poder comenzar a 
	 * utilizarla
	 */
   void inicializar();
   
   /**
    * PRECONDICION: grafo Inicializado	   
    */
   Secuencia<E> vertices();

   /**
    * PRECONDICION: grafo Inicializado, y el vertice no debe existir	   
    */
   void agregarVertice(E vertice);

   /**
    * PRECONDICION: grafo Inicializado, [Vertice1] y [Vertice2] existentes. [Costo] mayor igual a 0. Arista no existente.
    * El vertice1 es el origen y el vertice2 es el destino
    */
   void agregarArista(E vertice1,E vertice2, int costo);

   /**
    * PRECONDICION: grafo Inicializado, [Vertice1] y [Vertice2] existentes.
    * El vertice1 es el origen y el vertice2 es el destino
    */
   boolean existeArista(E vertice1, E vertice2);
   
   /**
    * PRECONDICION: grafo Inicializado.
    */
   boolean existeVertice(E e);
   
   /**
    * PRECONDICION: grafo Inicializado, [Vertice1] y [Vertice2] existentes. Arista existente.	   
    * El vertice1 es el origen y el vertice2 es el destino
    */
   int pesoArista(E vertice1, E vertice2);
   
   /**
    * PRECONDICION: grafo Inicializado, [vertice] existente.
    */
   void eliminarVertice(E vertice);
   
   /**
    * PRECONDICION: grafo Inicializado, [Vertice1] y [Vertice2] existentes. Arista Existente.
    * El vertice1 es el origen y el vertice2 es el destino
    */
   void eliminarArista(E vertice1, E vertice2);
   
   /**
    * PRECONDICION: grafo Inicializado, [vertice] existente.
    */
   Secuencia<E> adyacentes(E vertice);

}
