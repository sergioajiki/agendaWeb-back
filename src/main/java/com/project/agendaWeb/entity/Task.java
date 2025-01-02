package com.project.agendaWeb.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userResponsible;
}
