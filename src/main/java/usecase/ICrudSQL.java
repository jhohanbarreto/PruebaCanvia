package usecase;
import error.CrudSQLException;

public interface ICrudSQL {
	/**
	 * Devuelve un string con la sentencia SQL SELECT.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarSelectSQL("Producto") returns "SELECT * FROM Producto;"
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param table clase representada en la BD.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	<T> String generarSelectSQL(String table) throws CrudSQLException;

	/**
	 * Devuelve un string con la sentencia SQL SELECT junto con una condicion WHERE.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarSelectSQL(producto, "id_tipoProducto",2) returns "SELECT * FROM Producto WHERE id_tipoProducto = 2;"
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param table clase representada en la BD.
	 * @param where campo id para hacer la condicion(String).
	 * @param value valor de la condicion.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	<T> String generarSelectSQL(String table, String where, Integer value) throws CrudSQLException;
	/**
	 * Devuelve un string con la sentencia SQL SELECT junto con una condicion WHERE.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarSelectSQL(producto, "id_tipoProducto",2) returns "SELECT * FROM Producto WHERE id_tipoProducto = 2;"
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param table clase representada en la BD.
	 * @param where campo id para hacer la condicion(String).
	 * @param value valor de la condicion.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	<T> String generarSelectSQL(String table, String where, T object) throws CrudSQLException;

	/**
	 * Devuelve un string con la sentencia SQL INSERT INTO. Recorre cada campo de la
	 * clase y extrae cada campo y valor.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarInsertarSQL(producto) returns "INSERT INTO Producto(campo1,campo2, campo3) VALUES (valor1,valor2, valor3)";
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param o clase representada en la BD.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	<T> String generarInsertarSQL(T o) throws CrudSQLException;

	/**
	 * Devuelve un string con la sentencia SQL UPDATE. Recorre cada campo de la
	 * clase y extrae cada campo y valor.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarActualizarSQL(producto) returns "UPDATE Producto SET campo1 = valor1, campo2 = valor2, campo3 = valor2 WHERE id = valorx";
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param o clase representada en la BD.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	<T> String generarActualizarSQL(T o) throws CrudSQLException;

	/**
	 * Devuelve un string con la sentencia SQL DELETE. Recorre solo el primer campo
	 * extrae el campo y valor.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarEliminarSQL(producto) returns "DELETE FROM Producto WHERE id = valorx";
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param o clase representada en la BD.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	<T> String generarEliminarSQL(T o) throws CrudSQLException;

	/**
	 * Devuelve un string con la sentencia SQL INSERT INTO. Se debe enviar el nombre
	 * de la table, los campos y los valores.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarInsertarPersonalizadoSQL(producto, ["campo1", "campo2"], ["valor1", "valor2"]) returns "INSERT INTO Producto(campo1,campo2) VALUES (valor1,valor2)";
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param table  nombre de la tabla en la BD.
	 * @param fields campos de la tabla en la BD.
	 * @param values valores que se quieren ingresar a la BD.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	String generarInsertarPersonalizadoSQL(String table, String[] fields, Object[] values) throws CrudSQLException;

	/**
	 * Devuelve un string con la sentencia SQL INSERT INTO. Se debe enviar el nombre
	 * de la table, los campos y los valores.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarInsertarPersonalizadoSQL(producto, ["campo1", "campo2"], ["valor1", "valor2"], true) returns "(campo1,campo2) VALUES (valor1,valor2);";
	 * </pre>
	 * 
	 * <pre>
	 * iCrudSql.generarInsertarPersonalizadoSQL(producto, ["campo1", "campo2"], ["valor1", "valor2"], false) returns "(valor1,valor2),";
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param table  nombre de la tabla en la BD.
	 * @param fields campos de la tabla en la BD.
	 * @param values valores que se quieren ingresar a la BD.
	 * @param flag   indica si retorna campos y valores o solo los valores
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	String generarInsertarPersonalizadoSQL(String table, String[] fields, Object[] values, boolean flag)
			throws CrudSQLException;


	/**
	 * Devuelve un string con la sentencia SQL UPDATE. Se debe enviar el nombre de
	 * la table, los campos y los valores.
	 * <p>
	 * Ejemplo: <blockquote>
	 * 
	 * <pre>
	 * iCrudSql.generarActualizarPersonalizadoSQL(producto, ["campo1", "campo2"], ["valor1", "valor2"]) returns "UPDATE Producto SET campo1 = valor1,campo2 = valor2, campo3 = valor3 WHERE id = valorx";
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param table  nombre de la tabla en la BD.
	 * @param fields campos de la tabla en la BD.
	 * @param values valores que se quieren ingresar a la BD.
	 * @return la sentencia SQL en string.
	 * @exception CrudSQLException si alguno de los parametros es null o en caso la
	 *                             sintaxis sql se vea afeactada
	 */
	String generarActualizarPersonalizadoSQL(String table, String[] fields, Object[] values) throws CrudSQLException;
}
