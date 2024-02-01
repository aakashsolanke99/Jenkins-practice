package com.aakash.jpa.springdataJPArest.controller;

import com.aakash.jpa.springdataJPArest.exception.ResourceNotFoundException;
import com.aakash.jpa.springdataJPArest.model.Employee;
import com.aakash.jpa.springdataJPArest.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/getAEmployee/{id}")
    public ResponseEntity<Employee> getAEmployee(@PathVariable long id) throws ResourceNotFoundException {
        return employeeService.getAEmployee(id);
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee createdEmployee=employeeService.saveEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

//    @DeleteMapping("/deleteEmployee/{id}")
//    public String deleteEmployee(@PathVariable Long id){
//        return employeeService.deleteEmployee(id);
//    }

//    @PutMapping("/updateEmployee")
//    public Employee updateEmployee(@RequestBody Employee employee){
//        return employeeService.updateEmployee(employee);
//    }

    @DeleteMapping("/deleteEmployee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId,employeeDetails).getBody());
    }

    @GetMapping("/getEmpsByName/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name){
        return employeeService.getAllName(name);
    }

    @GetMapping("getEmplsByEmail/{email}")
    public ResponseEntity<List<Employee>> getEmployeesByEmail(@PathVariable String email,@RequestParam Long id){
        return employeeService.findEmployeeByEmail(email);
    }


}
