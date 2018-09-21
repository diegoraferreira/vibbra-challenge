package com.vibbra.challenge.model;

import com.vibbra.challenge.domain.TaskList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel(description = "Task list")
public class TaskListModel {

    private Long id;

    private String title;

    private List<TaskListItemModel> itens;

    public TaskListModel() {
        // serialization
    }

    public TaskListModel(TaskList taskList) {
        this.id = taskList.getId();
        this.title = taskList.getTitle();
        itens = taskList.getItens().stream()
                .map(TaskListItemModel::new)
                .collect(Collectors.toList());

    }

    @ApiModelProperty(value = "Task list data base id")
    public Long getId() {
        return id;
    }

    @ApiModelProperty(value = "Task list title")
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Task list itens")
    public List<TaskListItemModel> getItens() {
        return itens;
    }
}
