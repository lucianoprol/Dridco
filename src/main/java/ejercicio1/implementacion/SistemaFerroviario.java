package ejercicio1.implementacion;


import java.util.HashMap;
import java.util.Map;

import ejercicio1.entidades.Ciudad;
import ejercicio1.entidades.ColaPrioridadImpl;
import ejercicio1.entidades.ConjuntoImpl;
import ejercicio1.entidades.SecuenciaImpl;
import ejercicio1.interfaces.ColaPrioridad;
import ejercicio1.interfaces.Conjunto;
import ejercicio1.interfaces.GrafoDir;
import ejercicio1.interfaces.Secuencia;

public class SistemaFerroviario {

	private GrafoDir<Ciudad> grafo;
	
	public SistemaFerroviario(GrafoDir<Ciudad> grafo) {
		this.grafo = grafo;
	}
	
	public int calcularDistancia(Secuencia<Ciudad> ruta) throws Exception {
		int distancia = 0;
		for(int i=0; i < ruta.longitud()-1; i++) {
			Ciudad origen = ruta.obtener(i);
			Ciudad destino = ruta.obtener(i+1);
			distancia+= calcularDistancia(origen, destino);
		}
		return distancia;
	}
	
	private int calcularDistancia(Ciudad origen, Ciudad destino) throws Exception {
		if(grafo.existeArista(origen, destino)) {
			return grafo.pesoArista(origen, destino);
		} else {
			throw new Exception("SIN RUTA");
		}
	}
	
	public Conjunto<Secuencia<Ciudad>> obtenerRutas(Ciudad origen, Ciudad destino, int maxParadas) {
		Conjunto<Secuencia<Ciudad>> rutas = new ConjuntoImpl<Secuencia<Ciudad>>();
		rutas.inicializar();
		if(grafo.existeVertice(origen) && grafo.existeVertice(destino)) {
			Secuencia<Ciudad> rutaActual = new SecuenciaImpl<Ciudad>();
			rutaActual.inicializar();
			try {
				rutas = obtenerRutas(origen, destino, maxParadas, rutaActual);
			} catch(Exception e) {}
		}
		return rutas;
	}
	
	private Conjunto<Secuencia<Ciudad>> obtenerRutas(Ciudad origen, Ciudad destino, int maxParadas, Secuencia<Ciudad> rutaActual) throws Exception {
		Conjunto<Secuencia<Ciudad>> rutas = new ConjuntoImpl<Secuencia<Ciudad>>();
		rutas.inicializar();
		
		if(grafo.existeArista(origen, destino)) {
			rutaActual.agregar(destino);
			rutas.agregar(copiarSecuencia(rutaActual));
			rutaActual.sacar();
		}
		if(maxParadas > 1) {
			Secuencia<Ciudad> ady = grafo.adyacentes(origen);
			for(int i=0; i<ady.longitud(); i++) {
				Ciudad parada = ady.obtener(i);
				rutaActual.agregar(parada);
				Conjunto<Secuencia<Ciudad>> nuevasRutas = 
						obtenerRutas(parada, destino, maxParadas-1, copiarSecuencia(rutaActual));
				while(!nuevasRutas.vacio()) {
					Secuencia<Ciudad> ruta = nuevasRutas.elegir();
					rutas.agregar(ruta);
					nuevasRutas.sacar(ruta);
				}
				rutaActual.sacar();
			}
		}
		return rutas;
	}
	
	public Conjunto<Secuencia<Ciudad>> obtenerRutasConDistanciaMaxima(Ciudad origen, Ciudad destino, int maxDistancia) {
		Conjunto<Secuencia<Ciudad>> rutas = new ConjuntoImpl<Secuencia<Ciudad>>();
		rutas.inicializar();
		if(grafo.existeVertice(origen) && grafo.existeVertice(destino)) {
			Secuencia<Ciudad> rutaActual = new SecuenciaImpl<Ciudad>();
			rutaActual.inicializar();
			try {
				rutas = obtenerRutasConDistanciaMaxima(origen, destino, maxDistancia, rutaActual);
			} catch(Exception e) {}
		}
		return rutas;
	}
	
