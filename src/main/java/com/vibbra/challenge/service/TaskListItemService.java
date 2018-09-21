package com.vibbra.challenge.service;

import com.vibbra.challenge.domain.TaskList;
import com.vibbra.challenge.domain.TaskListItem;
import com.vibbra.challenge.model.TaskListItemModel;
import com.vibbra.challenge.model.request.TaskListItemRequest;
import com.vibbra.challenge.model.response.TaskListItemResponse;
import com.vibbra.challenge.model.response.TaskListItemsResponse;
import com.vibbra.challenge.repository.TaskListItemRepository;
import com.vibbra.challenge.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskListItemService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskListItemRepository taskListItemRepository;

    @Transactional(readOnly = true)
    public TaskListItemsResponse findByTaskListId(Long id) {
        Optional<TaskList> taskList = taskListRepository.findById(id);
        if (taskList.isPresent()) {
            List<TaskListItemModel> items = taskList.get().getItens()
                    .stream()
                    .map(TaskListItemModel::new)
                    .collect(Collectors.toList());
            return new TaskListItemsResponse(items);
        } else {
            String errorMessage = "No task list found for id: %s";
            throw new EntityNotFoundException(String.format(errorMessage, id));
        }
    }

    @Transactional
    public TaskListItemResponse createTaskListItem(Long taskListId, TaskListItemRequest taskListItemRequest) {
        Optional<TaskList> taskListOptional = taskListRepository.findById(taskListId);
        if (taskListOptional.isPresent()) {
            TaskList taskList = taskListOptional.get();
            TaskListItem taskListItem = this.createTaskListItem(taskListItemRequest);
            taskListItem.setTaskList(taskList);
            TaskListItem savedTaskListItem = taskListItemRepository.save(taskListItem);
            return new TaskListItemResponse(savedTaskListItem);
        } else {
            String errorMessage = "No task list found for id: %s";
            throw new EntityNotFoundException(String.format(errorMessage, taskListId));
        }
    }

    @Transactional
    public TaskListItemResponse createTaskListSubItem(Long taskListId, Long taskListItemId, TaskListItemRequest taskListItemRequest) {
        Optional<TaskListItem> taskListItemOptional = taskListItemRepository.findTaskListItemBy(taskListId, taskListItemId);
        if (taskListItemOptional.isPresent()) {
            TaskListItem taskListItem = taskListItemOptional.get();
            TaskListItem taskListSubItem = this.createTaskListItem(taskListItemRequest);
            taskListItem.setChildTaskListItem(taskListSubItem);
            TaskListItem savedTaskListItem = taskListItemRepository.save(taskListItem);
            return new TaskListItemResponse(savedTaskListItem);
        } else {
            String errorMessage = "No task list item found for id: %s and task list id: %s";
            throw new EntityNotFoundException(String.format(errorMessage, taskListItemId, taskListId));
        }
    }

    private TaskListItem createTaskListItem(TaskListItemRequest taskListItemRequest) {
        TaskListItem taskListItem = new TaskListItem();
        taskListItem.setTitle(taskListItemRequest.getTitle());
        taskListItem.setDescription(taskListItemRequest.getDescription());
        return taskListItem;
    }
}
