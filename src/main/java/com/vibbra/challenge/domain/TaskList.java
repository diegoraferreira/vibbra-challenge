package com.vibbra.challenge.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TASK_LIST")
@SequenceGenerator(name = "SEQ_TASKLIST", sequenceName = "SEQ_TASKLIST")
public class TaskList {

    private Long id;

    private String title;

    private List<TaskListItem> itens = new ArrayList<>();

    public TaskList() {
        // JPA
    }

    public TaskList(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(generator = "SEQ_TASKLIST", strategy = GenerationType.SEQUENCE)
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

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL)
    public List<TaskListItem> getItens() {
        return itens;
    }

    protected void setItens(List<TaskListItem> itens) {
        this.itens = itens;
    }
}
