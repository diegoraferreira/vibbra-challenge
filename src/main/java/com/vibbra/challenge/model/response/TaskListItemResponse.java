package com.vibbra.challenge.model.response;

import com.vibbra.challenge.domain.TaskListItem;
import com.vibbra.challenge.model.TaskListItemModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Task list item response")
public class TaskListItemResponse {

    private TaskListItemModel item;

    public TaskListItemResponse(TaskListItem item) {
        this.item = new TaskListItemModel(item);
    }

    @ApiModelProperty(value = "Task list item")
    public TaskListItemModel getItem() {
        return item;
    }

    public void setItem(TaskListItemModel item) {
        this.item = item;
    }
}
