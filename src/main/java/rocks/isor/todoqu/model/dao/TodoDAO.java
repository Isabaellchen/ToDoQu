package rocks.isor.todoqu.model.dao;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.Multiplicity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.isor.todoqu.model.dto.Task;
import rocks.isor.todoqu.model.dto.Todo;
import rocks.isor.todoqu.service.GremlinService;

import javax.annotation.PostConstruct;

@Component
public class TodoDAO {

    @Autowired
    private GraphTraversalSource g;

    @Autowired
    private GremlinService gremlinService;

    @PostConstruct
    private void setup() {
        gremlinService.createEdgeLabel("todo", Multiplicity.ONE2MANY);
    }

    public Todo link(Task origin, Task target) {
        Vertex originVertex = g.V().has("uuid", origin.getUuid()).next();
        Vertex targetVertex = g.V().has("uuid", target.getUuid()).next();

        return Todo.fromEdge(this.link(originVertex, targetVertex));
    }

    public Edge link(Vertex origin, Vertex target) {
        Edge persisted = g.addE("todo")
                .from(origin)
                .to(target)
                .next();

        g.tx().commit();

        return persisted;
    }
}
