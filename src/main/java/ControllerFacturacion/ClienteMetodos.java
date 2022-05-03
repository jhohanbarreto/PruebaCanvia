package ControllerFacturacion;


import java.io.Serializable;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.json.JSONArray;
import org.json.JSONObject;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.LangUtils;

import data_sources.ApiRepositoryFacturacion;
import entity.Cliente;
import error.ServerErrorException;
import usecase.ICrudSQL;
import usecase.ISelectorTagEjecucion;

@Named
@ViewScoped

public class ClienteMetodos extends Conexion implements Serializable {

	//private static final String TABLA = Cliente.class.getSimpleName();
	private static final String ENTIDAD = "Cliente";
	private static final String MENSAJE_INSERT = ENTIDAD + " AGREGADO";
	private static final String MENSAJE_UPDATE = ENTIDAD + " ACTUALIZADO";
	private static final String MENSAJE_DELETE = ENTIDAD + " ELIMINADO";
	private static final String MENSAJE_SERVER_ERROR = "ERROR DE SERVIDOR";
	private static final String MENSAJE_ERROR_CATCH = "ALGO SALIO MAL";

	private static final long serialVersionUID = 1L;

	private List<Cliente> clienteGrid;
	private List<Cliente> clienteGridFilter;
	private List<Cliente> clienteSeleccionados;
	private Cliente clienteSeleccionado;
	String[] posicionIdiomaContenido;

	Integer posicionIdioma = 1;

	ICrudSQL iCrudSQL;
	ISelectorTagEjecucion iSelectorTagEjecucion;

	UploadedFile file;


	// -----------------------------------------------------------------------
	// -------------------- INICIALIZAR VARIBALES ----------------------------
	// -----------------------------------------------------------------------

	@PostConstruct
	public void init() {
		this.iCrudSQL = new CrudSQLMetodos();
		this.clienteGrid = new ArrayList<Cliente>();
		this.clienteGridFilter = new ArrayList<Cliente>();
		this.posicionIdiomaContenido = new String[10];

		clienteListado();
	}

	public ClienteMetodos() {
		super();
	}

	public ClienteMetodos(ISelectorTagEjecucion iSelectorTagEjecucion, ICrudSQL iCrudSQL, Integer posicionIdioma) {
		super();
		this.iSelectorTagEjecucion = iSelectorTagEjecucion;
		this.iCrudSQL = iCrudSQL;
		this.posicionIdioma = posicionIdioma;
	}

	// -----------------------------------------------------------------------
	// -----------------------------------------------------------------------
	// -----------------------------------------------------------------------

	// -----------------------------------------------------------------------
	// ---------------------- GETT AND SETTERS -------------------------------
	// -----------------------------------------------------------------------

	public List<Cliente> getClienteGrid() {
		return clienteGrid;
	}

	public void setClienteGrid(List<Cliente> clienteGrid) {
		this.clienteGrid = clienteGrid;
	}

	public List<Cliente> getClienteGridFilter() {
		return clienteGridFilter;
	}

	public void setClienteGridFilter(List<Cliente> clienteGridFilter) {
		this.clienteGridFilter = clienteGridFilter;
	}

	public List<Cliente> getClienteSeleccionados() {
		return clienteSeleccionados;
	}

