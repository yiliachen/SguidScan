package com.example.pojo;

public class FileList {
	private long id;
	private String filename;
	private String startPath;
	private String Type;

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getStartPath() {
		return startPath;
	}

	public void setStartPath(String startPath) {
		this.startPath = startPath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
