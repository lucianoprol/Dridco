package ejercicio1.entidades;


import ejercicio1.interfaces.Secuencia;


public class SecuenciaImpl<E> implements Secuencia<E>{
	
	class Elem<T>{
		T e;
		
		@Override
		public boolean equals(Object obj){
			return e.equals(obj);
		}
		
		@Override
		public String toString(){
			return e.toString();
		}
	}
	
	private Elem<E>[] elementos;
	private int cant;
	
	public void agregar(E elem) {
		elementos[cant] = new Elem<E>();
		elementos[cant].e=elem;
		cant++;
	}

	public boolean vacia() {
		return cant==0;
	}

	public E obtener(int posicion) throws Exception {
		if (elementos != null && posicion >= 0 && posicion <cant && elementos[posicion] != null){
			return elementos[posicion].e;
		}else{
			if (elementos == null){
				throw new Exception("La secuencia no esta inicializada");
			}else{
				if (posicion < 0){
					throw new Exception("La posición no puede ser menor que cero");
				}
				else if (posicion > cant){
					throw new Exception("La posición no puede ser mayor a la longitud de la secuencia que es: "+ cant);
				}else{
					throw new Exception("La posición dada no contiene ningún elemento");
				}
			}
		}
	}

	
	public void inicializar() {
		elementos=new Elem[1000];
		cant=0;
	}

	
	public void sacar() throws Exception {
		cant--;
	}
	
	public int longitud(){
		return cant;
	}
	
	/*
	 * Eliminar un elemento de la secuencia
	 */
	public void eliminarElemento(E elemento) {
		int i;
		for (i=0; i<cant && !elementos[i].equals(elemento); i++);
		if (i<cant) {
			try {
				eliminarElemento(i);
			} catch(Exception e){}
		}
	}
	
	/*
	 * Eliminar un elemento de la secuencia dada una posición
	 */
	public void eliminarElemento(int posicion) throws Exception{
		if (posicion >= 0 && posicion < cant){
			for(int i=posicion; i < cant-1; i++) {
				elementos[i] = elementos[i+1];
				cant--;
			}
		}else{
			throw new Exception("La posición dada no es una posición válida");
		}
	}
	
	public String toString(){
		String r = new String();
		for (int i=0; i<cant;i++){
			r+=elementos[i].toString();
			r+=", ";
		}
		if(r.length() > 0)
			r=r.substring(0,r.length()-2);
		return r;
	}
	
}
