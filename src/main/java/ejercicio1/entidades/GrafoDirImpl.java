package ejercicio1.entidades;
import ejercicio1.interfaces.GrafoDir;
import ejercicio1.interfaces.Secuencia;

public class GrafoDirImpl<E> implements GrafoDir<E> {

	class Vertice<T>{
		T ver;
		
		@Override
		public boolean equals(Object elem){
			return ver.equals(elem);
		}
	}

	private int [][] MAdy;
	private Vertice<E> [] nodos;
	private int cant_nodos;

	private int Vert2Indice(E v){
		int  i=cant_nodos-1;
		while((i>=0) && !nodos[i].equals(v))
			i--;
		return i;
	}
	
	public void agregarArista(E v1, E v2, int peso) {
		int o=Vert2Indice(v1);
		int d=Vert2Indice(v2);
		MAdy[o][d]=peso;
	}

	public void agregarVertice(E vert) {
		nodos[cant_nodos] = new Vertice<E>();
		nodos[cant_nodos].ver=vert;
		for(int i=0;i<=cant_nodos;i++){
			MAdy[cant_nodos][i]=0;
			MAdy[i][cant_nodos]=0;
		}
		cant_nodos++;
	}

	public void eliminarArista(E v1, E v2) {
		int o=Vert2Indice(v1);
		int d=Vert2Indice(v2);
		MAdy[o][d]=0;
    }

	public void eliminarVertice(E v) {
		int i,k;
		int ind=Vert2Indice(v);
		for(i=ind;i<cant_nodos;i++){
			nodos[i]=nodos[i+1];
			for(k=0;k<cant_nodos;k++)
				MAdy[k][i]=MAdy[k][i+1];
			for(k=0;k<cant_nodos;k++)
				MAdy[i][k]=MAdy[i+1][k];
		}
		cant_nodos--;
	}

	public void inicializar() {
		MAdy = new int[100][100];
		nodos = new Vertice[100];
		cant_nodos=0;
	}

	public int pesoArista(E v1, E v2) {
		int o=Vert2Indice(v1);
		int d=Vert2Indice(v2);
		return MAdy[o][d];
	}

	public Secuencia<E> adyacentes(E e) {
		int v=Vert2Indice(e);
		Secuencia<E> VertAdy=new SecuenciaImpl<E>();
		VertAdy.inicializar();
		
		for(int i=0;i<cant_nodos;i++){
			if (MAdy[v][i]!=0)
				VertAdy.agregar(nodos[i].ver);
		}
		return VertAdy;
	}

	public Secuencia<E> vertices() {
		Secuencia<E> vertices = new SecuenciaImpl<E>();
		vertices.inicializar();
		
		for(int i=0;i<cant_nodos;i++){
			vertices.agregar(nodos[i].ver);
		}
		return vertices;
	}
	
	public boolean existeArista(E v1, E v2){
		int o=Vert2Indice(v1);
		int d=Vert2Indice(v2);
		return (MAdy[o][d] != 0);
	}
	
	public boolean existeVertice(E e) {
		for(int i=0;i<cant_nodos;i++){
			if(nodos[i].ver.equals(e))
				return true;
		}
		return false;
	}

}
