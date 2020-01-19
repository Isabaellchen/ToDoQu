package rocks.isor.todoqu.model.dto;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.*;

@Graph
public class LifesWork {

    @Id
    @GeneratedValue
    private String id;

    public LifesWork() {
        this.edges = new ArrayList<>();
        this.vertexes = new ArrayList<>();
    }

    @EdgeSet
    @Getter
    private List<Object> edges;

    @VertexSet
    @Getter
    private List<Object> vertexes;
}
