package ejercicio1.entidades;

import java.util.Random;

import ejercicio1.interfaces.Conjunto;

public class ConjuntoImpl<E> implements Conjunto<E> {

	class Elem<T>{
		T e;
		
		@Override
		public boolean equals(Object obj){
			return e.equals(obj);
		}
		
	}
	private Elem<E>[] elementos;
	private int cant;
	
	private Random random= new Random();
	
	public void agregar(E elemento) {
		elementos[cant] = new Elem<E>();
		elementos[cant].e=elemento;
		cant++;
	}


	public boolean vacio() {
		return cant==0;
	}


	public E elegir() {
		return elementos[random.nextInt(cant)].e;
	}

	
	public void inicializar() {
		elementos=new Elem[1000];
		cant=0;
	}

	
	public boolean pertenece(E elemento) {
		int i;
		for(i=0; i<cant && !elementos[i].equals(elemento); i++);
		return i<cant;
	}

	
	public void sacar(E elemento) {
		int i;
		for (i=0; i<cant && !elementos[i].equals(elemento); i++);
		if (i<cant) {
			elementos[i]=elementos[cant-1];
			cant--;
		}
	}
	
	
}
