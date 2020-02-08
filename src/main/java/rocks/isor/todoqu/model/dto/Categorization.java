package rocks.isor.todoqu.model.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.tinkerpop.gremlin.structure.Edge;

@Data
@Builder
public class Categorization {

    private Category category;
    private Task task;

    public static Categorization fromEdge(Edge edge) {
        return Categorization.builder()
                .category(Category.fromVertex(edge.outVertex()))
                .task(Task.fromVertex(edge.inVertex()))
                .build();
    }
}
