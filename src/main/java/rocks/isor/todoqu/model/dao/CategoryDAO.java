package rocks.isor.todoqu.model.dao;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.isor.todoqu.model.dto.Category;
import rocks.isor.todoqu.model.dto.Task;
import rocks.isor.todoqu.service.GremlinService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class CategoryDAO {

    @Autowired
    private GremlinService gremlinService;

    @Autowired
    private GraphTraversalSource g;

    @Autowired
    private CategorizationDAO categorizationDAO;

    @PostConstruct
    public void setup() {
        gremlinService.createVertexLabel("category");

        gremlinService.createPropertyKeyIndex("name", String.class);
        gremlinService.createPropertyKeyIndex("color", String.class);
    }

    public Category create(String name, String color) {
        return Category.fromVertex(createVertex(name, color));
    }

    public Vertex createVertex(String name, String color) {
        Category category = Category.builder()
                .uuid(UUID.randomUUID())
                .createdDate(new Date())
                .updatedDate(null)
                .deletedDate(null)

                .name(name)
                .color(color)

                .build();

        return this.persistVertex(category);
    }

    public List<Task> findAll() {
        List<Task> result = new ArrayList<>();
        g.V().hasLabel("task").forEachRemaining(vertex -> {
            result.add(Task.fromVertex(vertex));
        });

        return result;
    }

    public Task find(UUID uuid) {
        Vertex vertex = findVertex(uuid);
        Task task = Task.fromVertex(vertex);

        task.setTasks(tasks(vertex));

        return task;
    }

    private Vertex findVertex(UUID uuid) {
        return g.V().has("uuid", uuid).next();
    }



    public Category update(UUID uuid, Category updated) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("updatedDate", new Date())

                .property("name", updated.getName())
                .property("color", updated.getColor())
                .next();

        g.tx().commit();

        return Category.fromVertex(vertex);
    }

    public Category delete(UUID uuid) {
        Vertex vertex = this.findVertex(uuid);
        vertex.remove();

        return Category.fromVertex(vertex);
    }

    public Category softDelete(UUID uuid) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("deletedDate", new Date())
                .next();

        g.tx().commit();

        return Category.fromVertex(vertex);
    }

    public Category softDeleteRestore(UUID uuid) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("deletedDate", null)
                .next();

        g.tx().commit();

        return Category.fromVertex(vertex);
    }

    public List<Task> tasks(UUID uuid) {
        return this.tasks(findVertex(uuid));
    }

    public List<Task> tasks(Vertex vertex) {
        Iterator<Edge> tasks = vertex.edges(Direction.OUT, "categorization");

        List<Task> result = new ArrayList<>();

        tasks.forEachRemaining(edge -> {
            Vertex inVertex = edge.inVertex();
            result.add(Task.fromVertex(inVertex));
        });

        return result;
    }

    private Vertex persistVertex(Category category) {
        Vertex vertex = g.addV("task")
                .with("uuid", category.getUuid())
                .with("createdDate", category.getCreatedDate())
                .with("updatedDate", category.getUpdatedDate())
                .with("deletedDate", category.getDeletedDate())

                .with("name", category.getName())
                .with("color", category.getColor())
                .next();

        g.tx().commit();

        return vertex;
    }
}
