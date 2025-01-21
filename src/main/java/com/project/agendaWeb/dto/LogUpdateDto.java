package com.project.agendaWeb.dto;


import com.project.agendaWeb.entity.LogUpdate;

import java.time.LocalDateTime;


public class LogUpdateDto {
    private Long id;
    private String action;
    private String changedFields;
    private LocalDateTime updateDateTime;
    private UpdatedByDto updatedBy;
    private Long taskId;

    public LogUpdateDto(
            Long id,
            String action,
            String changedFields,
            LocalDateTime updateDateTime,
            UpdatedByDto updatedBy,
            Long taskId
    ) {
        this.id = id;
        this.action = action;
        this.changedFields = changedFields;
        this.updateDateTime = updateDateTime;
        this.updatedBy = updatedBy;
        this.taskId = taskId;
    }

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

    public UpdatedByDto getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UpdatedByDto updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public static LogUpdateDto fromEntity(LogUpdate log) {
        return new LogUpdateDto(
                log.getId(),
                log.getAction(),
                log.getChangedFields(),
                log.getUpdateDateTime(),
                new UpdatedByDto(log.getUpdatedBy().getId(), log.getUpdatedBy().getName()),
                log.getTaskId() != null ? log.getTaskId() : null
        );
    }

}
