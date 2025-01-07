package com.project.agendaWeb.service;

import com.project.agendaWeb.entity.LogUpdate;
import com.project.agendaWeb.repository.LogUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogUpdateService {
    @Autowired
    private final LogUpdateRepository logUpdateRepository;

    public LogUpdateService(LogUpdateRepository logUpdateRepository) {
        this.logUpdateRepository = logUpdateRepository;
    }

    // Busca todos os Logs
    public List<LogUpdate> getAllLogs(){
        return logUpdateRepository.findAll();
    }

    // Busca os Logs por Tarefa
   // public List<LogUpdate> getLogsByTask()


}
