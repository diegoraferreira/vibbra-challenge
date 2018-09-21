package com.vibbra.challenge.controller;

import com.vibbra.challenge.exceptionhandler.ApiError;
import com.vibbra.challenge.model.request.TaskListItemRequest;
import com.vibbra.challenge.model.response.TaskListItemResponse;
import com.vibbra.challenge.model.response.TaskListItemsResponse;
import com.vibbra.challenge.model.response.TaskListResponse;
import com.vibbra.challenge.service.TaskListItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Task list item rest API", description = "Provides access to create and seek Task list item, and to create task list item child.")
@RestController
@RequestMapping(path = "/api/v1/")
public class TaskListItemController {

    @Autowired
    private TaskListItemService taskListItemService;

    @ApiOperation(value = "Find task list item by task list taskListId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task list item found", response = TaskListResponse.class),
            @ApiResponse(code = 404, message = "Task list not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
    })
    @GetMapping(path = "/list/{taskListId}/item")
    public ResponseEntity<TaskListItemsResponse> findByTaskListId(@PathVariable Long taskListId) {
        TaskListItemsResponse response = taskListItemService.findByTaskListId(taskListId);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create task list item by task list id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Task list item created", response = TaskListResponse.class),
            @ApiResponse(code = 404, message = "Task list not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
    })
    @PostMapping(path = "/list/{taskListId}/item")
    public ResponseEntity<TaskListItemResponse> createTaskListItem(@PathVariable Long taskListId, @RequestBody TaskListItemRequest taskListItemRequest) {
        TaskListItemResponse response = taskListItemService.createTaskListItem(taskListId, taskListItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Create task list child item by task list id and task list item id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task list item child created", response = TaskListResponse.class),
            @ApiResponse(code = 404, message = "Task list item not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
    })
    @PutMapping(path = "/list/{taskListId}/item/{itemId}")
    public ResponseEntity<TaskListItemResponse> createTaskListSubItem(@PathVariable Long taskListId, @PathVariable Long itemId, @RequestBody TaskListItemRequest taskListItemRequest) {
        TaskListItemResponse response = taskListItemService.createTaskListSubItem(taskListId, itemId, taskListItemRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
