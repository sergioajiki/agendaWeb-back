package com.project.agendaWeb.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate; // Apenas data no formato yyyy-MM-dd

    private LocalDateTime updateDate;
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime; // Apenas horário (hh:mm)

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime; // Apenas horário (hh:mm)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userResponsible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
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

    public User getUserResponsible() {
        return userResponsible;
    }

    public void setUserResponsible(User userResponsible) {
        this.userResponsible = userResponsible;
    }
}
