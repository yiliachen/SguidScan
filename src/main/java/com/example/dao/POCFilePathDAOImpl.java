package com.example.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.example.model.rowmapper.BranchCounterMapper;
import com.example.model.rowmapper.PocFiles;



@Repository
public class POCFilePathDAOImpl {
	private final String INSERT_SQL = "replace into pocfiles(mail, path) values (?, ?) ";
	private final String PocPath = "/net/slc09pot.us.oracle.com/scratch/SeedProcess/utils";
	private final String FileName = "PocPaths.list";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	
	public void CreateReplacePoc( PocFiles pPF){
		//write to db
		//write to file
		jdbcTemplate.update(INSERT_SQL, new Object[] { pPF.get_Mail(),  pPF.get_Path()}, new int[] {Types.VARCHAR, Types.VARCHAR});
		
		Path pocpath = Paths.get(PocPath, FileName);
		List<String> fileContent;
		try {
			fileContent = new ArrayList<>(Files.readAllLines(pocpath, StandardCharsets.UTF_8));
		
			int found = 0;
		for (int i = 0; i < fileContent.size(); i++) {
			String []contentarray = fileContent.get(i).split("\\|");
			
		    if (contentarray[1].equals(pPF.get_Path()) ) {
		    	if(!contentarray[0].equals(pPF.get_Mail())){
		    		fileContent.set(i,pPF.get_Mail()+"|"+pPF.get_Path());
		    	}
		    	found = 1;
		        break;
		    }
		}
		if(found==0){
			fileContent.add(pPF.get_Mail()+"|"+pPF.get_Path());
		}
		Files.write(pocpath, fileContent, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Iterable<PocFiles> getPocs() {
		String sql = "select mail, path"+
                     " from pocfiles;";
		return (List<PocFiles>)this.jdbcTemplate.query(sql, new PocFiles());
	}
	
	public void deletePoc(PocFiles poc) {
		String sql = "delete from pocfiles where mail = ? and path = ?";
		jdbcTemplate.update(sql, new Object[] { poc.get_Mail(),  poc.get_Path()}, new int[] {Types.VARCHAR, Types.VARCHAR});
		
	}
}
