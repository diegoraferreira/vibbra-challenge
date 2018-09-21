package com.vibbra.challenge.repository;

import com.vibbra.challenge.domain.TaskList;
import org.springframework.data.repository.CrudRepository;

public interface TaskListRepository extends CrudRepository<TaskList, Long> {

}