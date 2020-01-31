package rocks.isor.todoqu;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rocks.isor.todoqu.model.dao.TaskDAO;
import rocks.isor.todoqu.model.dao.TodoDAO;
import rocks.isor.todoqu.model.dto.Task;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TodoquApplication {

	@Autowired
	private Graph graph;

	@Autowired
	private TodoDAO todos;

	@Autowired
	private TaskDAO tasks;

	public static void main(String[] args) {
		SpringApplication.run(TodoquApplication.class, args);
	}

	@PostConstruct
	public void setup() {

		Task testi = tasks.create("The first step", "What needs to be done?", null, null);
		Task testy = tasks.create("Create a Todo-Graph", "A task in a task is all we need.", null, null);

		todos.link(testi, testy);

		Task testee = tasks.create("Make some tasks", "Until you are done.", null, null);

		todos.link(testy, testee);

		graph.vertices().forEachRemaining(vertex -> {
			System.out.println(vertex.label());
			vertex.properties().forEachRemaining(vertexProperty -> {
				String propertyLabel = vertexProperty.label();
				Object propertyValue = vertex.value(propertyLabel);
				System.out.println(propertyLabel + ": " + propertyValue.toString());
			});
		});
	}

}
