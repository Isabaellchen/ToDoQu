package rocks.isor.todoqu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rocks.isor.todoqu.model.dao.TaskDAO;
import rocks.isor.todoqu.model.dto.Task;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskDAO taskDAO;

    @GetMapping
    public List<Task> getTasks() {
        return taskDAO.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") UUID uuid) {
        return taskDAO.find(uuid);
    }

    @GetMapping("/{id}/todos")
    public List<Task> getTaskTodos(@PathVariable("id") UUID uuid) {
        return taskDAO.todos(uuid);
    }

    @GetMapping("/{id}/parent")
    public Task getTaskParent(@PathVariable("id") UUID uuid) {
        return taskDAO.parent(uuid);
    }

    @PostMapping
    public Task createTask(
            @RequestParam String title,
            @RequestParam Optional<String> description,
            @RequestParam Optional<Date> procrastinateUntil,
            @RequestParam Optional<Date> dueDate
    ) {
        return taskDAO.create(
                title,
                description.orElse(null),
                procrastinateUntil.orElse(null),
                dueDate.orElse(null)
        );
    }

    @PostMapping("/{id}/todo")
    public Task addTodo(
            @PathVariable("id") UUID uuid,
            @RequestParam String title,
            @RequestParam Optional<String> description,
            @RequestParam Optional<Date> procrastinateUntil,
            @RequestParam Optional<Date> dueDate
    ) {
        return taskDAO.addTodo(
                uuid,
                title,
                description.orElse(null),
                procrastinateUntil.orElse(null),
                dueDate.orElse(null)
        );
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable("id") UUID uuid, @RequestBody Task task) {
        return taskDAO.update(uuid, task);
    }

    @PatchMapping("/{id}/done")
    public Task setDone(
            @PathVariable("id") UUID uuid,
            @RequestParam Boolean done
    ) {
        return taskDAO.setDone(uuid, done);
    }

    @PatchMapping("/{id}/delete")
    public Task softDelete(@PathVariable("id") UUID uuid) {
        return taskDAO.softDelete(uuid);
    }

    @PatchMapping("/{id}/restore")
    public Task softDeleteRestore(@PathVariable("id") UUID uuid) {
        return taskDAO.softDeleteRestore(uuid);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable("id") UUID uuid) {
        return taskDAO.delete(uuid);
    }
}
