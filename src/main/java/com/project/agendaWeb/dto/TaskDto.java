package com.project.agendaWeb.dto;

import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


public class TaskDto {
    @Schema(description = "Título da tarefa")
    @NotBlank(message = "O título da tarefa é obrigatório.")
    private String title;
    @Schema(description = "Descrição da tarefa")
    private String description;
    @Schema(description = "Data de agendamento da tarefa")
    @NotNull(message = "A data de agendamento é obrigatória.")
    @FutureOrPresent(message = "A data de agendamento deve ser no presente ou no futuro.")
    private LocalDate appointmentDate;
    @Schema(description = "Horário de início da tarefa")
    @NotNull(message = "O horário de início é obrigatório.")
    private LocalTime startTime;
    @Schema(description = "Horário de término da tarefa")
    @NotNull(message = "O horário de término é obrigatório.")
    private LocalTime endTime;
    @Schema(description = "ID do usuário responsável pela tarefa")
    @NotNull(message = "O ID do usuário responsável é obrigatório.")
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static Task toEntity(TaskDto taskDto, User user){
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setAppointmentDate(convertToDate(taskDto.getAppointmentDate()));
        task.setStartTime(convertToTime(taskDto.getStartTime()));
        task.setEndTime(convertToTime(taskDto.getEndTime()));
        task.setUpdateDate(new Date()); //Define a data de criação/atualização
        return task;
    }

    private static Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static Date convertToTime(LocalTime localTime) {
        return Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());
    }
}
