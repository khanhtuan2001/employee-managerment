package com.tuan.employeemanagement.service;

import com.tuan.employeemanagement.exception.ResourceNotFoundException;
import com.tuan.employeemanagement.model.Task;
import com.tuan.employeemanagement.repository.TaskReponsitory;
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
public class TaskService {
    @Autowired
    private TaskReponsitory taskReponsitory;

    public Task createTask( Task task)throws ResourceNotFoundException{
        if(task.getTaskName().isEmpty() ||
                task.getDecription().isEmpty() ||

                task.getStatus().isEmpty()|| task.getDeadline().isEmpty()){
            throw new ResourceNotFoundException("Please enter field!!! ");
        }
        if(task.getStatus().equals("0")){
            task.setStatus("Todo");
        }else if(task.getStatus().equals("1")){
            task.setStatus("In Progress");
        }else
        if(task.getStatus().equals("2")){
            task.setStatus("done");
        }else {
            throw new ResourceNotFoundException("incorrect!!! please follow the pattern 0:Todo | 1: In progress | 2: Done");
        }
        if(isValidDate(task.getDeadline(),"dd-MM-yyyy HH:mm")== true){
            task.setDeadline(task.getDeadline());
        }else {
            throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy HH:mm");
        }

        return this.taskReponsitory.save(task);
    }
    public  Task getTaskById(Long taskId)throws ResourceNotFoundException{
        Task task= taskReponsitory.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: "+taskId));
        return task;
    }
    public Page<Task> getPageTask(Integer page, Integer size ,Boolean enablePagination){
        return taskReponsitory.findAll(enablePagination ? PageRequest.of(page,size): Pageable.unpaged());
    }
    public List<Task> getAllTask(){
        return this.taskReponsitory.findAll();
    }
    public Map<String,Boolean> deleteTask(Long taskId)throws ResourceNotFoundException{
        Task task = taskReponsitory.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: "+taskId));

        this.taskReponsitory.delete(task);

        Map<String,Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return response;
    }
    public Task updateTask(Long taskId,Task taskDetail)throws ResourceNotFoundException{
        Task task = taskReponsitory.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id:: "+taskId));



       if(taskDetail.getTaskName()== null){
           task.setTaskName(task.getTaskName());
           if(taskDetail.getDecription()==null){
               task.setDecription(task.getDecription());
               kiemtraAsignee(taskId,taskDetail);
           }else {
               task.setDecription(taskDetail.getDecription());
               kiemtraAsignee(taskId,taskDetail);
           }
       }else {
           task.setTaskName(taskDetail.getTaskName());
           if(taskDetail.getDecription()==null){
               task.setDecription(task.getDecription());
               kiemtraAsignee(taskId,taskDetail);
           }else {
               task.setDecription(taskDetail.getDecription());
               kiemtraAsignee(taskId,taskDetail);
           }
       }
        return this.taskReponsitory.save(task);
    }
    public void kiemtraStatusTask(Long taskId,Task taskDetail)throws ResourceNotFoundException{
        Task task = taskReponsitory.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id:: "+taskId));
        if(taskDetail.getStatus()==null){
            task.setStatus(task.getStatus());

        }else {
            if(taskDetail.getStatus().contains("0")){
                task.setStatus("Todo");
            }else if(taskDetail.getStatus().contains("1")){
                task.setStatus("In Progress");
            }else
            if(taskDetail.getStatus().contains("2")){
                task.setStatus("Done");
            }else {
                throw new ResourceNotFoundException("incorrect!!! please follow the pattern 0:Todo | 1: In progress | 2: Done");
            }
        }
    }
    public void kiemtraDealine(Long taskId,Task taskDetail)throws ResourceNotFoundException{
        Task task = taskReponsitory.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id:: "+taskId));
        if(taskDetail.getDeadline()==null){
            task.setDeadline(task.getDeadline());
            kiemtraStatusTask(taskId,taskDetail);
        }else {
            if(isValidDate(taskDetail.getDeadline(),"dd-MM-yyyy HH:mm")== true){
                task.setDeadline(taskDetail.getDeadline());
            }else {
                throw new ResourceNotFoundException("incorrect!!! please follow the pattern dd-mm-yyyy HH:mm");
            }
            kiemtraStatusTask(taskId,taskDetail);
        }
    }
    public void kiemtraAsignee(Long taskId,Task taskDetail)throws ResourceNotFoundException{
        Task task = taskReponsitory.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("task not found for this id:: "+taskId));
        if(taskDetail.getAssignee()==null){
            task.setAssignee(task.getAssignee());
            kiemtraDealine(taskId,taskDetail);

        }else {
            task.setAssignee(taskDetail.getAssignee());
            kiemtraDealine(taskId,taskDetail);
        }
    }

}
