package com.corpcazorla.complaints.infrastructure.adapter.in.dto;

public class Meta {
    private String timestamp;
    private String version;
    private String traceId;
 // Constructor por defecto para que Jackson no falle
    public Meta() {}

    // Constructor de conveniencia
    public Meta(String version) {
        this.timestamp = java.time.OffsetDateTime.now().toString();
        this.version = version;
        this.traceId = java.util.UUID.randomUUID().toString();
    }
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
