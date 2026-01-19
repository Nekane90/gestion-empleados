package modeloDto;

import java.util.Objects;
/**
 * DTO  que representa una categoría.
 * Contiene el identificador de la categoría y su nombre.
 * @author Nekane y Maialen
 * @version 1.0
 */
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
	/**
     * Genera el código hash de la categoría.
     *
     * @return valor hash basado en el id y el nombre
     */
	@Override
	public int hashCode() {
		return Objects.hash(idCategoria, nombreCategoria);
	}
	/**
     * Compara dos objetos.
     *
     * @param obj objeto a comparar
     * @return {@code true} si ambos objetos son iguales,
     *         {@code false} en caso contrario
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		CategoriaDto other = (CategoriaDto) obj;
		return idCategoria == other.idCategoria && Objects.equals(nombreCategoria, other.nombreCategoria);
	}


	///Tostring
	/**
     * Devuelve en texto la categoría.
     * <p>
     * Se utiliza principalmente para mostrar el nombre de la categoría
     * en componentes como {@code JComboBox}.
     * </p>
     *
     * @return nombre de la categoría
     */
	 @Override
	public String toString() {
	        return nombreCategoria; // lo que se muestra en el combo
	    }





}
