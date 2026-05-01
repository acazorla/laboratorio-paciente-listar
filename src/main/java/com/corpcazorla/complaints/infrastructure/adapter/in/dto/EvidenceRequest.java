package com.corpcazorla.complaints.infrastructure.adapter.in.dto;

public class EvidenceRequest {
	private Integer evidenceTypeId;
	private String originalName;
	private String storageFileName;
	private String fileExtension;
	private String mimeType;
	private Long fileSizeBytes;
	private String accessUrl;
	private String verificationHash;
	
	public Integer getEvidenceTypeId() {
		return evidenceTypeId;
	}
	public void setEvidenceTypeId(Integer evidenceTypeId) {
		this.evidenceTypeId = evidenceTypeId;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getStorageFileName() {
		return storageFileName;
	}
	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Long getFileSizeBytes() {
		return fileSizeBytes;
	}
	public void setFileSizeBytes(Long fileSizeBytes) {
		this.fileSizeBytes = fileSizeBytes;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	public String getVerificationHash() {
		return verificationHash;
	}
	public void setVerificationHash(String verificationHash) {
		this.verificationHash = verificationHash;
	}
	
}
