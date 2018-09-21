package com.vibbra.challenge.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vibbra.challenge.model.TaskListSimpleModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Task list simple response")
public class TaskListSimpleResponse {

    @JsonProperty(value = "list")
    private TaskListSimpleModel taskListSimpleModel;

    public TaskListSimpleResponse(TaskListSimpleModel model) {
        this.taskListSimpleModel = model;
    }

    @ApiModelProperty(value = "Task list")
    public TaskListSimpleModel getTaskListSimpleModel() {
        return taskListSimpleModel;
    }
}
