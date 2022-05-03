package data_sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import org.json.JSONException;

import error.ServerErrorException;
import utils.UUtils;

public class Http {

	public static String post(String url, Map<String, String> headers, Map<String, Object> body)
			throws IOException, JSONException {
		return "";
	}

	public static String post(String uri, Map<String, ?> body) throws ServerErrorException {

		String respuesta = "";
		try {

			System.out.println("============== REQUEST HTTP ==============");
			System.out.println("URL: "+uri);
			System.out.println("Params: "+body.values());
			
			
			StringBuilder sb;
			
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("charset", "UTF-8");

			String input = UUtils.convertMapToString(body);
			
			System.out.println("============== RESPONSE HTTP ==============");
			OutputStream os = conn.getOutputStream();
			//INSERTAMOS EL UTF-8 PARA LOS CARACTERES ESPECIALES
			os.write(input.getBytes("UTF-8"));
			os.flush();

			System.out.println("CODE: "+conn.getResponseCode());
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {

				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));

			String output;

			sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);

			}
			
			respuesta = sb.toString();

			
			System.out.println("BODY: "+respuesta);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return respuesta;
	}

	public static String get(String url) throws IOException, JSONException {
		// TODO: Falta implementar el GET
		return "";
	}
	
}
