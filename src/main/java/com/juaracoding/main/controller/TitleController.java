package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;
import com.juaracoding.main.model.Worker;

@RestController
@RequestMapping("/title")
public class TitleController {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<Title> getTitle(){
		String sql = "Select * from title";
		List<Title> title = jdbc.query(sql, new TitleRowMapper());
		return title;
	}
	
	public int insertTitle(Title title) {
		return jdbc.update("INSERT INTO `title`(`worker_ref_id`, `worker_title`, `affected_from`) VALUES ("+title.getRefId()+",'"+title.getWorkerTitle()+"','"+title.getAffectedFrom()+"')");	
	}
	
	public int updateTitle(String refId, Title title) {
	return jdbc.update("UPDATE `title` SET `worker_title`='"+title.getWorkerTitle()+"',`affected_from`='"+title.getAffectedFrom()+"' WHERE `worker_ref_id`="+title.getRefId()+"");
	}
	
	public int deleteTitle(String refId) {
		return jdbc.update("DELETE FROM `title` WHERE `worker_ref_id`= '"+refId+"'");
		
	}
	
	
	@GetMapping("/")
	public List<Title> List(){
		return getTitle();	
	}
	
	@PostMapping("/insert")
    public String add(@RequestBody Title title) {
	 

		if (this.insertTitle(title) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
    }
	
	 @PutMapping("/{refId}")
	    public ResponseEntity<?> update(@RequestBody Title title, @PathVariable String refId) {
		 try {
	            updateTitle(refId, title);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	 }

	 @DeleteMapping("/{refId}")
	    public void delete(@PathVariable String refId) {
		 	deleteTitle(refId);
	 }
	 
}
