package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SDF {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="Path", length=1000)
	private String path;
	@Column(name="Am")
	private String Am;
	@Column(name="ModuleId")
	private String ModuleId;
	@Column(name="Vo")
	private String Vo;
	@Column(name="Effective_dated")
	private String Effective_dated;
	@Column(name="ts",insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date timestamp;

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getpath() {
		return path;
	}


	public void setpath(String path) {
		this.path = path;
	}

	public String getAm() {
		return Am;
	}

	public void setAm(String am) {
		Am = am;
	}

	public String getModuleId() {
		return ModuleId;
	}

	public void setModuleId(String moduleId) {
		ModuleId = moduleId;
	}

	public String getEffective_dated() {
		return Effective_dated;
	}

	public void setEffective_dated(String effective_dated) {
		this.Effective_dated = effective_dated;
	}

	public String getVo() {
		return Vo;
	}

	public void setVo(String vo) {
		Vo = vo;
	}
	
	public SDF(){}

	public SDF( String path, String am, String moduleId, String vo, String effective_dated) {
		super();
		this.path = path;
		Am = am;
		ModuleId = moduleId;
		Vo = vo;
		this.Effective_dated = effective_dated;
	}
}