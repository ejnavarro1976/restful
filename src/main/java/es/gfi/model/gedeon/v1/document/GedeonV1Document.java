package es.gfi.model.gedeon.v1.document;

import java.io.Serializable;
import java.util.HashMap;

public class GedeonV1Document<E, K, V> implements Serializable {

	private static final long serialVersionUID = -1729724200622086043L;
	
	private String createdBy;
	private String creationDate;
	private String description;
	private String folderId;
	private String id;
	private String lastModifictionDate;
	private String lastModifiedBy;
	private HashMap<K, V> metadata;
	private String name;
	private String primaryParentId;
	private String versionLabel;

	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastModifictionDate() {
		return lastModifictionDate;
	}

	public void setLastModifictionDate(String lastModifictionDate) {
		this.lastModifictionDate = lastModifictionDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}


	public HashMap<K, V> getMetadata() {
		return metadata;
	}

	public void setMetadata(HashMap<K, V> metadata) {
		this.metadata = (HashMap<K, V>) metadata;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryParentId() {
		return primaryParentId;
	}

	public void setPrimaryParentId(String primaryParentId) {
		this.primaryParentId = primaryParentId;
	}

	public String getVersionLabel() {
		return versionLabel;
	}

	public void setVersionLabel(String versionLabel) {
		this.versionLabel = versionLabel;
	}

}
