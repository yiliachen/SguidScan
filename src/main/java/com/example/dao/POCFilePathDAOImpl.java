package com.example.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.rowmapper.PocFiles;



@Repository
public class POCFilePathDAOImpl {
	private final String INSERT_SQL = "insert into pocfiles(mail) values (?) ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public void CreateReplacePoc( PocFiles pPF){
		jdbcTemplate.update(INSERT_SQL, new Object[] { pPF.get_Mail()}, new int[] {Types.VARCHAR});
	}
	
	public Iterable<PocFiles> getPocs() {
		String sql = "select mail, path"+
                     " from pocfiles;";
		return (List<PocFiles>)this.jdbcTemplate.query(sql, new PocFiles());
	}
	
	public void deletePoc(PocFiles poc) {
		String sql = "delete from pocfiles where mail = ? ";
		jdbcTemplate.update(sql, new Object[] { poc.get_Mail()}, new int[] {Types.VARCHAR});
	}
}
