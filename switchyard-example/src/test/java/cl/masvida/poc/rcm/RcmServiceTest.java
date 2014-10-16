package cl.masvida.poc.rcm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class })
public class RcmServiceTest {

	@ServiceOperation("RcmService")
	private Invoker service;
	
	private static final String BASE_URL = "http://localhost:8080/rcmRest";

	@Test
	public void testBuscarPorFolio() throws Exception {
		
		HTTPMixIn http = new HTTPMixIn();
		http.initialize();
        
        String folio = "0000";
        String fecha1 = "0000";
        String fecha2 = "0000";
        
		try{
			String response = http.sendString(BASE_URL + "/rcm/buscar/" + folio + ":" + fecha1 + ":" + fecha2, "", HTTPMixIn.HTTP_GET);
			System.out.println(response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
