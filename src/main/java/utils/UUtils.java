package utils;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.Instant;
import java.util.Map;



enum Longitudes {
	Km, m, cm, mm, in, ft, yd, mi,

}

enum Masas {
	t, kg, g, mg, oz, lb,

}

public class UUtils {

	// ----------------------------------------------------------------
	// --------------------CONSTANTES----------------------------------
	// ----------------------------------------------------------------
	public static final String HOST = "http://localhost:8080/";
	
	public static final String API_FACTURACION = HOST + "WebService-facturaElectrionica/Modulo-Factura/";
	
	// ----------------------------------------------------------------
	// ------------------- ENDPOINTS MODULO FACTURACIÓN-CLIENTE------------------
	// ----------------------------------------------------------------
	
	public static final String LISTAR_CLIENTE = API_FACTURACION + "cliente/listarCliente";
	public static final String LISTAR_CLIENTE_ID = API_FACTURACION + "cliente/listarClienteId";
	public static final String LISTAR_CLIENTE_PAGINADO = API_FACTURACION + "cliente/listarPaginadoCliente";
	public static final String INSERTAR_CLIENTE = API_FACTURACION + "cliente/insertarCliente";
	public static final String EDITAR_CLIENTE = API_FACTURACION + "cliente/editarCliente";
	public static final String ELIMINAR_CLIENTE = API_FACTURACION + "cliente/eliminarCliente";
	
	// ----------------------------------------------------------------
	// ------------------- ENDPOINTS MODULO FACTURACIÓN-CATEGORIA------------------
	// ----------------------------------------------------------------
	
	public static final String LISTAR_CATEGORIA = API_FACTURACION + "categoria/listarCategoria";
	public static final String LISTAR_CATEGORIA_ID = API_FACTURACION + "categoria/listarCategoriaId";
	public static final String LISTAR_CATEGORIA_PAGINADO = API_FACTURACION + "categoria/listarPaginadoCategoria";
	public static final String INSERTAR_CATEGORIA = API_FACTURACION + "categoria/insertarCategoria";
	public static final String EDITAR_CATEGORIA = API_FACTURACION + "categoria/editarCategoria";
	public static final String ELIMINAR_CATEGORIA = API_FACTURACION + "categoria/eliminarCategoria";
	
	

	

	private static volatile SecureRandom numberGenerator = null;
	private static final long MSB = 0x8000000000000000L;


	// ----------------------------------------------------------------
	// ------------------ COMPARACIONES -------------------------------
	// ----------------------------------------------------------------
	public static boolean isKimlometro(String unidadMedida) {
		return unidadMedida.equals(Longitudes.Km.name().toLowerCase());

	}

	public static boolean isMetro(String unidadMedida) {
		return unidadMedida.equals(Longitudes.m.name());

	}

	public static boolean isCentimetro(String unidadMedida) {
		return unidadMedida.equals(Longitudes.cm.name());

	}

	public static boolean isMilimetro(String unidadMedida) {
		return unidadMedida.equals(Longitudes.mm.name());

	}

	public static boolean isPulgada(String unidadMedida) {
		return unidadMedida.equals(Longitudes.in.name());

	}

	public static boolean isPie(String unidadMedida) {
		return unidadMedida.equals(Longitudes.ft.name());

	}

	public static boolean isYarda(String unidadMedida) {
		return unidadMedida.equals(Longitudes.yd.name());

	}

	public static boolean isMilla(String unidadMedida) {
		return unidadMedida.equals(Longitudes.mi.name());

	}

	////////////////////////////// CONVERSIONES ///////////////////

