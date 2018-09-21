package com.vibbra.challenge.domain;

import javax.persistence.*;

@Entity
@Table(name = "TASK_LIST_ITEM", indexes = {@Index(name = "IX_TASK_LIST_ID", columnList = "TASK_LIST_ID")})
@SequenceGenerator(name = "SEQ_TASKLIST_ITEM", sequenceName = "SEQ_TASKLIST_ITEM")
public class TaskListItem {

    private Long id;

    private String title;

    private String description;

    private TaskList taskList;

    private TaskListItem childTaskListItem;

    @Id
    @GeneratedValue(generator = "SEQ_TASKLIST_ITEM", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name = "TASK_LIST_ID", foreignKey = @ForeignKey(name = "FK_TASK_LIST"))
    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TASK_LIST_ITEM_ID", foreignKey = @ForeignKey(name = "FK_TASK_ITEM_CHILD"))
    public TaskListItem getChildTaskListItem() {
        return childTaskListItem;
    }

    public void setChildTaskListItem(TaskListItem childTaskListItem) {
        this.childTaskListItem = childTaskListItem;
    }
}
