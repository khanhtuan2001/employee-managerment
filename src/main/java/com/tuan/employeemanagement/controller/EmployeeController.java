package com.tuan.employeemanagement.controller;

import com.tuan.employeemanagement.exception.ResourceNotFoundException;
import com.tuan.employeemanagement.model.Employees;
import com.tuan.employeemanagement.repository.EmployeeReponsitory;
import com.tuan.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tuan.employeemanagement.utilities.isValiDate.isValidDate;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    // get employee
    @GetMapping("employees")
    public ResponseEntity< List<Employees>> getAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body( employeeService.getAllEmployee());
    }
    //get employees by id
     @GetMapping("employees/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException{

        //Employees employees= employeeReponsitory.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+employeeId));
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
     }
     @GetMapping("employees/page")
     public ResponseEntity<Page<Employees>> getPageEmployee(@RequestParam( value = "page")Integer page
                                                             ,@RequestParam(value = "size")Integer size
                                                             ,@RequestParam(required = true,defaultValue = "true")Boolean enablePagingation){
        return ResponseEntity.ok(employeeService.getPageEmployee(page,size,enablePagingation));
     }
    //create employees
    @PostMapping("employees")
    public  ResponseEntity<Employees> createEmployee(@RequestBody Employees employees)throws ResourceNotFoundException{



//        if(employees.getName().isEmpty() || employees.getBirthday().isEmpty()){
//            throw new ResourceNotFoundException("Please enter field!!! ");
//        }
//
//        if(isValidDate(employees.getBirthday(),"dd-MM-yyyy") == true){
//            employees.setBirthday(employees.getBirthday());
//        }else {
//            throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy");
//        }
        return ResponseEntity.ok(employeeService.saveEmpolyees(employees));

    }
    //update employees
    @PutMapping("employees/{id}")
    public ResponseEntity<Employees> updateEmployees(@PathVariable(value = "id") Long employeeId, @Validated @RequestBody Employees employeesDetail)
            throws ResourceNotFoundException{

//        Employees employees = employeeReponsitory.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: "+employeeId));
//        if(employeesDetail.getName().isEmpty() || employeesDetail.getBirthday().isEmpty()){
//            throw new ResourceNotFoundException("Please enter field!!! ");
//        }
//
//        if(isValidDate(employeesDetail.getBirthday(),"dd-MM-yyyy") == true){
//            employees.setBirthday(employeesDetail.getBirthday());
//        }else {
//            throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy");
//        }
//        employees.setName(employeesDetail.getName());
//        employees.setBirthday(employeesDetail.getBirthday());
//        employees.setGender(employeesDetail.isGender());

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeId,employeesDetail));

    }
//    public static boolean isValidDate(String dateString) {
//        boolean isvalid;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        try{
//            formatter.parse(dateString);
//            isvalid = true;
//
//        }catch (Exception e){
//            isvalid = false;
//
//        }
//        return isvalid;
//
//    }
    //delete employees
    @DeleteMapping("employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException{
//        Employees employees = employeeReponsitory.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id:: "+employeeId));
//
//        this.employeeReponsitory.delete(employees);
//
//        Map<String,Boolean> response = new HashMap<>();
//        response.put("delete", Boolean.TRUE);

        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
    }
}
