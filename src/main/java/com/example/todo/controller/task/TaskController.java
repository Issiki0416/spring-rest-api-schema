package com.example.todo.controller.task;

import com.example.todo.controller.TasksApi;
import com.example.todo.model.PageDTO;
import com.example.todo.model.TaskDTO;
import com.example.todo.model.TaskForm;
import com.example.todo.model.TaskListDTO;
import com.example.todo.service.task.TaskEntity;
import com.example.todo.service.task.TaskService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {
    private final TaskService taskService;
    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
        var entity = taskService.find(taskId);

        var dto = toTaskDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {
        var entity = taskService.create(form.getTitle());
        var dto = toTaskDTO(entity);

        return ResponseEntity
                .created(URI.create("/tasks/" + entity.getId())) // locationヘッダに追加
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> editTask(Long taskId, TaskForm taskForm) {
        var entity = taskService.update(taskId,taskForm.getTitle());
        var dto = toTaskDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TaskListDTO>  listTasks(Integer limit,Long offset) {
        var entityList = taskService.find(limit, offset);
        var dtoList = entityList.stream()
                .map(TaskController::toTaskDTO).collect(Collectors.toList());
        var dto = new TaskListDTO();
        var pageDTO = new PageDTO();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        dto.setPages(pageDTO);
        dto.setResults(dtoList);
        return ResponseEntity.ok(dto);
    }

    private static TaskDTO toTaskDTO(TaskEntity taskEntity) {
        var taskDto = new TaskDTO();
        taskDto.setId(taskEntity.getId());
        taskDto.setTitle(taskEntity.getTitle());
        return taskDto;
    }
}
