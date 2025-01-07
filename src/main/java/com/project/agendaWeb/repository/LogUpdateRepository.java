package com.project.agendaWeb.repository;


import com.project.agendaWeb.entity.LogUpdate;
import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogUpdateRepository extends JpaRepository<LogUpdate, Long> {
    // Busca por tarefa
    List<LogUpdate> findByTask(Task task);
    // Busca por intervalo de tempo
    List<LogUpdate> findByUpdateDateTimeBetween(LocalDateTime start, LocalDateTime end );
    // Busca por usuário
    List<LogUpdate> findByUpdatedBy(User user);
}
