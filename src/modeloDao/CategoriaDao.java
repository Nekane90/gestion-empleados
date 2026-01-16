package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modeloDto.CategoriaDto;
import conexion.Conexion;



/**
 * Clase DAO encargada de gestionar
 * el acceso a la tabla "categorias" de la base de datos.
 * 
 * Proporciona métodos para obtener información de las categorías.
 * 
 * @author Nekane y Maialen
 * @version 1.0
 */
public class CategoriaDao {
	/**
	 * Consulta SQL que obtiene todas las categorías
	 * de la tabla categorias.
	 */
	private static final String SQL_FINDALL="SELECT * FROM categorias";

	
	/**
	 * Instancia de la conexión a la base de datos.
	 */
	
	private Conexion con = Conexion.getInstancia();
	
	
	//Devuelve la lista de todos las categorias de la tabla
	/**
	 * Devuelve una lista con todas las categorías
	 * almacenadas en la base de datos.
	 * 
	 * @return ArrayList<CategoriaDto> lista de categorías
	 */
	
		public ArrayList<CategoriaDto> listarTodos() {
			ArrayList<CategoriaDto> listaCat = new ArrayList<CategoriaDto>();
			try {
				PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
				ResultSet rs = ps.executeQuery();
				while ( rs.next()) {
					CategoriaDto cat = new CategoriaDto(rs.getInt("idCategoria"), rs.getString("nombre"));
					listaCat.add(cat);
				}
				rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return listaCat;
			
		}
	
	
	
	
	
}
