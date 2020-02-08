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

		Task testi = tasks.create(
				Task.builder()
						.title("The first step")
						.description("What needs to be done?")
						.build()
		);

		Task testy = tasks.create(
				Task.builder()
						.title("Create a Todo-Graph")
						.description("A task in a task is all we need.")
						.build()
		);

		todos.link(testi, testy);

		Task testee = tasks.create(
				Task.builder()
						.title("Make some tasks")
						.description("Until you are done.")
						.build()
		);

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
