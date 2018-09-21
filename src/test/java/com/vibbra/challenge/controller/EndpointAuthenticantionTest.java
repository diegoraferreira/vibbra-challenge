package com.vibbra.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vibbra.challenge.model.TaskListSimpleModel;
import com.vibbra.challenge.model.request.TaskListItemRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EndpointAuthenticantionTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<TaskListSimpleModel> taskListSimpleModelJacksonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    /**
     * Validate access permited to {@link TaskListController#findById(Long)}}
     */
    @Test
    public void accessToListFindById() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list/100"));
        resultActions.andExpect(status().isOk());
    }

    /**
     * Validate access permited to {@link TaskListController#createTaskList(TaskListSimpleModel)}
     */
    @Test
    public void accessToCreateTaskList() throws Exception {
        TaskListSimpleModel model = new TaskListSimpleModel("task list test");
        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/list")
                        .content(taskListSimpleModelJacksonTester.write(model).getJson())
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isCreated());
    }

    /**
     * Validate access unauthorized {@link TaskListItemController#findByTaskListId(Long)}
     */
    @Test
    public void accessDeniedTofindByTaskListId() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list/1/item"));
        resultActions.andExpect(status().isUnauthorized());
    }

    /**
     * Validate access unauthorized to {@link TaskListItemController#createTaskListItem(Long, TaskListItemRequest)}
     */
    @Test
    public void accessDeniedToCreateTaskListItem() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/list/1/item"));
        resultActions.andExpect(status().isUnauthorized());
    }

    /**
     * Validate access unauthorized to {@link TaskListItemController#createTaskListSubItem(Long, Long, TaskListItemRequest)}
     */
    @Test
    public void accessDeniedToCreateTaskListSubItem() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/list/1/item/1"));
        resultActions.andExpect(status().isUnauthorized());
    }
}


