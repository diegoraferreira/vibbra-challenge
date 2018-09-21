package com.vibbra.challenge.service;

import com.vibbra.challenge.domain.TaskList;
import com.vibbra.challenge.model.TaskListSimpleModel;
import com.vibbra.challenge.model.response.TaskListResponse;
import com.vibbra.challenge.model.response.TaskListSimpleResponse;
import com.vibbra.challenge.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository repository;

    @Transactional(readOnly = true)
    public TaskListResponse findById(Long id) {
        Optional<TaskList> taskList = repository.findById(id);
        if (taskList.isPresent()) {
            return new TaskListResponse(taskList.get());
        } else {
            String errorMessage = "No task list found for id: %s";
            throw new EntityNotFoundException(String.format(errorMessage, id));
        }
    }

    @Transactional
    public TaskListSimpleResponse createTaskList(TaskListSimpleModel taskListSimpleModel) {
        repository.save(new TaskList(taskListSimpleModel.getTitle()));
        return new TaskListSimpleResponse(taskListSimpleModel);
    }
}
