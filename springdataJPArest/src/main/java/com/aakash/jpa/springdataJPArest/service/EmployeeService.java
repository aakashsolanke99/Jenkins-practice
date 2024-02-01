package com.aakash.jpa.springdataJPArest.service;

import com.aakash.jpa.springdataJPArest.exception.ResourceNotFoundException;
import com.aakash.jpa.springdataJPArest.model.Employee;
import com.aakash.jpa.springdataJPArest.repo.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee(){
        return  employeeRepository.findAll();
    }

     public  Map<String, Boolean> deleteEmployee(Long id) throws ResourceNotFoundException {
         Employee employee = employeeRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
         employeeRepository.delete(employee);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deleted", Boolean.TRUE);
         return response;
     }

    public ResponseEntity<Employee> getAEmployee(long id) throws ResourceNotFoundException {
        Employee employee=employeeRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Employee not found with id "+id));
        return ResponseEntity.ok().body(employee);
    }

    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employee.setEmail(employeeDetails.getEmail());
        employee.setName(employeeDetails.getName());
        employee.setPhone(employeeDetails.getPhone());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


    public List<Employee> getAllName(String name){
        return employeeRepository.findEmployeesByName(name);
    }

    public ResponseEntity<List<Employee>> findEmployeeByEmail(String email){
        return ResponseEntity.ok().body(employeeRepository.findByEmail(email));
    }


}
