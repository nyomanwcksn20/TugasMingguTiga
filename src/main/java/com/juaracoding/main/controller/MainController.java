package com.juaracoding.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public String getMain(){
		String worker ="Tugas nomer 2, 3, 4 \n"
				+ "localhost:3010/main/topsalary \n"
				+ "localhost:3010/main/samesalary \n"
				+ "localhost:3010/main/countdepartment \n";
		return worker;
	}
	
		//Mencari 5 orang dengan salary tertinggi
		public List<Worker> getSalary(){
			String sql = "Select * from worker order by salary desc limit 5";
			List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
			return worker;
		}
		
		//Menampilkan orang yang memiliki salary sama
		public List<Worker> getSameSalary(){
			String sql= "SELECT * from worker where salary IN (SELECT salary from worker group by salary HAVING COUNT(*) > 1)";
			List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
			return worker;
		}
		
		//Menampilkan jumlah orang dalam departement
		public List<Worker> getCountDepartment(){
			String sql= "SELECT DISTINCT department, COUNT(*) as jumlah from worker group by department";
			List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
			return worker;
		}
		
		@GetMapping("/")
		public String List(){
			return getMain();
		}
		
		@GetMapping("/topsalary")
		public List<Worker> List1(){
			return getSalary();	
		}
		
		@GetMapping("/samesalary")
		public List<Worker> List2(){
			return getSameSalary();	
		}
		
		@GetMapping("/countdepartment")
		public List<Worker> List3(){
			return getCountDepartment();	
		}
			
}
