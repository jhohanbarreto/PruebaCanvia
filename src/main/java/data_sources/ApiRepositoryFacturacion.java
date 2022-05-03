package data_sources;

import java.io.IOException;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

import error.ServerErrorException;
import utils.UUtils;

public class ApiRepositoryFacturacion {

	// ------------------------------------------------------------------------
	// ------------------------- Consumiendo Facturación-Cliente
	// -------------------------------------
	// ------------------------------------------------------------------------
	// lista cliente
	public static JSONArray listaCliente(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

		try {
			String resp = Http.post(UUtils.LISTAR_CLIENTE, map);

			JSONArray jsonArr = new JSONArray(resp);

			return jsonArr;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	// lista cliente
		public static JSONArray listaClienteId(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

			try {
				String resp = Http.post(UUtils.LISTAR_CLIENTE_ID, map);

				JSONArray jsonArr = new JSONArray(resp);

				return jsonArr;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		// lista cliente
		public static JSONArray listaClientePaginado(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

			try {
				String resp = Http.post(UUtils.LISTAR_CLIENTE_PAGINADO, map);

				JSONArray jsonArr = new JSONArray(resp);

				return jsonArr;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	
		
	// Insertar cliente
	public static void insertarCliente(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

		try {
			String resp = Http.post(UUtils.INSERTAR_CLIENTE, map);

		} catch (ServerErrorException e) {
			e.printStackTrace();
		}

	}

	// Editar cliente
	public static void editarCliente(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

		try {
			String resp = Http.post(UUtils.EDITAR_CLIENTE, map);

		} catch (ServerErrorException e) {
			e.printStackTrace();
		}

	}
	
	// Eliminar cliente
	public static void EliminarCliente(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

		try {
			String resp = Http.post(UUtils.ELIMINAR_CLIENTE, map);

		} catch (ServerErrorException e) {
			e.printStackTrace();
		}

	}
	
	// ------------------------------------------------------------------------
		// ------------------------- Consumiendo Facturación-Categoria
		// -------------------------------------
		// ------------------------------------------------------------------------
		// lista Categoria
		public static JSONArray listaCategoria(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

			try {
				String resp = Http.post(UUtils.LISTAR_CATEGORIA, map);

				JSONArray jsonArr = new JSONArray(resp);

				return jsonArr;
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		// lista Categoria
			public static JSONArray listaCategoriaId(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

				try {
					String resp = Http.post(UUtils.LISTAR_CATEGORIA_ID, map);

					JSONArray jsonArr = new JSONArray(resp);

					return jsonArr;
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			// lista Categoria
			public static JSONArray listaCategoriaPaginado(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

				try {
					String resp = Http.post(UUtils.LISTAR_CATEGORIA_PAGINADO, map);

					JSONArray jsonArr = new JSONArray(resp);

					return jsonArr;
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}
		
		
			
		// Insertar Categoria
		public static void insertarCategoria(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

			try {
				String resp = Http.post(UUtils.INSERTAR_CATEGORIA, map);

			} catch (ServerErrorException e) {
				e.printStackTrace();
			}

		}

		// Editar Categoria
		public static void editarCategoria(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

			try {
				String resp = Http.post(UUtils.EDITAR_CATEGORIA, map);

			} catch (ServerErrorException e) {
				e.printStackTrace();
			}

		}
		
		// Eliminar Categoria
		public static void EliminarCategoria(Map<String, ?> map) throws ServerErrorException, IOException, JSONException {

			try {
				String resp = Http.post(UUtils.ELIMINAR_CATEGORIA, map);

			} catch (ServerErrorException e) {
				e.printStackTrace();
			}

		}

}
