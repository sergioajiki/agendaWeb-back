package com.project.agendaWeb.controller;

import com.project.agendaWeb.entity.LogUpdate;
import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.service.LogUpdateService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogUpdateController {
    private final LogUpdateService logUpdateService;

    public LogUpdateController(LogUpdateService logUpdateService) {
        this.logUpdateService = logUpdateService;
    }

    @GetMapping
    public List<LogUpdate> getAllLogs() {
        return logUpdateService.getAllLogs();
    }

    @GetMapping("/task/{taskId}")
    public List<LogUpdate> getLogsByTask(@PathVariable Long taskId) {
        Task task = new Task();
        task.setId(taskId);
        return logUpdateService.getLogsByTask_Id(task);
    }

    @GetMapping("/period")
    public List<LogUpdate> getLogsByPeriod (
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
            ) {
        return logUpdateService.getLogsByPeriod(start, end);
    }

    @GetMapping("/user/{userId}")
    public List<LogUpdate> getLogsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return logUpdateService.getLogsByUser(user);
    }
}
