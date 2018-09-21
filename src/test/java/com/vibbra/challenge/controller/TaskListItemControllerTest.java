package com.vibbra.challenge.controller;

import com.vibbra.challenge.domain.TaskListItem;
import com.vibbra.challenge.exceptionhandler.RestResponseEntityExceptionHandler;
import com.vibbra.challenge.model.TaskListItemModel;
import com.vibbra.challenge.model.request.TaskListItemRequest;
import com.vibbra.challenge.model.response.TaskListItemResponse;
import com.vibbra.challenge.model.response.TaskListItemsResponse;
import com.vibbra.challenge.service.TaskListItemService;
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

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TaskListItemControllerTest {

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    @InjectMocks
    private TaskListItemController taskListItemController;

    @Mock
    private TaskListItemService taskListItemService;

    private MockMvc mockMvc;

    private TaskListItem taskListItem = new TaskListItem();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskListItemController).setControllerAdvice(exceptionHandler).build();
        taskListItem.setId(25L);
        taskListItem.setDescription("task list item description");
        taskListItem.setTitle("task list item title");
    }

    @Test
    public void validateFindByTaskListId() throws Exception {
        TaskListItemModel taskListItemModel = new TaskListItemModel(taskListItem);
        TaskListItemsResponse response = new TaskListItemsResponse(Collections.singletonList(taskListItemModel));
        Mockito.when(taskListItemService.findByTaskListId(1L)).thenReturn(response);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list/1/item"));
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.items").exists())
                .andExpect(jsonPath("$.items.[0].id").value(25L))
                .andExpect(jsonPath("$.items.[0].description").value("task list item description"))
                .andExpect(jsonPath("$.items.[0].title").value("task list item title"));

        Mockito.verify(taskListItemService, Mockito.times(1)).findByTaskListId(1L);
    }

    @Test
    public void validateCreateTaskListItem() throws Exception {
        String json = "{ \"title\": \"task list item description\", \"description\": \"task list item title\"}";
        TaskListItemResponse response = new TaskListItemResponse(this.taskListItem);
        Mockito.when(taskListItemService.createTaskListItem(Mockito.anyLong(), Mockito.any(TaskListItemRequest.class))).
                thenReturn(response);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/list/1/item")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.item").exists())
                .andExpect(jsonPath("$.item.id").value(25L))
                .andExpect(jsonPath("$.item.description").value("task list item description"))
                .andExpect(jsonPath("$.item.title").value("task list item title"))
                .andExpect(jsonPath("$.item.childTaskListItem").doesNotExist());
        Mockito.verify(taskListItemService, Mockito.times(1)).createTaskListItem(Mockito.anyLong(), Mockito.any(TaskListItemRequest.class));
    }

    @Test
    public void validateCreateTaskListSubItem() throws Exception {
        String json = "{ \"title\": \"task list sub item 1\", \"description\": \"description of task sub item 1\"}";
        TaskListItem taskListSubItem = new TaskListItem();
        taskListSubItem.setId(42L);
        taskListSubItem.setTitle("task list sub item 1");
        taskListSubItem.setDescription("description of task sub item 1");
        taskListItem.setChildTaskListItem(taskListSubItem);
        TaskListItemResponse response = new TaskListItemResponse(this.taskListItem);

        Mockito.when(taskListItemService.createTaskListSubItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(TaskListItemRequest.class))).
                thenReturn(response);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/list/1/item/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.item").exists())
                .andExpect(jsonPath("$.item.id").value(25L))
                .andExpect(jsonPath("$.item.description").value("task list item description"))
                .andExpect(jsonPath("$.item.title").value("task list item title"))
                .andExpect(jsonPath("$.item.childTaskListItem.id").value(42L))
                .andExpect(jsonPath("$.item.childTaskListItem.title").value("task list sub item 1"))
                .andExpect(jsonPath("$.item.childTaskListItem.description").value("description of task sub item 1"));
        Mockito.verify(taskListItemService, Mockito.times(1)).createTaskListSubItem(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(TaskListItemRequest.class));
    }
}