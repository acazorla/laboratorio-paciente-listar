package com.corpcazorla.complaints.infrastructure.adapter.in.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.math.BigDecimal;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class ComplaintsRequest {
	
	@NotNull(message = "documentTypeId no puede ser nulo")
	private Integer documentTypeId;
	
	@NotBlank(message = "documentNumber no puede estar vacío")
	@Size(max = 25, message = "documentNumber no puede exceder los 25 caracteres")
	private String documentNumber;
	
	@NotBlank(message = "firstName no puede estar vacío")
	@Size(max = 100, message = "firstName no puede exceder los 100 caracteres")
	private String firstName;
	
	@NotBlank(message = "lastName no puede estar vacío")
	@Size(max = 100, message = "lastName no puede exceder los 100 caracteres")
	private String lastName;
	
	@Size(max = 100, message = "middleName no puede exceder los 100 caracteres")
	private String middleName;
	
	@NotBlank(message = "email no puede estar vacío")
	@Size(max = 150, message = "email no puede exceder los 150 caracteres")
	private String email;
	
	@NotBlank(message = "phoneNumber no puede estar vacío")
    @Size(max = 25, message = "phoneNumber no puede exceder los 25 caracteres")
	private String phoneNumber;
	
	@NotNull(message = "countryId no puede ser nulo")
	private Integer countryId;
	
	@NotNull(message = "locationLevel2Id no puede ser nulo")
	private Integer locationLevel2Id;
	private Integer locationLevel3Id;
	private Integer locationLevel4Id;
	
	@NotBlank(message = "detailedAddress no puede estar vacío")
	private String detailedAddress;
	
	private String postalCode;
	private Boolean hasRepresentative;
	private String representativeName;
	
	@NotNull(message = "branchId no puede ser nulo")
	private Integer branchId;
	
	@Schema(description = "Reclamo o Queja: R o Q", enumeration = {"R", "Q"})
	@Pattern(regexp = "[RQ]", message = "complaintType debe ser R (Reclamo) o Q (Queja)")
	private String complaintType;
	private Integer categoryId;
	private Integer currencyId;
	private BigDecimal claimedAmount;
	
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de fechaInicio debe ser YYYY-MM-DD")
	private String incidentDate;
	private String complaintDescription;
	private String consumerRequest;
	private UUID createdBy;
	private List<EvidenceRequest> evidenceList;
	public Integer getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Integer documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getLocationLevel2Id() {
		return locationLevel2Id;
	}
	public void setLocationLevel2Id(Integer locationLevel2Id) {
		this.locationLevel2Id = locationLevel2Id;
	}
	public Integer getLocationLevel3Id() {
		return locationLevel3Id;
	}
	public void setLocationLevel3Id(Integer locationLevel3Id) {
		this.locationLevel3Id = locationLevel3Id;
	}
	public Integer getLocationLevel4Id() {
		return locationLevel4Id;
	}
	public void setLocationLevel4Id(Integer locationLevel4Id) {
		this.locationLevel4Id = locationLevel4Id;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Boolean getHasRepresentative() {
		return hasRepresentative;
	}
	public void setHasRepresentative(Boolean hasRepresentative) {
		this.hasRepresentative = hasRepresentative;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	public BigDecimal getClaimedAmount() {
		return claimedAmount;
	}
	public void setClaimedAmount(BigDecimal claimedAmount) {
		this.claimedAmount = claimedAmount;
	}
	public String getIncidentDate() {
		return incidentDate;
	}
	public void setIncidentDate(String incidentDate) {
		this.incidentDate = incidentDate;
	}
	public String getComplaintDescription() {
		return complaintDescription;
	}
	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}
	public String getConsumerRequest() {
		return consumerRequest;
	}
	public void setConsumerRequest(String consumerRequest) {
		this.consumerRequest = consumerRequest;
	}
	public UUID getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}
	public List<EvidenceRequest> getEvidenceList() {
		return evidenceList;
	}
	public void setEvidenceList(List<EvidenceRequest> evidenceList) {
		this.evidenceList = evidenceList;
	}
		
}
