package com.vibbra.challenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vibbra.challenge.domain.TaskListItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Task list item")
public class TaskListItemModel {

    private Long id;

    private String title;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TaskListItemModel childTaskListItem;

    public TaskListItemModel() {
        // serialization
    }

    public TaskListItemModel(TaskListItem taskListItem) {
        this.id = taskListItem.getId();
        this.title = taskListItem.getTitle();
        this.description = taskListItem.getDescription();
        if (taskListItem.getChildTaskListItem() != null) {
            this.childTaskListItem = new TaskListItemModel(taskListItem.getChildTaskListItem());
        }
    }

    @ApiModelProperty(value = "Task list item data base id")
    public Long getId() {
        return id;
    }

    @ApiModelProperty(value = "Task list item title")
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Task list item description")
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(value = "Task list item child")
    public TaskListItemModel getChildTaskListItem() {
        return childTaskListItem;
    }
}

