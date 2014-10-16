package cl.masvida.poc.rcm;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

import cl.masvida.poc.rcm.vo.RcmVO;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
		CDIMixIn.class, HTTPMixIn.class })
public class RcmServiceMixinTest {

	private SwitchYardTestKit testKit;
	private CDIMixIn cdiMixIn;
	private HTTPMixIn httpMixIn;
	@ServiceOperation("RcmService")
	private Invoker service;

	@Test
	public void testBuscarPorFolio() throws Exception {
		// TODO Auto-generated method stub
		// initialize your test message
		String message = null;
		RcmVO result = service.operation("buscarPorFolio").sendInOut(message)
				.getContent(RcmVO.class);

		// validate the results
		Assert.assertTrue("Implement me", false);
	}

}
