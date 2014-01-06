package ejercicio1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ejercicio1.entidades.Ciudad;
import ejercicio1.entidades.GrafoDirImpl;
import ejercicio1.entidades.SecuenciaImpl;
import ejercicio1.implementacion.SistemaFerroviario;
import ejercicio1.interfaces.Conjunto;
import ejercicio1.interfaces.GrafoDir;
import ejercicio1.interfaces.Secuencia;

public class Principal {

	private SistemaFerroviario sistemaFerroviario;
	
	@Before
	public void setUp() throws Exception {
		GrafoDir<Ciudad> grafo = completarGrafo();
		sistemaFerroviario = new SistemaFerroviario(grafo);
	}

	private GrafoDir<Ciudad> completarGrafo(){
		GrafoDir<Ciudad> grafo = new GrafoDirImpl<Ciudad>();
		grafo.inicializar();
			
		grafo.agregarVertice(new Ciudad("A"));
		grafo.agregarVertice(new Ciudad("B"));
		grafo.agregarVertice(new Ciudad("C"));
		grafo.agregarVertice(new Ciudad("D"));
		grafo.agregarVertice(new Ciudad("E"));
		
		grafo.agregarArista(new Ciudad("A"), new Ciudad("B"), 5);
		grafo.agregarArista(new Ciudad("B"), new Ciudad("C"), 4);
		grafo.agregarArista(new Ciudad("C"), new Ciudad("D"), 8);
		grafo.agregarArista(new Ciudad("D"), new Ciudad("C"), 8);
		grafo.agregarArista(new Ciudad("D"), new Ciudad("E"), 6);
		grafo.agregarArista(new Ciudad("A"), new Ciudad("D"), 5);
		grafo.agregarArista(new Ciudad("C"), new Ciudad("E"), 2);
		grafo.agregarArista(new Ciudad("E"), new Ciudad("B"), 3);
		grafo.agregarArista(new Ciudad("A"), new Ciudad("E"), 7);
			
		return grafo;
	}

	private Secuencia<Ciudad> completarRuta_1() {
		Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
		ruta.inicializar();
		ruta.agregar(new Ciudad("A"));
		ruta.agregar(new Ciudad("B"));
		ruta.agregar(new Ciudad("C"));
		return ruta;
	}
	private Secuencia<Ciudad> completarRuta_2() {
		Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
		ruta.inicializar();
		ruta.agregar(new Ciudad("A"));
		ruta.agregar(new Ciudad("D"));
		return ruta;
	}
	private Secuencia<Ciudad> completarRuta_3() {
		Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
		ruta.inicializar();
		ruta.agregar(new Ciudad("A"));
		ruta.agregar(new Ciudad("D"));
		ruta.agregar(new Ciudad("C"));
		return ruta;
	}
	private Secuencia<Ciudad> completarRuta_4() {
		Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
		ruta.inicializar();
		ruta.agregar(new Ciudad("A"));
		ruta.agregar(new Ciudad("E"));
		ruta.agregar(new Ciudad("B"));
		ruta.agregar(new Ciudad("C"));
		ruta.agregar(new Ciudad("D"));
		return ruta;
	}
	private Secuencia<Ciudad> completarRuta_5() {
		Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
		ruta.inicializar();
		ruta.agregar(new Ciudad("A"));
		ruta.agregar(new Ciudad("E"));
		ruta.agregar(new Ciudad("D"));
		return ruta;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void caso1() {
		Secuencia<Ciudad> ruta = completarRuta_1();
		try {
			assertEquals(9, sistemaFerroviario.calcularDistancia(ruta));
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void caso2() {
		Secuencia<Ciudad> ruta = completarRuta_2();
		try {
			assertEquals(5, sistemaFerroviario.calcularDistancia(ruta));
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void caso3() {
		Secuencia<Ciudad> ruta = completarRuta_3();
		try {
			assertEquals(13, sistemaFerroviario.calcularDistancia(ruta));
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void caso4() {
		Secuencia<Ciudad> ruta = completarRuta_4();
		try {
			assertEquals(22, sistemaFerroviario.calcularDistancia(ruta));
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Test(expected=Exception.class)
	public void caso5() throws Exception {
		Secuencia<Ciudad> ruta = completarRuta_5();
		try {
			sistemaFerroviario.calcularDistancia(ruta);
			fail("Se esperaba una excepcion.");
		} catch(Exception e) {
			assertEquals("SIN RUTA", e.getMessage());
			throw new Exception();
		}
	}
	
	@Test
	public void caso6() {
		Conjunto<Secuencia<Ciudad>> rutas = 
				sistemaFerroviario.obtenerRutas(new Ciudad("C"), new Ciudad("C"), 3);
		int cant=0;
		while(!rutas.vacio()) {
			Secuencia<Ciudad> ruta = rutas.elegir();
			cant++;
			rutas.sacar(ruta);
		}
		assertEquals(2, cant);
	}
	
	@Test
	public void caso7() {
		Conjunto<Secuencia<Ciudad>> rutas = 
				sistemaFerroviario.obtenerRutas(new Ciudad("A"), new Ciudad("C"), 4);
		int cant=0;
		while(!rutas.vacio()) {
			Secuencia<Ciudad> ruta = rutas.elegir();
			if(ruta.longitud() == 4)
				cant++;
			rutas.sacar(ruta);
		}
		assertEquals(3, cant);
	}

	@Test
	public void caso8() {
		try {
			Secuencia<Ciudad> aux = 
					sistemaFerroviario.obtenerRutaMasCorta(new Ciudad("A"), new Ciudad("C"));
			// La ruta que devuelve no incluye el origen. 
			// Para calcular la distancia, tengo que agregarlo.
			Secuencia<Ciudad> ruta = new SecuenciaImpl<Ciudad>();
			ruta.inicializar();
			ruta.agregar(new Ciudad("A"));
			for(int i=0; i<aux.longitud(); i++) {
				ruta.agregar(aux.obtener(i));
			}
			assertEquals(9, sistemaFerroviario.calcularDistancia(ruta));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void caso9() {
		try {
			Secuencia<Ciudad> ruta = 
					sistemaFerroviario.obtenerRutaMasCorta(new Ciudad("B"), new Ciudad("B"));
			System.out.println(ruta);
			assertEquals(9, sistemaFerroviario.calcularDistancia(ruta));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void caso10() {
		Conjunto<Secuencia<Ciudad>> rutas = 
				sistemaFerroviario.obtenerRutasConDistanciaMaxima(new Ciudad("C"), new Ciudad("C"), 30);
		int cant=0;
		while(!rutas.vacio()) {
			Secuencia<Ciudad> ruta = rutas.elegir();
			cant++;
			rutas.sacar(ruta);
		}
		assertEquals(7, cant);
	}
}
