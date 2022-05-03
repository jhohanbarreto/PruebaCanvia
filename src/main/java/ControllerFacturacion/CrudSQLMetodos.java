package ControllerFacturacion;

import java.lang.reflect.Field;

import error.CrudSQLException;
import usecase.ICrudSQL;

public class CrudSQLMetodos implements ICrudSQL {

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
	@Override
	public <T> String generarSelectSQL(String table) {
		String sqlQuery = "SELECT * FROM " + table + ";";

		System.out.println(sqlQuery);

		return sqlQuery;
	}

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
	@Override
	public <T> String generarSelectSQL(String table, String where, Integer value) throws CrudSQLException {
		String sqlQuery = "";
		try {
			String sqlWhere = " WHERE " + where + " = " + value + ";";
			sqlQuery = "SELECT * FROM " + table;

			sqlQuery = sqlQuery + sqlWhere;

			System.out.println(sqlQuery);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlQuery;

	}

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

	@Override
	public <T> String generarInsertarSQL(T o) throws CrudSQLException {
		String fields = "";
		String values = "";
		String sqlQuery = "INSERT INTO " + o.getClass().getSimpleName();
		boolean isFirst = true;

		for (Field f : o.getClass().getFields()) {
			if (isFirst) {
				isFirst = false;

			} else {
				fields = fields + "," + f.getName();

				if (f.getType().getSimpleName().contentEquals("String")
						|| f.getType().getSimpleName().contentEquals("Date")) {
					try {
						values = values + ",'" + f.get(o) + "'";
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						values = values + "," + f.get(o);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		System.out.print("INSERT INTO " + o.getClass().getSimpleName() + " ( ");
		System.out.print(fields.substring(1));
		System.out.println(") Values (" + values.substring(1) + "),");
		sqlQuery = sqlQuery + " ( " + fields.substring(1) + ") Values (" + values.substring(1) + ");";
		return sqlQuery;
	}

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

	@Override
	public <T> String generarActualizarSQL(T o) throws CrudSQLException {
		String fields = "";
		String sqlWhere = " WHERE ";
		String sqlQuery = "UPDATE " + o.getClass().getSimpleName() + " SET ";

		boolean isFirst = true;
		for (Field f : o.getClass().getFields()) {
			if (isFirst) {
				try {
					sqlWhere = sqlWhere + f.getName() + "=" + f.get(o);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isFirst = false;

			} else {

				String value = "";
				try {
					value = f.get(o) != null ? f.get(o).toString() : null;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (f.getType().getSimpleName().contentEquals("String")
						|| f.getType().getSimpleName().contentEquals("Date")) {
					value = "'" + value + "'";
				}

				fields = fields + "," + f.getName() + " = " + value;
			}

		}

		System.out.print("UPDATE " + o.getClass().getSimpleName() + " SET ");
		System.out.println(fields.substring(1) + sqlWhere + ";");
		sqlQuery = sqlQuery + fields.substring(1) + sqlWhere + ";";
		return sqlQuery;
	}

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
	@Override
	public <T> String generarEliminarSQL(T o) throws CrudSQLException {
		String sqlWhere = " WHERE ";
		String sqlQuery = "DELETE FROM " + o.getClass().getSimpleName();

		for (Field f : o.getClass().getFields()) {

			try {
				sqlWhere = sqlWhere + f.getName() + "=" + f.get(o);
				break;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println("DELETE FROM " + o.getClass().getSimpleName() + sqlWhere + ";");
		sqlQuery = sqlQuery + sqlWhere + ";";
		return sqlQuery;
	}

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
	@Override
	public String generarInsertarPersonalizadoSQL(String table, String[] fields, Object[] values)
			throws CrudSQLException {

		String sqlQuery = "INSERT INTO " + table;
		String strFields = "";
		String strValues = "";

		boolean isFirst = true;

		for (int i = 0; i < values.length; i++) {

			if (isFirst) {
				isFirst = false;
				continue;
			}
			if (values[i] == null)
				continue;

			strFields = strFields + "," + fields[i];
			String strType = values[i].getClass().getSimpleName();

			if (strType.equals("String") || strType.equals("Date")) {
				strValues = strValues + ",'" + values[i] + "'";
			} else {
				strValues = strValues + "," + values[i];
			}

		}

		System.out.println(sqlQuery + " (" + strFields.substring(1) + ") Values (" + strValues.substring(1) + ");");
		sqlQuery = sqlQuery + " (" + strFields.substring(1) + ") Values (" + strValues.substring(1) + ");";
		return sqlQuery;
	}

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
	@Override
	public String generarInsertarPersonalizadoSQL(String table, String[] fields, Object[] values, boolean flag)
			throws CrudSQLException {

		String sqlQuery = "";
		String strFields = "";
		String strValues = "";

		boolean isFirst = true;

		for (int i = 0; i < values.length; i++) {

			if (isFirst) {
				isFirst = false;
				continue;
			}
			if (values[i] == null)
				continue;

			strFields = strFields + "," + fields[i];
			String strType = values[i].getClass().getSimpleName();

			if (strType.equals("String") || strType.equals("Date")) {
				strValues = strValues + ",'" + values[i] + "'";
			} else {
				strValues = strValues + "," + values[i];
			}

		}

		// Retorna campos y valores
		sqlQuery = sqlQuery + " (" + strFields.substring(1) + ") Values (" + strValues.substring(1) + ");";

		// Retorna solo valores
		if (!flag)
			sqlQuery = "(" + strValues.substring(1) + "),";

		System.out.println(sqlQuery);

		return sqlQuery;
	}

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
	@Override
	public String generarActualizarPersonalizadoSQL(String table, String[] fields, Object[] values)
			throws CrudSQLException {

		String sqlQuery = "UPDATE " + table + " SET ";
		String strFields = "";
		String sqlWhere = " WHERE ";
		boolean isFirst = true;

		for (int i = 0; i < values.length; i++) {

			if (isFirst) {
				isFirst = false;
				String strType = values[i].getClass().getSimpleName();
				String value = values[i].toString();

				if (strType.equals("String")) {
					value = "'" + value + "'";
				}
				sqlWhere = sqlWhere + fields[i] + "=" + value;
				continue;
			}

			if (values[i] == null)
				continue;

			String strType = values[i].getClass().getSimpleName();
			String value = "";

			if (strType.equals("String") || strType.equals("Date")) {
				value = "'" + values[i] + "'";
			} else {
				value = values[i].toString();
			}

			strFields = strFields + "," + fields[i] + " = " + value;
		}

		System.out.println(sqlQuery + strFields.substring(1) + sqlWhere + ";");
		sqlQuery = sqlQuery + strFields.substring(1) + sqlWhere + ";";
		return sqlQuery;
	}

	@Override
	public <T> String generarSelectSQL(String table, String where, T object) throws CrudSQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
