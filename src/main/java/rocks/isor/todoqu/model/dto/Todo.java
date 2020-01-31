package rocks.isor.todoqu.model.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.tinkerpop.gremlin.structure.Edge;

@Data
@Builder
public class Todo {

    private Task origin;
    private Task target;

    public static Todo fromEdge(Edge edge) {
        return Todo.builder()
                .origin(Task.fromVertex(edge.outVertex()))
                .target(Task.fromVertex(edge.inVertex()))
                .build();
    }
}
