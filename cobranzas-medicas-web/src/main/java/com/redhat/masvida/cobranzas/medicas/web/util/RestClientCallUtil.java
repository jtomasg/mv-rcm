package com.redhat.masvida.cobranzas.medicas.web.util;

import javax.faces.bean.ManagedBean;
import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@ManagedBean
@Singleton
public class RestClientCallUtil {

	/**
	 * Metodo que permite realizar llamada a servicio de tipo Rest mediante una petición HTTP de tipo GET.
	 * Los parametros son definidos en la URL de la peticion HTTP dado a que esta es de tipo GET.
	 * @param endpointURL
	 * @return String (JSON)
	 * @throws Exception
	 */
	public String callJsonRemoteRest(String endpointURL) throws Exception {

		String strJson = null;
		ClientConfig clientConfig = new DefaultClientConfig();

		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);

		Client client = Client.create(clientConfig);

		WebResource webResource = client.resource(endpointURL);

		ClientResponse response = webResource.
				accept(MediaType.APPLICATION_JSON).
				type(MediaType.APPLICATION_JSON).
				get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		strJson = response.getEntity(String.class);
		
		return strJson;
	}
	
	/**
	 * Metodo que permite realizar llamada a servicio de tipo Rest mediante una petición HTTP de tipo POST.
	 * Toma el parametro de tipo Object y lo serializa, dandole una estructura JSON.
	 * @param endpointURL
	 * @param pojo
	 * @return String (JSON)
	 * @throws Exception
	 */
	public String callJsonRemoteRest(String endpointURL, Object pojo) throws Exception {
        
		String strJson = null;
		
		ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource webResource = client.resource(endpointURL);
        
        ClientResponse response = webResource.
        			type(MediaType.APPLICATION_JSON).
        			accept(MediaType.APPLICATION_JSON).
        			post(ClientResponse.class, pojo);
        
        if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		//String strJson = response.getEntity(String.class);

		System.out.println("Server response .... \n");
		System.out.println(strJson);
        
        return strJson;
    }

}
