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
import entity.Categoria;
import error.ServerErrorException;
import usecase.ICrudSQL;
import usecase.ISelectorTagEjecucion;

@Named
@ViewScoped

public class CategoriaMetodos extends Conexion implements Serializable {

	//private static final String TABLA = Categoria.class.getSimpleName();
	private static final String ENTIDAD = "Categoria";
	private static final String MENSAJE_INSERT = ENTIDAD + " AGREGADO";
	private static final String MENSAJE_UPDATE = ENTIDAD + " ACTUALIZADO";
	private static final String MENSAJE_DELETE = ENTIDAD + " ELIMINADO";
	private static final String MENSAJE_SERVER_ERROR = "ERROR DE SERVIDOR";
	private static final String MENSAJE_ERROR_CATCH = "ALGO SALIO MAL";

	private static final long serialVersionUID = 1L;

	private List<Categoria> categoriaGrid;
	private List<Categoria> categoriaGridFilter;
	private List<Categoria> categoriaSeleccionados;
	private Categoria categoriaSeleccionado;
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
		this.categoriaGrid = new ArrayList<Categoria>();
		this.categoriaGridFilter = new ArrayList<Categoria>();
		this.posicionIdiomaContenido = new String[10];

		categoriaListado();
	}

	public CategoriaMetodos() {
		super();
	}

	public CategoriaMetodos(ISelectorTagEjecucion iSelectorTagEjecucion, ICrudSQL iCrudSQL, Integer posicionIdioma) {
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

	public List<Categoria> getCategoriaGrid() {
		return categoriaGrid;
	}

	public void setCategoriaGrid(List<Categoria> categoriaGrid) {
		this.categoriaGrid = categoriaGrid;
	}

	public List<Categoria> getCategoriaGridFilter() {
		return categoriaGridFilter;
	}

	public void setCategoriaGridFilter(List<Categoria> categoriaGridFilter) {
		this.categoriaGridFilter = categoriaGridFilter;
	}

	public List<Categoria> getCategoriaSeleccionados() {
		return categoriaSeleccionados;
	}

	public void setCategoriaSeleccionados(List<Categoria> categoriaSeleccionados) {
		this.categoriaSeleccionados = categoriaSeleccionados;
	}

	public Categoria getCategoriaSeleccionado() {
		return categoriaSeleccionado;
	}

	public void setCategoriaSeleccionado(Categoria categoriaSeleccionado) {
		this.categoriaSeleccionado = categoriaSeleccionado;
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
	 * Metodo categoriaListado
	 * <p>
	 * Descripcion: Metodo categoriaListado sirve para traer todos los categoriaes
	 * <p>
	 * Parametros: no maneja parametros
	 */
	public void categoriaListado() {

		try {

			Map<String, Integer> mapParam = new HashMap<String, Integer>();

			JSONArray listCategoriaJSONArray = ApiRepositoryFacturacion.listaCategoria(mapParam);

			for (int i = 0; i < listCategoriaJSONArray.length(); i++) {
				
				JSONObject jsonObj = listCategoriaJSONArray.getJSONObject(i);

				Categoria categoriaVoid = new Categoria();
				categoriaVoid.setId_categoria(jsonObj.getInt("id_categoria"));
				categoriaVoid.setNombreCategoria(jsonObj.getString("nombreCategoria"));
				categoriaVoid.setDescripcionCategoria(jsonObj.getString("descripcionCategoria"));
			

				this.categoriaGrid.add(categoriaVoid);
				this.categoriaGridFilter.add(categoriaVoid);

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
	 * Metodo categoriaListadoId
	 * <p>
	 * Descripcion: Metodo categoriaListadoId sirve para traer un categoria en especifico
	 * <p>
	 * Parametros: no maneja parametros
	 */
	public void categoriaListadoId() {

		try {

			Map<String, Integer> mapParam = new HashMap<String, Integer>();
			
			//ID del categoria a solicitar
			int id_categoria = categoriaSeleccionado.getId_categoria();
			mapParam.put("id_categoria", id_categoria);
			
			JSONArray listCategoriaJSONArray = ApiRepositoryFacturacion.listaCategoriaId(mapParam);

			for (int i = 0; i < listCategoriaJSONArray.length(); i++) {
				JSONObject jsonObj = listCategoriaJSONArray.getJSONObject(i);

				Categoria categoriaVoid = new Categoria();
				categoriaVoid.setId_categoria(jsonObj.getInt("id_categoria"));
				categoriaVoid.setNombreCategoria(jsonObj.getString("nombreCategoria"));
				categoriaVoid.setDescripcionCategoria(jsonObj.getString("descripcionCategoria"));
			

				this.categoriaGrid.add(categoriaVoid);
				this.categoriaGridFilter.add(categoriaVoid);
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
	 * Metodo categoriaListadoPaginado
	 * <p>
	 * Descripcion: Metodo categoriaListadoPaginado sirve para traer cierta cantidad de lista del categoria.
	 * <p>
	 * Parametros: no maneja parametros
	 */
	public void categoriaListadoPaginado() {

		try {

			Map<String, Integer> mapParam = new HashMap<String, Integer>();

			JSONArray listCategoriaJSONArray = ApiRepositoryFacturacion.listaCategoriaPaginado(mapParam);

			for (int i = 0; i < listCategoriaJSONArray.length(); i++) {
				JSONObject jsonObj = listCategoriaJSONArray.getJSONObject(i);

				Categoria categoriaVoid = new Categoria();
				categoriaVoid.setId_categoria(jsonObj.getInt("id_categoria"));
				categoriaVoid.setNombreCategoria(jsonObj.getString("nombreCategoria"));
				categoriaVoid.setDescripcionCategoria(jsonObj.getString("descripcionCategoria"));
			

				this.categoriaGrid.add(categoriaVoid);
				this.categoriaGridFilter.add(categoriaVoid);


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
	 * M�todo abrirNuevoCategoria
	 * <p>
	 * Descripci�n: El m�todo abrirNuevoCategoria sirve para preparar un nuevo categoria
	 * <p>
	 * Par�metros: no maneja par�metros
	 */
	public void abrirNuevoCategoria() {
		this.categoriaSeleccionado = new Categoria();
		// this.posicionIdiomaContenido = new String[10];
	}

	public void cargarCategoria(Categoria mdd) {
		this.categoriaSeleccionado = mdd;
		// this.posicionIdiomaContenido = new String[10];
	}

	/**
	 * M�todo editarCategoria
	 * <p>
	 * Descripci�n: El m�todo editarCategoria sirve para preparar la edici�n de
	 * categoria
	 * <p>
	 * Par�metros:
	 * <p>
	 * Nombre / Tipo: categoria / Categoria
	 */
	public void editarCategoria(Categoria categoria) {
		// cargarIdiomaListado(categoria.codigo_traduccionCategoria);
		// TODO: Falta implementar
	}

	/**
	 * M�todo categoriaInsert
	 * <p>
	 * Descripci�n: El m�todo categoriaInsert sirve para agregar un nuevo categoria a la
	 * bd
	 * <p>
	 * Par�metros: no maneja par�metros
	 */

	public void categoriaInsert() {

		boolean isInsert = false;

		try {

			if (this.categoriaSeleccionado.getId_categoria() == null) {

				// Insertar Categoria
				Map<String, Object> mapParam = new HashMap<String, Object>();
				// variables

				mapParam.put("nombreCategoria", categoriaSeleccionado.getNombreCategoria());
				mapParam.put("descripcionCategoria", categoriaSeleccionado.getDescripcionCategoria());
				

				ApiRepositoryFacturacion.insertarCategoria(mapParam);

				isInsert = true;

			} else {

				// Editar Categoria
				Map<String, Object> mapParam = new HashMap<String, Object>();

				mapParam.put("id_categoria", categoriaSeleccionado.getId_categoria());

				mapParam.put("nombreCategoria", categoriaSeleccionado.getNombreCategoria());
				mapParam.put("descripcionCategoria", categoriaSeleccionado.getDescripcionCategoria());

			}

			if (isInsert) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_INSERT));
				categoriaListado();

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
			PrimeFaces.current().executeScript("PF('wdialogo_categoriaDetalle').hide()");
			PrimeFaces.current().ajax().update("form:messages", "form:dt-categoria");
		}
	}

	/**
	 * M�todo categoriaDelete
	 * <p>
	 * Descripci�n: El m�todo categoriaDelete sirve para eliminar un categoria en la bd
	 * <p>
	 * Par�metros: no maneja par�metros
	 */
	public void categoriaDelete() {

		try {

			// Eliminar Categoria
			Map<String, Object> mapParam = new HashMap<String, Object>();

			mapParam.put("id_categoria", categoriaSeleccionado.getId_categoria());

			ApiRepositoryFacturacion.EliminarCategoria(mapParam);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_DELETE));
			categoriaListado();

		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		} finally {

			PrimeFaces.current().ajax().update("form:messages", "form:dt-categoria", "form:delete-categoria-button");
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
		if (hasSelectedCategorias()) {
			int size = this.categoriaSeleccionados.size();
			return size > 1 ? size + " " + ENTIDAD + " seleccionados" : "1 " + ENTIDAD + " seleccionado";
		}

		return "Eliminar";
	}

	/**
	 * M�todo hasSelectedCategorias
	 * <p>
	 * Descripcion: El metodo hasSelectedCategorias sirve para determinar sis existen
	 * elementos seleccionados
	 * <p>
	 * Tipo m�todo: boolean indica si existen elementos seleccionados
	 * <p>
	 * Par�metros: no maneja parametros
	 */
	public boolean hasSelectedCategorias() {
		return this.categoriaSeleccionados != null && !this.categoriaSeleccionados.isEmpty();
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
	public void eliminarCategoriasSeleccionados() {

		try {

			for (Categoria categoria : this.categoriaSeleccionados) {

				// Eliminar Categoria
				Map<String, Object> mapParam = new HashMap<String, Object>();

				mapParam.put("id_categoria", categoria.getId_categoria());

				ApiRepositoryFacturacion.EliminarCategoria(mapParam);

			}

			this.categoriaGrid.removeAll(this.categoriaSeleccionados);
			this.categoriaGridFilter.removeAll(this.categoriaSeleccionados);
			this.categoriaSeleccionados = null;

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_DELETE));
			
		} catch (ServerErrorException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_SERVER_ERROR));
			e.printStackTrace();
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(MENSAJE_ERROR_CATCH));
			e.printStackTrace();

		} finally {

		}

		PrimeFaces.current().ajax().update("form:messages", "form:dt-categoria");
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

		Categoria categoria = (Categoria) value;
		return categoria.getNombreCategoria().toLowerCase().contains(filterText);
	}

}