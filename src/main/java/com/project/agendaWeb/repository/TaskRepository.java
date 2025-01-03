package com.project.agendaWeb.repository;

import com.project.agendaWeb.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Task t " +
            "WHERE t.appointmentDate = :appointmentDate " +
            "AND t.id != :excludeId " +
            "AND ((:startTime BETWEEN t.startTime AND t.endTime) " +
            "OR (:endTime BETWEEN t.startTime AND t.endTime)" +
            "OR (t.startTime BETWEEN :startTime AND :endTime))")
    boolean existByTimeSlot(@Param("appointmentDate") Date appointmentDate,
                            @Param("startTime") Date startTime,
                            @Param("endTime") Date endTime,
                            @Param("excludeId") Long excludeId);
}
