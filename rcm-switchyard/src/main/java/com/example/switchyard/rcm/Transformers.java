package com.example.switchyard.rcm;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Element;

public final class Transformers {

	@Transformer(to = "{urn:com.example.switchyard:rcm-switchyard:1.0}buscarRcmResponse")
	public String transformStringToBuscarRcmResponse(String from) {
		// TODO Auto-generated method stub
		return "<buscarRcmResponse xmlns=urn:com.example.switchyard:rcm-switchyard:1.0><string>"
        + from + "</string></buscarRcmResponse>";
	}

	@Transformer(from = "{urn:com.example.switchyard:rcm-switchyard:1.0}buscarRcm")
	public String transformBuscarRcmToString(Element from) {
		// TODO Auto-generated method stub
		return from.getTextContent();
	}

}
