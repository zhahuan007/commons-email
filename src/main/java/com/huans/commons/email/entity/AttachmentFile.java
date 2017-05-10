package com.huans.commons.email.entity;

import java.io.File;
import java.io.Serializable;

public class AttachmentFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5615144569333707517L;
	
	public AttachmentFile() {
		
	}
	
	public AttachmentFile(String fileName, File file) {
		super();
		this.fileName = fileName;
		this.file = file;
	}

	private String fileName;
	
	private File file;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