	private Conjunto<Secuencia<Ciudad>> obtenerRutasConDistanciaMaxima(Ciudad origen, Ciudad destino, int maxDistancia, Secuencia<Ciudad> rutaActual) throws Exception {
		Conjunto<Secuencia<Ciudad>> rutas = new ConjuntoImpl<Secuencia<Ciudad>>();
		rutas.inicializar();
		
		if(grafo.existeArista(origen, destino) && grafo.pesoArista(origen, destino) < maxDistancia) {
			rutaActual.agregar(destino);
			rutas.agregar(copiarSecuencia(rutaActual));
			rutaActual.sacar();
		}
		if(maxDistancia > 0) {
			Secuencia<Ciudad> ady = grafo.adyacentes(origen);
			for(int i=0; i<ady.longitud(); i++) {
				Ciudad parada = ady.obtener(i);
				rutaActual.agregar(parada);
				maxDistancia-= grafo.pesoArista(origen, parada);
				Conjunto<Secuencia<Ciudad>> nuevasRutas = 
						obtenerRutasConDistanciaMaxima(parada, destino, maxDistancia, copiarSecuencia(rutaActual));
				while(!nuevasRutas.vacio()) {
					Secuencia<Ciudad> ruta = nuevasRutas.elegir();
					rutas.agregar(ruta);
					nuevasRutas.sacar(ruta);
				}
				rutaActual.sacar();
				maxDistancia+= grafo.pesoArista(origen, parada);
			}
		}
		return rutas;
	}
	
	public Secuencia<Ciudad> obtenerRutaMasCorta(Ciudad origen, Ciudad destino) throws Exception {
		Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
		ruta.inicializar();
		if(grafo.existeVertice(origen) && grafo.existeVertice(destino)) {
			Map<String, Integer> distanciaAcum = new HashMap<String, Integer>();
			Map<String, Ciudad> anterior = new HashMap<String, Ciudad>();
			ColaPrioridad<Ciudad> cola = new ColaPrioridadImpl<Ciudad>();
			cola.inicializar();
			Secuencia<Ciudad> vertices = grafo.vertices();
			for(int i=0; i<vertices.longitud(); i++) {
				Ciudad c = vertices.obtener(i);
				anterior.put(c.obtenerNombre(), null);
				if(c.equals(origen)) {
					distanciaAcum.put(c.obtenerNombre(), 0);
				} else {
					distanciaAcum.put(c.obtenerNombre(), Integer.MAX_VALUE);
				}
			}
			cola.agregarElemento(origen, 0);
			while(!cola.vacia()) {
				Ciudad actual = cola.recuperarMinElemento();
				cola.eliminarMinPrioridad();
				Secuencia<Ciudad> ady = grafo.adyacentes(actual);
				for(int i=0; i<ady.longitud(); i++) {
					Ciudad adyacente = ady.obtener(i);
					if(distanciaAcum.get(adyacente.obtenerNombre()) > distanciaAcum.get(actual.obtenerNombre()) + grafo.pesoArista(actual, adyacente)) {
						distanciaAcum.put(adyacente.obtenerNombre(), distanciaAcum.get(actual.obtenerNombre()) + grafo.pesoArista(actual, adyacente));
						anterior.put(adyacente.obtenerNombre(), actual);
						cola.agregarElemento(adyacente, distanciaAcum.get(adyacente.obtenerNombre()));
					}
				}
			}
			Secuencia<Ciudad> aux = new SecuenciaImpl<Ciudad>();
			aux.inicializar();
			Ciudad c = anterior.get(destino.obtenerNombre());
			while(c != null && !c.equals(origen)) {
				aux.agregar(c);
				c = anterior.get(c.obtenerNombre());
			}
			if(c != null && c.equals(origen)) {
				for(int i=aux.longitud()-1; i>=0; i--) {
					ruta.agregar(aux.obtener(i));
				}
				ruta.agregar(destino);
			} else {
				throw new Exception("SIN RUTA");
			}
		}
		return ruta;
	}
	
	private Secuencia<Ciudad> copiarSecuencia(Secuencia<Ciudad> original) {
		Secuencia<Ciudad> copia = new SecuenciaImpl<Ciudad>();
		copia.inicializar();
		try {
			for(int i=0; i<original.longitud(); i++) {
				copia.agregar(original.obtener(i));
			}
		} catch(Exception e){}
		return copia;
	}
}