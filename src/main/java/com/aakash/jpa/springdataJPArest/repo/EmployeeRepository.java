package com.aakash.jpa.springdataJPArest.repo;

import com.aakash.jpa.springdataJPArest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("select e from Employee e where e.name like %:name%")
    List<Employee> findEmployeesByName(@Param("name") String empName);

    List<Employee> findByEmail(String email);
}