	public static Double kilomteroConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor;
		mFactor = valor * 1000;
		cmFactor = valor * 100000;
		mmFactor = valor * 1000000;
		inFactor = valor * 39370.07874;
		ftFactor = valor * 3280.839895;
		ydFactor = valor * 1093.613298;
		miFactor = valor * 0.621368876;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double metroConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 0.001;
		mFactor = valor;
		cmFactor = valor * 100;
		mmFactor = valor * 1000;
		inFactor = valor * 39.37007874;
		ftFactor = valor * 3.280839895;
		ydFactor = valor * 1.093613298;
		miFactor = valor * 0.000621369;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double centimetroConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 0.00001;
		mFactor = valor * 0.01;
		cmFactor = valor;
		mmFactor = valor * 10;
		inFactor = valor * 0.393700787;
		ftFactor = valor * 0.032808399;
		ydFactor = valor * 0.010936133;
		miFactor = valor * 0.0000062137;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double milimetroConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 0.000001;
		mFactor = valor * 0.001;
		cmFactor = valor * 0.1;
		mmFactor = valor;
		inFactor = valor * 0.039370079;
		ftFactor = valor * 0.00328084;
		ydFactor = valor * 0.001093613;
		miFactor = valor * 0.000000621369;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double pulgadaConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 0.0000254;
		mFactor = valor * 0.0254;
		cmFactor = valor * 2.54;
		mmFactor = valor * 25.4;
		inFactor = valor;
		ftFactor = valor * 0.083333;
		ydFactor = valor * 0.027778;
		miFactor = valor * 0.0000157828;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double pieConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 0.0003048;
		mFactor = valor * 0.3048;
		cmFactor = valor * 30.48;
		mmFactor = valor * 304.8;
		inFactor = valor * 12;
		ftFactor = valor;
		ydFactor = valor * 0.333333333;
		miFactor = valor * 0.000189394;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double yardaConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 0.0009144;
		mFactor = valor * 0.9144;
		cmFactor = valor * 91.44;
		mmFactor = valor * 914.4;
		inFactor = valor * 36;
		ftFactor = valor * 3;
		ydFactor = valor;
		miFactor = valor * 0.000568182;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}

	public static Double millaConvertirX(Double valor, String unidadMedidaConvertir) {

		Double kmFactor, mFactor, cmFactor, mmFactor, inFactor, ftFactor, ydFactor, miFactor = 0.0;

		kmFactor = valor * 1.60935;
		mFactor = valor * 1609.35;
		cmFactor = valor * 160935;
		mmFactor = valor * 1609350;
		inFactor = valor * 63360;
		ftFactor = valor * 5280;
		ydFactor = valor * 1760;
		miFactor = valor;

		if (isKimlometro(unidadMedidaConvertir))
			return kmFactor;

		if (isMetro(unidadMedidaConvertir))
			return mFactor;

		if (isCentimetro(unidadMedidaConvertir))
			return cmFactor;

		if (isMilimetro(unidadMedidaConvertir))
			return mmFactor;

		if (isPulgada(unidadMedidaConvertir))
			return inFactor;

		if (isPie(unidadMedidaConvertir))
			return ftFactor;

		if (isYarda(unidadMedidaConvertir))
			return ydFactor;

		if (isMilla(unidadMedidaConvertir))
			return miFactor;

		return 0.0;
	}


	// -------------------------------------------------------------------
	// ------------------------ CONVERSIONES -----------------------------
	// -------------------------------------------------------------------

	public static String convertMapToString(Map<String, ?> map) {

		if (map.isEmpty())
			return "{}";
		Object value;
		StringBuilder mapAsString = new StringBuilder("{");
		for (String key : map.keySet()) {
//			System.out.println("Es String: "+));
			value = map.get(key);
			if (String.class.isInstance(value) || Date.class.isInstance(value)) {

				mapAsString.append('"' + key + '"' + ":" + '"' + value + '"' + ", ");
			} else {
				mapAsString.append('"' + key + '"' + ":" + value + ", ");
			}
//			System.out.println( map.get(key).getClass().getTypeName());

		}
		mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("}");
		System.out.println("Params:" + mapAsString.toString());
		return mapAsString.toString();
	}

	// -------------------------------------------------------------------
	// ------------------------ GENERADOR CODIGO UNICO -------------------
	// -------------------------------------------------------------------

	public static String unique() {
		SecureRandom ng = numberGenerator;

		String dateString = "" + Instant.now().getEpochSecond();
		if (ng == null) {
			numberGenerator = ng = new SecureRandom();

		}

		return dateString + Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
	}

}
