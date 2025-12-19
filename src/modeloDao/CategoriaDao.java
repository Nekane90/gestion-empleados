package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modeloDto.CategoriaDto;
import conexion.Conexion;




public class CategoriaDao {
	
	private static final String SQL_FINDALL="SELECT * FROM categorias";

	
	
	
	private Conexion con = Conexion.getInstancia();
	
	
	//Devuelve la lista de todos las categorias de la tabla
	
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
