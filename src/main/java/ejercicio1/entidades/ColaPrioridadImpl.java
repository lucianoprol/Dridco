package ejercicio1.entidades;

import ejercicio1.interfaces.ColaPrioridad;

public class ColaPrioridadImpl<E> implements ColaPrioridad<E> {
	
	private class Nodo<N>{
		N elem;
		double prio;
		Nodo<N> sig;
	}
	
	Nodo<E> mayorPrio;
	Nodo<E> menorPrio;
	
	public void inicializar(){
		mayorPrio = null;
		menorPrio = null;
	}
	
	/*
	 * Agrega el elemento e con la prioridad asociada p
	 */
	public void agregarElemento(E e, double p){
		if (mayorPrio == null){
			mayorPrio = new Nodo<E>();
			mayorPrio.elem = e;
			mayorPrio.prio = p;
			menorPrio = mayorPrio;
		}
		else{
			Nodo<E> nuevo = new Nodo<E>();
			nuevo.elem=e;
			nuevo.prio=p;
			
			if (nuevo.prio > mayorPrio.prio){
				nuevo.sig = mayorPrio;
				mayorPrio = nuevo;
			}
			else{
				Nodo<E> aux = mayorPrio;
				while (aux.sig != null && aux.sig.prio >= p){
					aux = aux.sig;
				}
				
				if (aux.sig == null){
					aux.sig = nuevo;
					menorPrio = nuevo;
				}
				else{
					nuevo.sig = aux.sig;
					aux.sig = nuevo;
				}
			}
		}
	}
	/*
	 * Recupera el valor del elemento con mayor prioridad	
	 */
	public E recuperarMaxElemento() throws Exception{
		if (mayorPrio != null){
			return mayorPrio.elem;
		}else{
			throw new Exception("La cola esta vacia");
		}
	}
	
	/*
	 * Recupera el valor del elemento con menor prioridad	
	 */
	public E recuperarMinElemento() throws Exception{
		if (menorPrio != null){
			return menorPrio.elem;
		}else{
			throw new Exception("La cola esta vacia");
		}
	}
	/*
	 * Recupera la prioridad del elemento con mayor prioridad	
	 */
	public double recuperarMaxPrioridad()  throws Exception{
		if (mayorPrio != null){
			return mayorPrio.prio;
		}else{
			throw new Exception("La cola esta vacia");
		}
	}
	/*
	 * Recupera la prioridad del elemento con menor prioridad	
	 */
	public double recuperarMinPrioridad() throws Exception{
		if (menorPrio != null){
			return menorPrio.prio;
		}else{
			throw new Exception("La cola esta vacia");
		}
	}
	/*
	 * Elimina el elemento con mayor prioridad	
	 */
	public void eliminarMaxPrioridad(){
		if (mayorPrio != null){
			mayorPrio = mayorPrio.sig;
			if (mayorPrio == null){
				menorPrio = mayorPrio;
			}
		}
	}
	/*
	 * Elimina el elemento con menor prioridad	
	 */
	public void eliminarMinPrioridad(){
		if (menorPrio != null){
			if (mayorPrio == menorPrio){
				mayorPrio = null;
				menorPrio = null;
			}else{
				Nodo<E> aux = mayorPrio;
				while (aux.sig != null && aux.sig != menorPrio){
					aux = aux.sig;
				}
				if (aux.sig != null){
					aux.sig = null;
					menorPrio = aux;
				}
			}
		}
	}
	/*
	 * Devuelve true si la cola no tiene elementos y false en caso contratrio
	 */
	public boolean vacia(){
		return (mayorPrio == null);
	}

}
