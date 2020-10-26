package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.*;
import com.example.demo.repo.EmployeeRepo;



@RestController
@RequestMapping("/api/v2")
public class controller {

	@Autowired
	private EmployeeRepo erepo;
	
	@PostMapping("/Employees")
	public model createEmployee(@RequestBody model emp){
		return erepo.save(emp);
		
	}
	
	@GetMapping("/Employees/{id}")
	public ResponseEntity<model> getEmployeeById(@PathVariable(value="id") Long empID) {
		model m= erepo.findById(empID).get() ;
		return ResponseEntity.ok().body(m) ;
		
	}
	
	@GetMapping("/Employees")
	public ResponseEntity<Iterable<model>> getAllEmployee() {
		Iterable<model> iterable = erepo.findAll();
	      for (model employee : iterable) {
	          System.out.println(employee);
	      }
		return ResponseEntity.ok().body(iterable) ;
		
	}
	
	@PutMapping("/Employees/{id}")
	public ResponseEntity<model> getEmployeeById(@PathVariable(value="id") Long empID,@RequestBody model empdetails) {
		model m= erepo.findById(empID).get() ;
		m.setFirstname(empdetails.getFirstname());
		m.setLastname(empdetails.getLastname());
		m.setBranch(empdetails.getBranch());
		final model updateModel = erepo.save(m);
		return ResponseEntity.ok(updateModel);
		
	}
	
	@DeleteMapping("/Employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value="id") Long empID) {
		model m= erepo.findById(empID).get() ;
		String result = "Deleted"+ " "+m.toString();
		erepo.delete(m);
		return ResponseEntity.ok(result);
	}
}
