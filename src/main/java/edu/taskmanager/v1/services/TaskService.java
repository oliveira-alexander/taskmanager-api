package edu.taskmanager.v1.services;

import edu.taskmanager.v1.dtos.TaskInputDTO;
import edu.taskmanager.v1.dtos.TaskOutputDTO;

import java.util.List;

public interface TaskService {
    List<TaskOutputDTO> getAll();
    TaskOutputDTO create(TaskInputDTO task);
    boolean delete(Long id);
    TaskOutputDTO getById(long id);
    TaskOutputDTO update(TaskInputDTO task);
}
