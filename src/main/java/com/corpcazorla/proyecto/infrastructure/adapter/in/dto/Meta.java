package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;
//import java.time.Instant;
public class Meta {
    private String timestamp;
    private String version;
    private String traceId;
    
	/*
	 * public Meta() { this.timestamp = Instant.now().toString(); this.version =
	 * "v1"; this.traceId = java.util.UUID.randomUUID().toString(); }
	 */

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
    
}
