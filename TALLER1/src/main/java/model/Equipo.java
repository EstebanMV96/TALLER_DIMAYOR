package model;

public class Equipo {
	
	
	private String nombre;
	private String anoFundacion;
	private String numTitulos;
	
	
	public Equipo(String nombre, String anoFundacion, String numTitulos) {
		this.nombre = nombre;
		this.anoFundacion = anoFundacion;
		this.numTitulos = numTitulos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAnoFundacion() {
		return anoFundacion;
	}
	public void setAnoFundacion(String anoFundacion) {
		this.anoFundacion = anoFundacion;
	}
	public String getNumTitulos() {
		return numTitulos;
	}
	public void setNumTitulos(String numTitulos) {
		this.numTitulos = numTitulos;
	}
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", anoFundacion=" + anoFundacion + ", numTitulos=" + numTitulos + "]";
	}
	
	

	
	
	

}
