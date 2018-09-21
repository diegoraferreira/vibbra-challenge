package com.vibbra.challenge.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vibbra.challenge.domain.TaskList;
import com.vibbra.challenge.model.TaskListModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Task list response")
public class TaskListResponse {

    @JsonProperty(value = "list")
    private TaskListModel taskListModel;

    public TaskListResponse(TaskList taskList) {
        taskListModel = new TaskListModel(taskList);
    }

    @ApiModelProperty(value = "Task list")
    public TaskListModel getTaskListModel() {
        return taskListModel;
    }
}
