package rocks.isor.todoqu;

import com.microsoft.spring.data.gremlin.common.GremlinFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rocks.isor.todoqu.model.dto.LifesWork;
import rocks.isor.todoqu.model.dto.Task;
import rocks.isor.todoqu.model.dto.Todo;
import rocks.isor.todoqu.model.repository.LifesWorkRepository;
import rocks.isor.todoqu.model.repository.TaskRepository;
import rocks.isor.todoqu.model.repository.TodoRepository;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication
public class TodoquApplication {

	@Autowired
	private LifesWorkRepository graph;

	@Autowired
	private TaskRepository tasks;

	@Autowired
	private TodoRepository todos;

    @Autowired
    private GremlinFactory factory;

	public static void main(String[] args) {
		SpringApplication.run(TodoquApplication.class, args);
	}

	@PostConstruct
	public void setup() throws Exception {
		this.graph.deleteAll();

		Task firstStep = Task.builder()
				//.id(89757L)
				.title("The first step")
				.description("What needs to be done?")
				.build();

		Task createGraph = Task.builder()
				//.id(666666L)
				.title("Create a Todo-Graph")
				.description("A task in a task is all we need")
				.build();

		Todo doWork = Todo.builder()
				//.id(2333L)
				.parent(firstStep)
				.todo(createGraph)
				.build();

		LifesWork lifesWork = new LifesWork();
		lifesWork.getVertexes().add(firstStep);
		lifesWork.getVertexes().add(createGraph);
		lifesWork.getEdges().add(doWork);

		this.graph.save(lifesWork);
	}

}
