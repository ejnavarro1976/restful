package es.gfi.model.gedeon.v1.document;

import java.io.Serializable;

public class GedeonV1BodyStreamingResponseBody implements Serializable {

	
	private static final long serialVersionUID = -5555155400881012875L;
	
	private String name;
	
	private byte[]out;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getOut() {
		return out;
	}

	public void setOut(byte[] out) {
		this.out = out;
	}
	
	
	
}
