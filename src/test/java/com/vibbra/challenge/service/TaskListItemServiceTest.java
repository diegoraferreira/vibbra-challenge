package com.vibbra.challenge.service;

import com.vibbra.challenge.repository.TaskListItemRepository;
import com.vibbra.challenge.repository.TaskListRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TaskListItemServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private TaskListItemRepository taskListItemRepository;

    @Mock
    private TaskListRepository taskListRepository;

    @InjectMocks
    private TaskListItemService service;

    @Test
    public void findByTaskListId() {
        Mockito.when(taskListRepository.findById(1L)).thenReturn(Optional.empty());
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("No task list found for id: 1");

        service.findByTaskListId(1L);
    }

    @Test
    public void createTaskListItem() {
        Mockito.when(taskListRepository.findById(1L)).thenReturn(Optional.empty());
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("No task list found for id: 1");

        service.createTaskListItem(1L, null);
    }

    @Test
    public void createTaskListSubItem() {
        Mockito.when(taskListItemRepository.findTaskListItemBy(1L, 1L)).thenReturn(Optional.empty());
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("No task list item found for id: 1 and task list id: 1");

        service.createTaskListSubItem(1L, 1L, null);
    }
}