	public void setClienteSeleccionados(List<Cliente> clienteSeleccionados) {
		this.clienteSeleccionados = clienteSeleccionados;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Integer getPosicionIdioma() {
		return posicionIdioma;
	}

	public void setPosicionIdioma(Integer posicionIdioma) {
	}

	public String[] getPosicionIdiomaContenido() {
		return posicionIdiomaContenido;
	}

	public void setPosicionIdiomaContenido(String[] posicionIdiomaContenido) {
		this.posicionIdiomaContenido = posicionIdiomaContenido;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------

	// ------------------------------------------------------------------------
	// ------------------------- LISTADOS -------------------------------------
	// ------------------------------------------------------------------------

	/**
	 * Metodo clienteListado
	 * <p>
	 * Descripcion: Metodo clienteListado sirve para traer todos los clientees
	 * <p>
	 * Parametros: no maneja parametros
	 */
	public void clienteListado() {

		try {

			Map<String, Integer> mapParam = new HashMap<String, Integer>();

			JSONArray listClienteJSONArray = ApiRepositoryFacturacion.listaCliente(mapParam);

			for (int i = 0; i < listClienteJSONArray.length(); i++) {
				JSONObject jsonObj = listClienteJSONArray.getJSONObject(i);

				Cliente clienteVoid = new Cliente();
				clienteVoid.setId_cliente(jsonObj.getInt("id_cliente"));
				clienteVoid.setApellidoCliente(jsonObj.getString("apellidoCliente"));
				clienteVoid.setDireccion(jsonObj.getString("direccion"));
				clienteVoid.setEmail(jsonObj.getString("email"));
				clienteVoid.setNombreCliente(jsonObj.getString("nombreCliente"));
				clienteVoid.setTelefono(jsonObj.getInt("telefono"));

				this.clienteGrid.add(clienteVoid);
				this.clienteGridFilter.add(clienteVoid);

			}

		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		}

	}
	
	/**
	 * Metodo clienteListadoId
	 * <p>
	 * Descripcion: Metodo clienteListadoId sirve para traer un cliente especifico
	 * <p>
	 * Parametros: no maneja parametros
	 */
	public void clienteListadoId() {

		try {

			Map<String, Integer> mapParam = new HashMap<String, Integer>();
			
			//ID del cliente a solicitar
			int id_cliente = clienteSeleccionado.getId_cliente();
			mapParam.put("id_cliente", id_cliente);
			
			JSONArray listClienteJSONArray = ApiRepositoryFacturacion.listaClienteId(mapParam);

			for (int i = 0; i < listClienteJSONArray.length(); i++) {
				JSONObject jsonObj = listClienteJSONArray.getJSONObject(i);

				Cliente clienteVoid = new Cliente();
				clienteVoid.setId_cliente(jsonObj.getInt("id_cliente"));
				clienteVoid.setApellidoCliente(jsonObj.getString("apellidoCliente"));
				clienteVoid.setDireccion(jsonObj.getString("direccion"));
				clienteVoid.setEmail(jsonObj.getString("email"));
				clienteVoid.setNombreCliente(jsonObj.getString("nombreCliente"));
				clienteVoid.setTelefono(jsonObj.getInt("telefono"));

				this.clienteGrid.add(clienteVoid);
				this.clienteGridFilter.add(clienteVoid);

			}

		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		}

	}
	
	
	/**
	 * Metodo clienteListadoPaginado
	 * <p>
	 * Descripcion: Metodo clienteListadoPaginado sirve para traer cierta cantidad de lista del cliente.
	 * <p>
	 * Parametros: no maneja parametros
	 */
	public void clienteListadoPaginado() {

		try {

			Map<String, Integer> mapParam = new HashMap<String, Integer>();

			JSONArray listClienteJSONArray = ApiRepositoryFacturacion.listaClientePaginado(mapParam);

			for (int i = 0; i < listClienteJSONArray.length(); i++) {
				JSONObject jsonObj = listClienteJSONArray.getJSONObject(i);

				Cliente clienteVoid = new Cliente();
				clienteVoid.setId_cliente(jsonObj.getInt("id_cliente"));
				clienteVoid.setApellidoCliente(jsonObj.getString("apellidoCliente"));
				clienteVoid.setDireccion(jsonObj.getString("direccion"));
				clienteVoid.setEmail(jsonObj.getString("email"));
				clienteVoid.setNombreCliente(jsonObj.getString("nombreCliente"));
				clienteVoid.setTelefono(jsonObj.getInt("telefono"));

				this.clienteGrid.add(clienteVoid);
				this.clienteGridFilter.add(clienteVoid);

			}

		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		}

	}

	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------

	// ------------------------------------------------------------------------
	// ---------------------- MANTENIMIENTO(INSERT, UPDATE, DELETE) -----------
	// ------------------------------------------------------------------------

	/**
	 * M�todo abrirNuevoCliente
	 * <p>
	 * Descripci�n: El m�todo abrirNuevoCliente sirve para preparar un nuevo cliente
	 * <p>
	 * Par�metros: no maneja par�metros
	 */
	public void abrirNuevoCliente() {
		this.clienteSeleccionado = new Cliente();
		// this.posicionIdiomaContenido = new String[10];
	}

	public void cargarCliente(Cliente mdd) {
		this.clienteSeleccionado = mdd;
		// this.posicionIdiomaContenido = new String[10];
	}

	/**
	 * M�todo editarCliente
	 * <p>
	 * Descripci�n: El m�todo editarCliente sirve para preparar la edici�n de
	 * cliente
	 * <p>
	 * Par�metros:
	 * <p>
	 * Nombre / Tipo: cliente / Cliente
	 */
	public void editarCliente(Cliente cliente) {
		// cargarIdiomaListado(cliente.codigo_traduccionCliente);
		// TODO: Falta implementar
	}

	/**
	 * M�todo clienteInsert
	 * <p>
	 * Descripci�n: El m�todo clienteInsert sirve para agregar un nuevo cliente a la
	 * bd
	 * <p>
	 * Par�metros: no maneja par�metros
	 */

	public void clienteInsert() {

		boolean isInsert = false;

		try {

			if (this.clienteSeleccionado.getId_cliente() == null) {

				// Insertar Cliente
				Map<String, Object> mapParam = new HashMap<String, Object>();
				// variables

				mapParam.put("nombreCliente", clienteSeleccionado.getNombreCliente());
				mapParam.put("apellidoCliente", clienteSeleccionado.getApellidoCliente());
				mapParam.put("direccion", clienteSeleccionado.getDireccion());
				mapParam.put("email", clienteSeleccionado.getEmail());
				mapParam.put("telefono", clienteSeleccionado.getTelefono());

				ApiRepositoryFacturacion.insertarCliente(mapParam);

				isInsert = true;

			} else {

				// Editar Cliente
				Map<String, Object> mapParam = new HashMap<String, Object>();

				mapParam.put("id_cliente", clienteSeleccionado.getId_cliente());

				mapParam.put("nombreCliente", clienteSeleccionado.getNombreCliente());
				mapParam.put("apellidoCliente", clienteSeleccionado.getApellidoCliente());
				mapParam.put("direccion", clienteSeleccionado.getDireccion());
				mapParam.put("email", clienteSeleccionado.getEmail());
				mapParam.put("telefono", clienteSeleccionado.getTelefono());

				ApiRepositoryFacturacion.editarCliente(mapParam);

			}

			if (isInsert) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_INSERT));
				clienteListado();

			} else {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_UPDATE));
			}

		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		} finally {
			this.Desconectar();
			PrimeFaces.current().executeScript("PF('wdialogo_clienteDetalle').hide()");
			PrimeFaces.current().ajax().update("form:messages", "form:dt-cliente");
		}
	}

	/**
	 * M�todo clienteDelete
	 * <p>
	 * Descripci�n: El m�todo clienteDelete sirve para eliminar un cliente en la bd
	 * <p>
	 * Par�metros: no maneja par�metros
	 */
	public void clienteDelete() {

		try {

			// Eliminar Cliente
			Map<String, Object> mapParam = new HashMap<String, Object>();

			mapParam.put("id_cliente", clienteSeleccionado.getId_cliente());

			ApiRepositoryFacturacion.EliminarCliente(mapParam);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_DELETE));
			clienteListado();

		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		} finally {

			PrimeFaces.current().ajax().update("form:messages", "form:dt-cliente", "form:delete-cliente-button");
		}
	}

	/**
	 * Metodo getEliminarBotonMensaje
	 * <p>
	 * Descripcion: El metodo getEliminarBotonMensaje sirve para mostrar la cantidad
	 * de elementos a eliminar
	 * <p>
	 * Tipo m�todo: String cantidad de elementos seleccionados de manera multiple
	 * <p>
	 * Par�metros: no maneja parametros
	 */
	public String getEliminarBotonMensaje() {
		if (hasSelectedClientes()) {
			int size = this.clienteSeleccionados.size();
			return size > 1 ? size + " " + ENTIDAD + " seleccionados" : "1 " + ENTIDAD + " seleccionado";
		}

		return "Eliminar";
	}

	/**
	 * M�todo hasSelectedClientes
	 * <p>
	 * Descripcion: El metodo hasSelectedClientes sirve para determinar sis existen
	 * elementos seleccionados
	 * <p>
	 * Tipo m�todo: boolean indica si existen elementos seleccionados
	 * <p>
	 * Par�metros: no maneja parametros
	 */
	public boolean hasSelectedClientes() {
		return this.clienteSeleccionados != null && !this.clienteSeleccionados.isEmpty();
	}

	/**
	 * M�todo eliminarAlmacenesSeleccionados
	 * <p>
	 * Descripcion: El metodo hasSelectedAlmacenes sirve para determinar sis existen
	 * elementos seleccionados
	 * <p>
	 * Tipo m�todo: boolean indica si existen elementos seleccionados
	 * <p>
	 * Par�metros: no maneja parametros
	 */
	public void eliminarClientesSeleccionados() {

		try {

			for (Cliente cliente : this.clienteSeleccionados) {

				// Eliminar Cliente
				Map<String, Object> mapParam = new HashMap<String, Object>();

				mapParam.put("id_cliente", cliente.getId_cliente());

				ApiRepositoryFacturacion.EliminarCliente(mapParam);

			}

			this.clienteGrid.removeAll(this.clienteSeleccionados);
			this.clienteGridFilter.removeAll(this.clienteSeleccionados);
			this.clienteSeleccionados = null;

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_DELETE));
			
		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		} finally {

		}

		PrimeFaces.current().ajax().update("form:messages", "form:dt-cliente");
	}

	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// -------------------------------- FILTRO ------------------------------
	// ----------------------------------------------------------------------

	/**
	 * M�todo globalFilterFunction
	 * <p>
	 * Descripcion: El metodo globalFilterFunction sirve para saber si existe
	 * coincidencia en la busqueda
	 * <p>
	 * Tipo m�todo: boolean indica si existe coincidencia
	 * <p>
	 * Par�metros: no maneja parametros
	 * <p>
	 * Nombre / Tipo: value / Object
	 * <p>
	 * Descripci�n: Valor ingresado para buscar.
	 * <p>
	 * Nombre / Tipo: filter / Object
	 * <p>
	 * Descripci�n: filtro para realizar segun el valor
	 * <p>
	 * Nombre / Tipo: locale / Locale
	 * <p>
	 * Descripci�n: locale para saber la ubicacion geografica(no se usa en el metodo
	 * pero es necesario para la vista)
	 */
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
		if (LangUtils.isValueBlank(filterText)) {
			return true;
		}

		Cliente cliente = (Cliente) value;
		return cliente.getApellidoCliente().toLowerCase().contains(filterText);
	}

}