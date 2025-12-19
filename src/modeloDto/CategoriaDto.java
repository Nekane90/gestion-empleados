package modeloDto;

import java.util.Objects;

public class CategoriaDto {

	private int idCategoria;
	private String nombreCategoria;
	
	//Constructores
	
	public CategoriaDto(int idCategoria, String nombreCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
	}

	public CategoriaDto() {
		super();
	}

	//Getters y setters
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	
	//Hashcode y equals
	
	@Override
	public int hashCode() {
		return Objects.hash(idCategoria, nombreCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaDto other = (CategoriaDto) obj;
		return idCategoria == other.idCategoria && Objects.equals(nombreCategoria, other.nombreCategoria);
	}
	
	
	///Tostring
	 public String toString() {
	        return nombreCategoria; // lo que se muestra en el combo
	    }
	
	
	
	
	
}
