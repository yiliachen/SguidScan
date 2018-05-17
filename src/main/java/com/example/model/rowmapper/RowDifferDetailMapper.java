package com.example.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RowDifferDetailMapper  implements RowMapper<RowDifferDetailMapper> {
	
	private String branch;
	private String tarBranch;
	private String filepath;
	private String vo;
	private String rowkey;
	
	//TODO design and implement getter and setter of attribute of this mapper
	@Override
	public RowDifferDetailMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		RowDifferDetailMapper rdm = new RowDifferDetailMapper();
		rdm.setBranch(rs.getString(1));
		rdm.setTarBranch(rs.getString(2));
		rdm.setFilepath(rs.getString(3));
		rdm.setVo(rs.getString(4));
		rdm.setRowkey(rs.getString(5));
		return rdm;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTarBranch() {
		return tarBranch;
	}

	public void setTarBranch(String tarBranch) {
		this.tarBranch = tarBranch;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getVo() {
		return vo;
	}

	public void setVo(String vo) {
		this.vo = vo;
	}

	public String getRowkey() {
		return rowkey;
	}

	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

		
}
