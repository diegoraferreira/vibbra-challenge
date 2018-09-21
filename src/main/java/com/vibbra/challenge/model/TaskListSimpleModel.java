package com.vibbra.challenge.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Task list simple model")
public class TaskListSimpleModel {

    private String title;

    public TaskListSimpleModel() {
        // serialization ...
    }

    public TaskListSimpleModel(String title) {
        this.title = title;
    }

    @ApiModelProperty(value = "Task list title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
