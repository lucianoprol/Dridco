package ejercicio1.entidades;

public class Ciudad {

	private String nombre;
	
	public Ciudad(String nombre) {
		this.nombre = nombre;
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
	
	@Override
	public boolean equals(Object elem) {
		Ciudad ciudad = (Ciudad) elem;
		if(!this.nombre.equals(ciudad.obtenerNombre())) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return nombre;
	}
}
