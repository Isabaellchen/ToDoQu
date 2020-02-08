package rocks.isor.todoqu.model.dao;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.isor.todoqu.model.dto.Category;
import rocks.isor.todoqu.model.dto.Task;
import rocks.isor.todoqu.model.dto.Todo;
import rocks.isor.todoqu.service.GremlinService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Component
public class TaskDAO {

    @Autowired
    private GraphTraversalSource g;

    @Autowired
    private GremlinService gremlinService;

    @Autowired
    private TodoDAO todoDAO;

    @PostConstruct
    private void setup() {
        gremlinService.createVertexLabel("task");

        gremlinService.createPropertyKeyIndex("uuid", UUID.class);

        gremlinService.createVertexProperty("createdDate", Date.class);
        gremlinService.createVertexProperty("updatedDate", Date.class);
        gremlinService.createVertexProperty("deletedDate", Date.class);

        gremlinService.createVertexProperty("title", String.class);
        gremlinService.createVertexProperty("description", String.class);
        gremlinService.createVertexProperty("procrastinateUntil", Date.class);
        gremlinService.createVertexProperty("dueDate", Date.class);

        gremlinService.createVertexProperty("done", Boolean.class);
    }

    public Task create(Task task) {
        return Task.fromVertex(createVertex(task));
    }

    public Vertex createVertex(Task task) {
        task.setUuid(UUID.randomUUID());
        task.setCreatedDate(new Date());

        task.setDone(false);

        return this.persistVertex(task);
    }

    public Task addTodo(UUID uuid, Task task) {
        Vertex parent = this.findVertex(uuid);
        Vertex todo = this.createVertex(task);

        Edge link = todoDAO.link(parent, todo);
        return Task.fromVertex(link.inVertex());
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

        task.setTasks(todos(vertex));
        task.setParent(parent(vertex));
        task.setCategories(categories(vertex));

        return task;
    }

    private Vertex findVertex(UUID uuid) {
        return g.V().has("uuid", uuid).next();
    }

    public List<Task> todos(UUID uuid) {
        return this.todos(findVertex(uuid));
    }

    public List<Task> todos(Vertex vertex) {
        Iterator<Edge> todos = vertex.edges(Direction.OUT, "todo");

        List<Task> result = new ArrayList<>();

        todos.forEachRemaining(edge -> {
            Vertex inVertex = edge.inVertex();
            result.add(Task.fromVertex(inVertex));
        });

        return result;
    }

    public Task parent(UUID uuid) {
        return this.parent(findVertex(uuid));
    }

    public Task parent(Vertex vertex) {
        Iterator<Edge> parents = vertex.edges(Direction.IN, "todo");

        Task result = null;

        if (parents.hasNext()) {
            Todo relation = Todo.fromEdge(parents.next());
            result = relation.getOrigin();
        }

        return result;
    }

    public List<Category> categories(UUID uuid) {
        return categories(findVertex(uuid));
    }

    public List<Category> categories(Vertex vertex) {
        Iterator<Edge> todos = vertex.edges(Direction.IN, "categorization");

        List<Category> result = new ArrayList<>();

        todos.forEachRemaining(edge -> {
            Vertex outVertex = edge.outVertex();
            result.add(Category.fromVertex(outVertex));
        });

        return result;
    }

    public Task setDone(UUID uuid, Boolean done) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("updatedDate", new Date())
                .property("done", done)
                .next();

        g.tx().commit();

        return Task.fromVertex(vertex);
    }

    public Task update(UUID uuid, Task updated) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("updatedDate", new Date())

                .property("title", updated.getTitle())
                .property("description", updated.getDescription())
                .property("procrastinateUntil", updated.getProcrastinateUntil())
                .property("dueDate", updated.getDueDate())
                .next();

        g.tx().commit();

        return Task.fromVertex(vertex);
    }

    public Task delete(UUID uuid) {
        Vertex vertex = this.findVertex(uuid);
        vertex.remove();

        return Task.fromVertex(vertex);
    }

    public Task softDelete(UUID uuid) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("deletedDate", new Date())
                .next();

        g.tx().commit();

        return Task.fromVertex(vertex);
    }

    public Task softDeleteRestore(UUID uuid) {
        Vertex vertex = g.V()
                .has("uuid", uuid)
                .property("deletedDate", null)
                .next();

        g.tx().commit();

        return Task.fromVertex(vertex);
    }

    private Vertex persistVertex(Task task) {
        Vertex vertex = g.addV("task")
                .with("uuid", task.getUuid())
                .with("createdDate", task.getCreatedDate())
                .with("updatedDate", task.getUpdatedDate())
                .with("deletedDate", task.getDeletedDate())

                .with("title", task.getTitle())
                .with("description", task.getDescription())
                .with("procrastinateUntil", task.getProcrastinateUntil())
                .with("dueDate", task.getDueDate())
                .with("done", task.isDone())
                .next();

        g.tx().commit();

        return vertex;
    }
}
