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

import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;

@RestController
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<Worker> getWorker(){
		String sql = "Select * from worker";
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;
	}
	
	
	public int insertWorker(Worker worker) {
		return jdbc.update("INSERT INTO `worker`(`worker_id`, `first_name`, `last_name`, `salary`, `joining_date`, `department`) VALUES ('"+ worker.getWorkerId() +"','"+ worker.getfName() +"','"+ worker.getlName() +"',"+ worker.getSalary() +",'"+ worker.getJoinDate() +"','"+ worker.getDepartment() +"')") ;
	}
	
	public int updateWorker(String workerId, Worker worker ) {
		return jdbc.update("UPDATE `worker` SET `first_name`='"+worker.getfName()+"',`last_name`='"+worker.getlName()+"',`salary`="+worker.getSalary()+",`joining_date`='"+worker.getJoinDate()+"',`department`='"+worker.getDepartment()+"' WHERE `worker_id`= '"+worker.getWorkerId()+"'");
	}
	
	public int deleteWorker(String workerId) {
		return jdbc.update("DELETE FROM `worker` WHERE `worker_id`= '"+workerId+"'");
		
	}
	
	@GetMapping("/")
	public List<Worker> List(){
		return getWorker();	
	}
		
	@PostMapping("/insert")
    public String add(@RequestBody Worker worker) {
	 

		if (this.insertWorker(worker) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
    }
	
	 @PutMapping("/{workerId}")
	    public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable String workerId) {
		 try {
	            updateWorker(workerId, worker);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	 
	 @DeleteMapping("/{workerId}")
	    public void delete(@PathVariable String workerId) {
		 	deleteWorker(workerId);
	 }
	 
}
