package cl.masvida.poc.rcm.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.masvida.vo.AgenciaVO;
import com.redhat.masvida.vo.RcmVO;
import com.redhat.masvida.vo.TipoPagoVO;

@Path("/rcm")
public interface RcmRestInterface {

	@GET
	@Path("/buscar/{folio}")
	@Produces(MediaType.APPLICATION_JSON)
	public RcmVO buscarPorFolio(@PathParam("folio") String folio
			);
	
	@GET
	@Path("/agencias")
	@Produces(MediaType.APPLICATION_JSON)
	public AgenciaVO[] obtenerAgencias();
	
	@GET
	@Path("/tipospago")
	@Produces(MediaType.APPLICATION_JSON)
	public TipoPagoVO[] obtenerTipoPago();
	
	@POST
	@Path("/buscar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RcmVO buscarPorFolioPost(RcmVO rcmVO);
	
	@POST
	@Path("/guardar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarRcm(RcmVO rcmVO);

}
