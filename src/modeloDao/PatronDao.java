package modeloDao;

import java.util.ArrayList;

public interface PatronDao<TipoGen> {

		public boolean insertar(TipoGen t); //Insertar un registro (del tipo que sea)
		public boolean borrar(Object pk); //Eliminar un registro referenciado por su PK
		public boolean actualizar(TipoGen t); //Actualizar un registro
		public TipoGen buscar (Object pk); //Devuelve el registro cuya PK se le pasa
		public ArrayList<TipoGen> listarTodos();

	}//Devuelve la lista de todos los registros de la tabla


