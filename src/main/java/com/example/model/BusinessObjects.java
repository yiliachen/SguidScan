package com.example.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="BusinessObjects")
public class BusinessObjects {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="vo_def",length=500,nullable=true)
	private String vo_def;
	@Column(name="vo_name",length=100,nullable=true)
	private String vo_name;
	@Column(name="vo_extends",length=500,nullable=true)
	private String vo_extends;
	@Column(name="vo_path",length=500,nullable=true)
	private String vo_path;
	@Column(name="extra_name",length=500,nullable=true)
	private String extra_name;
	@Column(name="extra_def",length=500,nullable=true)
	private String extra_def;
	@Column(name="eo_name",length=100,nullable=true)
	private String eo_name;
	@Column(name="eo_path",length=500,nullable=true)
	private String eo_path;
	@Column(name="vo_entity_usage_obj_def",length=500,nullable=true)
	private String vo_entity_usage_obj_def;
	@Column(name="dbo_type",length=500,nullable=true)
	private String dbo_type;
	@Column(name="dbo_name",length=100,nullable=true)
	private String dbo_name;
	@Column(name="dbo_col_name",length=500,nullable=true)
	private String dbo_col_name;
	@Column(name="dbo_path",length=500,nullable=true)
	private String dbo_path;
	@Column(name="sdf_path",length=500,nullable=true)
	private String sdf_path;
	@Column(name="sdf_vo",length=100,nullable=true)
	private String sdf_vo;
	@Column(name="sdf_am",length=300,nullable=true)
	private String sdf_am;
	@Column(name="am_name",length=300,nullable=true)
	private String am_name;
	@Column(name="am_vo_usage_name",length=300,nullable=true)
	private String am_vo_usage_name;
	@Column(name="am_vo_usage_object_def",length=500,nullable=true)
	private String am_vo_usage_object_def;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVo_def() {
		return vo_def;
	}

	public void setVo_def(String vo_def) {
		this.vo_def = vo_def;
	}

	public String getVo_name() {
		return vo_name;
	}

	public void setVo_name(String vo_name) {
		this.vo_name = vo_name;
	}

	public String getVo_extends() {
		return vo_extends;
	}

	public void setVo_extends(String vo_extends) {
		this.vo_extends = vo_extends;
	}

	public String getVo_path() {
		return vo_path;
	}

	public void setVo_path(String vo_path) {
		this.vo_path = vo_path;
	}

	public String getExtra_name() {
		return extra_name;
	}

	public void setExtra_name(String extra_name) {
		this.extra_name = extra_name;
	}

	public String getExtra_def() {
		return extra_def;
	}

	public void setExtra_def(String extra_def) {
		this.extra_def = extra_def;
	}

	public String getEo_name() {
		return eo_name;
	}

	public void setEo_name(String eo_name) {
		this.eo_name = eo_name;
	}

	public String getEo_path() {
		return eo_path;
	}

	public void setEo_path(String eo_path) {
		this.eo_path = eo_path;
	}

	public String getVo_entity_usage_obj_def() {
		return vo_entity_usage_obj_def;
	}

	public void setVo_entity_usage_obj_def(String vo_entity_usage_obj_def) {
		this.vo_entity_usage_obj_def = vo_entity_usage_obj_def;
	}

	public String getDbo_type() {
		return dbo_type;
	}

	public void setDbo_type(String dbo_type) {
		this.dbo_type = dbo_type;
	}

	public String getDbo_name() {
		return dbo_name;
	}

	public void setDbo_name(String dbo_name) {
		this.dbo_name = dbo_name;
	}

	public String getDbo_col_name() {
		return dbo_col_name;
	}

	public void setDbo_col_name(String dbo_col_name) {
		this.dbo_col_name = dbo_col_name;
	}

	public String getDbo_path() {
		return dbo_path;
	}

	public void setDbo_path(String dbo_path) {
		this.dbo_path = dbo_path;
	}

	public String getSdf_path() {
		return sdf_path;
	}

	public void setSdf_path(String sdf_path) {
		this.sdf_path = sdf_path;
	}

	public String getSdf_vo() {
		return sdf_vo;
	}

	public void setSdf_vo(String sdf_vo) {
		this.sdf_vo = sdf_vo;
	}

	public String getSdf_am() {
		return sdf_am;
	}

	public void setSdf_am(String sdf_am) {
		this.sdf_am = sdf_am;
	}

	public String getAm_name() {
		return am_name;
	}

	public void setAm_name(String am_name) {
		this.am_name = am_name;
	}

	public String getAm_vo_usage_name() {
		return am_vo_usage_name;
	}

	public void setAm_vo_usage_name(String am_vo_usage_name) {
		this.am_vo_usage_name = am_vo_usage_name;
	}

	public String getAm_vo_usage_object_def() {
		return am_vo_usage_object_def;
	}

	public void setAm_vo_usage_object_def(String am_vo_usage_object_def) {
		this.am_vo_usage_object_def = am_vo_usage_object_def;
	}
}