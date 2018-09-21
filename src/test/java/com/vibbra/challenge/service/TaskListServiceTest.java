package com.vibbra.challenge.service;

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
public class TaskListServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private TaskListRepository repository;

    @InjectMocks
    private TaskListService service;

    @Test
    public void findById() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("No task list found for id: 1");

        service.findById(1L);
    }
}