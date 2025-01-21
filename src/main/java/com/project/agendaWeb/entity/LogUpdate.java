package com.project.agendaWeb.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LogUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String action; // Ações: CREATE, UPDATE, DELETE
    @Column(nullable = true, columnDefinition = "TEXT")
    private String changedFields; // Campos e valores alterados
    @Column(nullable = false)
    private LocalDateTime updateDateTime; // Data e hora da atualização

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User updatedBy; // Usuário que fez a atualização

    //@ManyToOne
    // ID da tarefa (sem vínculo de chave estrangeira, para manter as informações do log após a exclusão da tarefa)
    @JoinColumn(name = "task_id", nullable = true)
    private Long taskId; // Tarefa associada à alteração

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChangedFields() {
        return changedFields;
    }

    public void setChangedFields(String changedFields) {
        this.changedFields = changedFields;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
