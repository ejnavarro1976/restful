package es.gfi.model.gedeon.v1.exception;

import java.io.Serializable;

public class GedeonV1ReponseException implements Serializable {

	private static final long serialVersionUID = -8480896382243030433L;

	private String details;
	private String message;
	private String timestamp;

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
