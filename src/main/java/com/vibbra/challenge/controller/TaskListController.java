package com.vibbra.challenge.controller;

import com.vibbra.challenge.exceptionhandler.ApiError;
import com.vibbra.challenge.model.TaskListSimpleModel;
import com.vibbra.challenge.model.response.TaskListResponse;
import com.vibbra.challenge.model.response.TaskListSimpleResponse;
import com.vibbra.challenge.service.TaskListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(tags = "Task list rest API", description = "Provides access to create and seek Task list.")
@RestController
@RequestMapping(path = "/api/v1/")
public class TaskListController {

    @Autowired
    private TaskListService service;

    @ApiOperation(value = "Find task list by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Task list found", response = TaskListResponse.class),
            @ApiResponse(code = 404, message = "Task list not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
    })
    @GetMapping(path = "list/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TaskListResponse> findById(@PathVariable Long id) {
        TaskListResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Record task list")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Task list created", response = TaskListSimpleResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class),
    })
    @PostMapping(path = "/list", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TaskListSimpleResponse> createTaskList(@RequestBody TaskListSimpleModel taskListSimpleModel) {
        TaskListSimpleResponse response = service.createTaskList(taskListSimpleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
