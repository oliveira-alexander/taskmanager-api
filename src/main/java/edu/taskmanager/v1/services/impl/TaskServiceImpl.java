package edu.taskmanager.v1.services.impl;

import edu.taskmanager.v1.dtos.TaskInputDTO;
import edu.taskmanager.v1.dtos.TaskOutputDTO;
import edu.taskmanager.v1.entities.Task;
import edu.taskmanager.v1.repositories.TaskRepository;
import edu.taskmanager.v1.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public List<TaskOutputDTO> getAll() {
        List<TaskOutputDTO> taskList = new ArrayList<>();

        repository.findAll()
                  .forEach(task -> taskList.add(new TaskOutputDTO(task.getId(),
                                                                        task.getTitle(),
                                                                        task.getDescription()
                                                                        )
                                                      ));

        return taskList;
    }

    @Override
    public TaskOutputDTO create(TaskInputDTO task) {
        Task newTask = repository.save(new Task(task.title(), task.description()));

        return new TaskOutputDTO(newTask.getId(), newTask.getTitle(), newTask.getDescription());
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public TaskOutputDTO getById(long id) {
        if (repository.existsById(id)){
            Task taskDB = repository.findById(id).get();

            return new TaskOutputDTO(taskDB.getId(), taskDB.getTitle(), taskDB.getDescription());
        }
        else
            return new TaskOutputDTO(0, null, null);
    }

    @Override
    public TaskOutputDTO update(TaskInputDTO task) {
        if (repository.existsById(task.id())){
            Task updatingTask = new Task(task.id(), task.title(), task.description());
            repository.save(updatingTask);

            return new TaskOutputDTO(updatingTask.getId(), updatingTask.getTitle(), updatingTask.getDescription());
        }
        else
            return new TaskOutputDTO(0, null, null);
    }
}
