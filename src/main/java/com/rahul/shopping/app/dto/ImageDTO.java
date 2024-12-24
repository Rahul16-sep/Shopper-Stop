package com.rahul.shopping.app.dto;

public class ImageDTO {
	
	private Long id;
	private String filename;
	private String filetype;
	private String downloadUrl;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFiletype() {
		return filetype;
	}
	
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
	public String getDownloadUrl() {
		return downloadUrl;
	}
	
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
}
