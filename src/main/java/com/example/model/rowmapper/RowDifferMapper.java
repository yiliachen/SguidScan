package com.example.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RowDifferMapper  implements RowMapper<RowDifferMapper> {
	
	private String branch;
	private String tarBranch;
	private String filepath;
	private Long RowDiffCount;
	
	//TODO design and implement getter and setter of attribute of this mapper
	@Override
	public RowDifferMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
		RowDifferMapper rdm = new RowDifferMapper();
		rdm.setBranch(rs.getString(1));
		rdm.setTarBranch(rs.getString(2));
		rdm.setFilepath(rs.getString(3));
		rdm.setRowDiffCount(rs.getLong(4));
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

	public Long getRowDiffCount() {
		return RowDiffCount;
	}

	public void setRowDiffCount(Long rowDiffCount) {
		RowDiffCount = rowDiffCount;
	}

		
}
