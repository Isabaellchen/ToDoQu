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
import rocks.isor.todoqu.model.dao.CategoryDAO;
import rocks.isor.todoqu.model.dto.Category;
import rocks.isor.todoqu.model.dto.Task;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @GetMapping
    public List<Task> getCategories() {
        return categoryDAO.findAll();
    }

    @GetMapping("/{id}")
    public Task getCategory(@PathVariable("id") UUID uuid) {
        return categoryDAO.find(uuid);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getCategoryTasks(@PathVariable("id") UUID uuid) {
        return categoryDAO.tasks(uuid);
    }

    @PostMapping
    public Category createCategory(
            @RequestParam String name,
            @RequestParam Optional<String> color
    ) {
        return categoryDAO.create(
                name,
                color.orElse(null)
        );
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") UUID uuid, @RequestBody Category category) {
        return categoryDAO.update(uuid, category);
    }

    @PatchMapping("/{id}/delete")
    public Category softDelete(@PathVariable("id") UUID uuid) {
        return categoryDAO.softDelete(uuid);
    }

    @PatchMapping("/{id}/restore")
    public Category softDeleteRestore(@PathVariable("id") UUID uuid) {
        return categoryDAO.softDeleteRestore(uuid);
    }

    @DeleteMapping("/{id}")
    public Category deleteTask(@PathVariable("id") UUID uuid) {
        return categoryDAO.delete(uuid);
    }
}
