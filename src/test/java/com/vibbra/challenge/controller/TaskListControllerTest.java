package com.vibbra.challenge.controller;

import com.vibbra.challenge.domain.TaskList;
import com.vibbra.challenge.exceptionhandler.RestResponseEntityExceptionHandler;
import com.vibbra.challenge.model.TaskListSimpleModel;
import com.vibbra.challenge.model.response.TaskListResponse;
import com.vibbra.challenge.model.response.TaskListSimpleResponse;
import com.vibbra.challenge.service.TaskListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TaskListControllerTest {

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    @InjectMocks
    private TaskListController taskListController;

    @Mock
    private TaskListService taskListService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskListController).setControllerAdvice(exceptionHandler).build();
    }

    @Test
    public void validateFindByIdReturnsTaskListResponse() throws Exception {
        TaskList title = new TaskList("title");
        title.setId(50L);
        TaskListResponse response = new TaskListResponse(title);
        Mockito.when(taskListService.findById(1L)).thenReturn(response);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list/1"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.list").exists())
                .andExpect(jsonPath("$.list.id").value(50L))
                .andExpect(jsonPath("$.list.title").value("title"))
                .andExpect(jsonPath("$.list.itens").isEmpty());
    }

    @Test
    public void validateStatusCodeFindByIdThrowEntityNotFoundException() throws Exception {
        Mockito.when(taskListService.findById(1L)).thenThrow(EntityNotFoundException.class);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list/1"));
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void validateStatusCodeFindByIdThrowOthersRuntimeExceptions() throws Exception {
        Mockito.when(taskListService.findById(Mockito.anyLong())).thenThrow(RuntimeException.class);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list/1"));
        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    public void validateCreateTaskList() throws Exception {
        String json = "{\"title\" : \"task 1\"}";
        Mockito.when(taskListService.createTaskList(Mockito.any(TaskListSimpleModel.class))).
                thenReturn(new TaskListSimpleResponse(new TaskListSimpleModel("task 1")));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/list/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resultActions
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.list").exists())
                .andExpect(jsonPath("$.list.title").value("task 1"));
        Mockito.verify(taskListService, Mockito.times(1)).createTaskList(Mockito.any(TaskListSimpleModel.class));
    }
}