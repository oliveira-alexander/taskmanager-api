package edu.taskmanager.v1.controllers;

import edu.taskmanager.v1.dtos.TaskInputDTO;
import edu.taskmanager.v1.dtos.TaskOutputDTO;
import edu.taskmanager.v1.entities.Task;
import edu.taskmanager.v1.services.TaskService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private TaskService service;

    @CrossOrigin
    @GetMapping(value = "/task")
    public List<TaskOutputDTO> getAll(){
        return service.getAll();
    }

    @CrossOrigin
    @PostMapping(value = "/task")
    public TaskOutputDTO create(@RequestBody TaskInputDTO task){
        return service.create(task);
    }

    @CrossOrigin
    @DeleteMapping("/task/{id}")
    public boolean delete(@PathVariable long id)
    {
        return service.delete(id);
    }

    @CrossOrigin
    @GetMapping(value = "/task/{id}")
    public TaskOutputDTO getById(@PathVariable long id)
    {
        return service.getById(id);
    }

    @CrossOrigin
    @PutMapping(value = "/task")
    public TaskOutputDTO update(@RequestBody TaskInputDTO task){
        return service.update(task);
    }
}
