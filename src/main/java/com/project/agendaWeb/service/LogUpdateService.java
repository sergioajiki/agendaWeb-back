package com.project.agendaWeb.service;

import com.project.agendaWeb.dto.LogUpdateDto;
import com.project.agendaWeb.entity.LogUpdate;
import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.repository.LogUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogUpdateService {
    @Autowired
    private final LogUpdateRepository logUpdateRepository;

    public LogUpdateService(LogUpdateRepository logUpdateRepository) {
        this.logUpdateRepository = logUpdateRepository;
    }

    // Busca todos os Logs
    public List<LogUpdateDto> getAllLogs() {

        List<LogUpdate> logs = logUpdateRepository.findAll();

        return logs.stream()
                .map(LogUpdateDto::fromEntity)
                .toList();
    }

    // Busca os Logs por Tarefa
    public List<LogUpdate> getLogsByTaskId(Long task) {
        return logUpdateRepository.findByTaskId(task);
    }

    // Busca Logs por período
    public List<LogUpdate> getLogsByPeriod(LocalDateTime start, LocalDateTime end) {
        return logUpdateRepository.findByUpdateDateTimeBetween(start, end);
    }

    // Busca Logs por usuário
    public List<LogUpdate> getLogsByUser(User user) {
        return logUpdateRepository.findByUpdatedBy(user);
    }

    public void saveLog(LogUpdate logUpdate) {
        logUpdateRepository.save(logUpdate);
    }

}
