package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileEntry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="filename")
	private String filename;
	@Column(name="entryName")
	private String entryName;
	@Column(name="type")
	private String type;
	@Column(name="occourance")
	private Integer occourance;
	@Column(name="SguidIsNull")
	private String SguidIsNull;
	@Column(name="SguidPopulated")
	private String SguidPopulated;
	@Column(name="SguidDuplicated")
	private String SguidDuplicated;
	@Column(name="HasRowkey")
	private String HasRowkey;
	@Column(name="timestamp",insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date timestamp;
	@Column(name="startPath")
	private String startPath;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public String getStartPath() {
		return startPath;
	}

	public void setStartPath(String startPath) {
		this.startPath = startPath;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getSguidIsNull() {
		return SguidIsNull;
	}

	public void setSguidIsNull(String sguidIsNull) {
		SguidIsNull = sguidIsNull;
	}

	public String getSguidPopulated() {
		return SguidPopulated;
	}

	public void setSguidPopulated(String sguidPopulated) {
		SguidPopulated = sguidPopulated;
	}

	public String getSguidDuplicated() {
		return SguidDuplicated;
	}

	public void setSguidDuplicated(String sguidDuplicated) {
		SguidDuplicated = sguidDuplicated;
	}

	public String getHasRowkey() {
		return HasRowkey;
	}

	public void setHasRowkey(String hasRowkey) {
		HasRowkey = hasRowkey;
	}

	public FileEntry() {
		super();
	}
	
	public FileEntry(String filename, String entryName, String type, Integer occurance) {
		super();
		this.filename = filename;
		this.entryName = entryName;
		this.setType(type);
		this.occourance = occurance;
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type!=null && type.indexOf("SGUIDPOP") >= 0){
			this.setSguidPopulated("Y");
		}else{
			this.setSguidPopulated("N");
		}
		
		if(type!=null && type.indexOf("DUPSGUID") >= 0){
			this.setSguidDuplicated("Y");
		}else{
			this.setSguidDuplicated("N");
		}
		
		if(type!=null && type.indexOf("NULLSGUID") >= 0){
			this.setSguidIsNull("Y");
		}else{
			this.setSguidIsNull("N");
		}
		
		if(type!=null && type.indexOf("rowkey") >= 0){
			this.setHasRowkey("Y");
		}else{
			this.setHasRowkey("N");
		}
	}

	public Integer getOccourance() {
		return occourance;
	}

	public void setOccourance(Integer occourance) {
		this.occourance = occourance;
	}
	
	public Boolean equals(FileEntry fe){
		if(fe.entryName.equals(this.entryName) &&
				fe.filename.equals(this.filename) && 
				fe.type.equals(this.type) && fe.occourance.equals(this.occourance)){
			return true;
		}else{
			return false;
		}
	}
}