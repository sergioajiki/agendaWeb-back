package com.project.agendaWeb.controller;

import com.project.agendaWeb.entity.LogUpdate;
import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.service.LogUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "Logs", description = "Logs de atualização das tarefas")
@RequestMapping("/logs")
public class LogUpdateController {

    private final LogUpdateService logUpdateService;

    public LogUpdateController(LogUpdateService logUpdateService) {
        this.logUpdateService = logUpdateService;
    }

    @Operation(summary = "Lista todos os logs")
    @GetMapping
    public List<LogUpdate> getAllLogs() {
        return logUpdateService.getAllLogs();
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Lista os logs de uma tarefa")
    public List<LogUpdate> getLogsByTask(@PathVariable Long taskId) {
        return logUpdateService.getLogsByTaskId(taskId);
    }

    @GetMapping("/period")
    @Operation(summary = "Lista os logs por um periodo")
    public List<LogUpdate> getLogsByPeriod(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return logUpdateService.getLogsByPeriod(start, end);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Lista os logs de um usuário")
    public List<LogUpdate> getLogsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return logUpdateService.getLogsByUser(user);
    }
}
