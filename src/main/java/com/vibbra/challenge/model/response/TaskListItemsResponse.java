package com.vibbra.challenge.model.response;

import com.vibbra.challenge.model.TaskListItemModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Task list items response")
public class TaskListItemsResponse {

    private List<TaskListItemModel> items;

    public TaskListItemsResponse(List<TaskListItemModel> items) {
        this.items = items;
    }

    @ApiModelProperty(value = "Task list itens")
    public List<TaskListItemModel> getItems() {
        return items;
    }
}
