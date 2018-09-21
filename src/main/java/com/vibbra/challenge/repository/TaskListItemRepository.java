package com.vibbra.challenge.repository;

import com.vibbra.challenge.domain.TaskListItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaskListItemRepository extends CrudRepository<TaskListItem, Long> {

    @Query(value = "SELECT tli FROM TaskListItem tli INNER JOIN tli.taskList tl WHERE tli.id = :taskListItemId and tl.id = :taskListId")
    Optional<TaskListItem> findTaskListItemBy(@Param("taskListId") Long taskListId, @Param("taskListItemId") Long taskListItemId);
}
