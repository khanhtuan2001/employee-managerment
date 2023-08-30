package com.tuan.employeemanagement.service;


import com.tuan.employeemanagement.controller.EmployeeController;
import com.tuan.employeemanagement.exception.ResourceNotFoundException;
import com.tuan.employeemanagement.model.Employees;
import com.tuan.employeemanagement.repository.EmployeeReponsitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tuan.employeemanagement.utilities.isValiDate.isValidDate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeReponsitory employeeReponsitory;

    public Employees saveEmpolyees (Employees employees)throws ResourceNotFoundException{

        if(employees.getName().isEmpty() || employees.getBirthday().isEmpty()){
            throw new ResourceNotFoundException("Please enter field!!! ");
        }

        if(isValidDate(employees.getBirthday(),"dd-MM-yyyy") == true){
            employees.setBirthday(employees.getBirthday());
        }else {
            throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy");
        }
            return employeeReponsitory.save(employees);

    }
    public Employees getEmployeeById(Long employeeId)throws ResourceNotFoundException{
        Employees employees= employeeReponsitory.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+employeeId));
    return employees;
    }
    public Page<Employees> getPageEmployee(Integer page, Integer size , Boolean enablePagination){
        return employeeReponsitory.findAll(enablePagination ? PageRequest.of(page,size): Pageable.unpaged());
    }
    public List<Employees> getAllEmployee(){
        return this.employeeReponsitory.findAll();
    }

    public Map<String, Boolean> deleteEmployee(Long id)throws ResourceNotFoundException{

        Employees employees = employeeReponsitory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: "+id));

        this.employeeReponsitory.delete(employees);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return response;
    }

    public Employees updateEmployee(Long employeeId, Employees employeesDetail) throws ResourceNotFoundException{
        Employees employees = employeeReponsitory.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: "+employeeId));

        if(employeesDetail.getName()== null){
            employees.setName(employees.getName());
            kiemtra(employeeId,employeesDetail);
        }else {
            employees.setName(employeesDetail.getName());
            kiemtra(employeeId,employeesDetail);
        }
        return this.employeeReponsitory.save(employees);
    }

    public void kiemtra(Long employeeId,Employees employeesDetail)throws ResourceNotFoundException{
        Employees employees = employeeReponsitory.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: "+employeeId));
        if(employeesDetail.getBirthday()== null){
            employees.setBirthday(employees.getBirthday());

            if(employeesDetail.isGender()==null){
                employees.setGender(employees.isGender());
            }else {
                employees.setGender(employeesDetail.isGender());
            }
        }else {
            if( isValidDate(employeesDetail.getBirthday(),"dd-MM-yyyy") == true){
                employees.setBirthday(employeesDetail.getBirthday());
            }else {
                throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy");
            }
            if(employeesDetail.isGender()==null){
                employees.setGender(employees.isGender());
            }else {
                employees.setGender(employeesDetail.isGender());
            }
        }
    }
}
