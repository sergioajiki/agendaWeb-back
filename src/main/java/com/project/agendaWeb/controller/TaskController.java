package com.project.agendaWeb.controller;

import com.project.agendaWeb.dto.TaskDto;
import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Tag(name = "Tasks", description = "Gerenciamento de tarefas")
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Cria uma nova tarefa.
     *
     * @param taskDto DTO com os dados da tarefa.
     * @return DTO da tarefa criada.
     */
    @PostMapping
    @Operation(summary = "Cadastrar uma nova tarefa", description = "Cria uma nova tarefa no sistema")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        Task savedTask = taskService.saveTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto.fromEntity(savedTask));
    }

    /**
     * Atualiza uma tarefa existente.
     *
     * @param id      ID da tarefa a ser atualizada.
     * @param taskDto DTO com os dados atualizados.
     * @return DTO da tarefa atualizada.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma tarefa", description = "Atualiza os dados de uma tarefa existente")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(TaskDto.fromEntity(updatedTask));
    }

    /**
     * Remove uma tarefa existente.
     *
     * @param id ID da tarefa a ser removida.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover uma tarefa", description = "Remove uma tarefa do sistema")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retorna todas as tarefas.
     *
     * @return Lista de DTOs das tarefas.
     */
    @GetMapping
    @Operation(summary = "Listar todas as tarefas", description = "Retorna uma lista com todas as tarefas")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.findAllTasks()
                .stream()
                .map(TaskDto::fromEntity) // Converte Task para TaskDto
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retorna uma tarefa pelo ID.
     *
     * @param id ID da tarefa.
     * @return DTO da tarefa encontrada.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma tarefa pelo ID", description = "Retorna os dados de uma tarefa específica")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto taskDto = taskService.findTaskById(id);
        return ResponseEntity.ok(taskDto);
    }
}
