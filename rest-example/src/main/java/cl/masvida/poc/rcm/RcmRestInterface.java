package cl.masvida.poc.rcm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cl.masvida.poc.rcm.vo.RcmVO;

@Path("/rcm")
public interface RcmRestInterface {

	@GET
	@Path("/buscar/{folio}&{fecha}&{fecha2}")
	@Produces(MediaType.APPLICATION_JSON)
	public RcmVO buscarPorFolio(@PathParam("folio") String folio,
			@PathParam("fecha") String fecha,
			@PathParam("fecha2") String fecha2
			);
	
	@POST
	@Path("/buscar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RcmVO buscarPorFolioPost(RcmVO rcmVO);

}
