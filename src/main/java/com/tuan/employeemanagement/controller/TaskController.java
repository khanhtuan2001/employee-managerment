package com.tuan.employeemanagement.controller;

import com.tuan.employeemanagement.exception.ResourceNotFoundException;
import com.tuan.employeemanagement.model.Employees;
import com.tuan.employeemanagement.model.Task;
import com.tuan.employeemanagement.repository.TaskReponsitory;
import com.tuan.employeemanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tuan.employeemanagement.utilities.isValiDate.isValidDate;

@RestController
@RequestMapping("/api/v1/")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("task")
    public ResponseEntity<List<Task>> getAllTask(){

        return ResponseEntity.ok(this.taskService.getAllTask());
    }
    //get employees by id
    @GetMapping("task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id")Long taskId)
            throws ResourceNotFoundException {
        //Task task= taskService.getTaskById(taskId).orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+taskId));
        return ResponseEntity.ok().body(this.taskService.getTaskById(taskId));
    }
    @GetMapping("task/page")
    public ResponseEntity<Page<Task>> getPageTask(@RequestParam( value = "page")Integer page
                                                 ,@RequestParam(value = "size")Integer size
                                                 ,@RequestParam(required = true,defaultValue = "true")Boolean enablePagingation){
        return ResponseEntity.ok(taskService.getPageTask(page,size,enablePagingation));
    }
    //create employees
    @PostMapping("task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws ResourceNotFoundException{
//        if(task.getTaskName().isEmpty() ||
//                task.getDecription().isEmpty() ||
//
//        task.getStatus().isEmpty()|| task.getDeadline().isEmpty()){
//            throw new ResourceNotFoundException("Please enter field!!! ");
//        }
//        if(task.getStatus().equals("0")){
//            task.setStatus("Todo");
//        }else if(task.getStatus().equals("1")){
//            task.setStatus("In Progress");
//        }else
//        if(task.getStatus().equals("2")){
//            task.setStatus("done");
//        }else {
//            throw new ResourceNotFoundException("incorrect!!! please follow the pattern 0:Todo | 1: In progress | 2: Done");
//        }
//        if(isValidDate(task.getDeadline(),"dd-MM-yyyy HH:mm")== true){
//            task.setDeadline(task.getDeadline());
//        }else {
//            throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy HH:mm");
//        }

        return ResponseEntity.ok(this.taskService.createTask(task));

    }

    //update employees
    @PutMapping("task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId, @Validated @RequestBody Task taskDetail)
            throws ResourceNotFoundException{

//        Task task = taskReponsitory.findById(taskId)
//                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id:: "+taskId));
//        if(task.getTaskName().isEmpty() ||
//                task.getDecription().isEmpty() ||
//
//                task.getStatus().isEmpty()|| task.getDeadline().isEmpty()){
//            throw new ResourceNotFoundException("Please enter field!!! ");
//        }
//        if(taskDetail.getStatus().contains("0")){
//            task.setStatus("Todo");
//        }else if(taskDetail.getStatus().contains("1")){
//            task.setStatus("In Progress");
//        }else
//        if(taskDetail.getStatus().contains("2")){
//            task.setStatus("Done");
//        }else {
//            throw new ResourceNotFoundException("incorrect!!! please follow the pattern 0:Todo | 1: In progress | 2: Done");
//        }
//        if(isValidDate(taskDetail.getDeadline(),"dd-MM-yyyy HH:mm")== true){
//            task.setDeadline(taskDetail.getDeadline());
//        }else {
//            throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy HH:mm");
//        }
//        task.setTaskName(taskDetail.getTaskName());
//        task.setDecription(taskDetail.getDecription());
//        task.setAssignee(taskDetail.getAssignee());


        return ResponseEntity.ok(this.taskService.updateTask(taskId,taskDetail));

    }
    //delete employees
    @DeleteMapping("task/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException{
//        Task task = taskReponsitory.findById(taskId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: "+taskId));
//
//        this.taskReponsitory.delete(task);
//
//        Map<String,Boolean> response = new HashMap<>();
//        response.put("delete", Boolean.TRUE);

        return ResponseEntity.ok(this.taskService.deleteTask(taskId));
    }
}
