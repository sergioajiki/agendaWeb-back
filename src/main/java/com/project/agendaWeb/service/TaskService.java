package com.project.agendaWeb.service;

import com.project.agendaWeb.dto.TaskDto;
import com.project.agendaWeb.entity.LogUpdate;
import com.project.agendaWeb.entity.Task;
import com.project.agendaWeb.entity.User;
import com.project.agendaWeb.exception.BusinessRuleException;
import com.project.agendaWeb.exception.NotFoundException;
import com.project.agendaWeb.repository.LogUpdateRepository;
import com.project.agendaWeb.repository.TaskRepository;
import com.project.agendaWeb.repository.UserRepository;
import com.project.agendaWeb.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogUpdateRepository logUpdateRepository;

    /**
     * Grava uma nova tarefa no banco de dados, garantindo que o intervalo de horário não esteja ocupado.
     *
     * @param taskDto Dados da tarefa a ser salva.
     * @return Task salva.
     */
    public Task saveTask(TaskDto taskDto) {
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com ID: " + taskDto.getUserId()));

        Task task = TaskDto.toEntity(taskDto, user);
        validateTimeSlot(task, null); // Valida se o horário está disponível
        task.setUpdateDate(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);

        // Registrar log de criação
        registerLog("CREATE", null, savedTask.getId(), user);

        return savedTask;
    }

    /**
     * Remove uma tarefa do banco de dados.
     *
     * @param id ID da tarefa a ser removida.
     */
    public void deleteTask(Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada com ID: " + id));

        // Registra Log de exclusão
        registerLog("DELETE", null, existingTask.getId(), existingTask.getUserResponsible());

        taskRepository.delete(existingTask);
    }

    /**
     * Busca uma tarefa pelo ID.
     *
     * @param id ID da tarefa.
     * @return Task encontrada.
     */
    public TaskDto findTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada com ID: " + id));
        return TaskDto.fromEntity(task);
    }

    /**
     * Busca todas as tarefas.
     *
     * @return Lista de tarefas.
     */
    public List<TaskDto> findAllTasksDto() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskDto::fromEntity).toList();
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Atualiza uma tarefa existente.
     *
     * @param id      ID da tarefa a ser atualizada.
     * @param taskDto Dados atualizados da tarefa.
     * @return Tarefa atualizada.
     */
    public Task updateTask(Long id, TaskDto taskDto) {
        // Busca a tarefa existente
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada com ID: " + id));

        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com ID: " + taskDto.getUserId()));

        Task updatedTask = TaskDto.toEntity(taskDto, user);

        // Validação de sobreposição de horários
        validateTimeSlot(updatedTask, id);

        // Usa LogUtils para identificar mudanças
        Map<String, String> changes = LogUtils.getChangeFields(existingTask, updatedTask);

        // Atualiza os dados da tarefa
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setAppointmentDate(updatedTask.getAppointmentDate());
        existingTask.setStartTime(updatedTask.getStartTime());
        existingTask.setEndTime(updatedTask.getEndTime());
        existingTask.setUpdateDate(LocalDateTime.now());
        existingTask.setUserResponsible(updatedTask.getUserResponsible());

        // Registra Log de atualização
        registerLog("UPDATE", changes, existingTask.getId(), existingTask.getUserResponsible());

        return taskRepository.save(existingTask);
    }

    /**
     * Valida se o intervalo de horário está disponível para a tarefa.
     *
     * @param task      Tarefa a ser validada.
     * @param excludeId ID da tarefa a ser excluída da validação (usado em atualizações).
     */
    private void validateTimeSlot(Task task, Long excludeId) {
        boolean isConflict = taskRepository.existsByTimeSlot(
                task.getAppointmentDate(),
                task.getStartTime(),
                task.getEndTime(),
                excludeId // Excluir a própria tarefa durante a validação
        );

        if (isConflict) {
            throw new BusinessRuleException("O intervalo de horário já está ocupado por outra tarefa.");
        }
    }

    private void registerLog(String action, Map<String, String> changes, Long taskId, User user) {
        LogUpdate log = new LogUpdate();
        log.setAction(action);
        log.setChangedFields(changes != null ? changes.toString() : null);
        log.setTaskId(taskId);
        log.setUpdatedBy(user);
        log.setUpdateDateTime(LocalDateTime.now());

        logUpdateRepository.save(log);
    }

}
