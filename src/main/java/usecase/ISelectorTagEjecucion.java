package usecase;

import error.CrudSQLException;

public interface ISelectorTagEjecucion {
	
	String generarConsultaSQL(String codigo_tagEjecucion, Integer positionIdioma) throws CrudSQLException;
}
