package com.project.agendaWeb.repository;

import com.project.agendaWeb.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Task t " +
            "WHERE t.appointmentDate = :appointmentDate " +
            "AND (:excludeId IS NULL OR t.id != :excludeId) " +
            "AND ((:startTime BETWEEN t.startTime AND t.endTime) " +
            "OR (:endTime BETWEEN t.startTime AND t.endTime) " +
            "OR (t.startTime BETWEEN :startTime AND :endTime))")
    boolean existsByTimeSlot(@Param("appointmentDate") LocalDate appointmentDate,
                             @Param("startTime") LocalTime startTime,
                             @Param("endTime") LocalTime endTime,
                             @Param("excludeId") Long excludeId);

    @Query("SELECT t FROM Task t " +
            "WHERE t.appointmentDate = :appointmentDate " +
            "AND t.userResponsible.id = :userId " +
            "AND t.id <> :taskId " +
            "AND ((t.startTime < :endTime AND t.endTime > :startTime))")
    List<Task> findTasksByTimeRange(@Param("appointmentDate") LocalDate appointmentDate,
                                    @Param("startTime") LocalTime startTime,
                                    @Param("endTime") LocalTime endTime,
                                    @Param("userId") Long userId,
                                    @Param("taskId") Long taskId);
}
