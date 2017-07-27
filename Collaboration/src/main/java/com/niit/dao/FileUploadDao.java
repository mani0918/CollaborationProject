package com.niit.dao;

import com.niit.domain.FileUpload;

public interface FileUploadDao {

	public void save(FileUpload fileUpload, String userId);
	public FileUpload getFile(String userId);
	
}
