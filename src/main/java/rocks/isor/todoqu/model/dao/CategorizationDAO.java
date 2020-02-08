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
public class CategorizationDAO {

    @Autowired
    private GremlinService gremlinService;

    @Autowired
    private GraphTraversalSource g;

    @PostConstruct
    public void setup() {
        gremlinService.createEdgeLabel("categorization", Multiplicity.MULTI);
    }

    public Todo categorize(Task origin, Task target) {
        Vertex originVertex = g.V().has("uuid", origin.getUuid()).next();
        Vertex targetVertex = g.V().has("uuid", target.getUuid()).next();

        return Todo.fromEdge(this.categorize(originVertex, targetVertex));
    }

    public Edge categorize(Vertex origin, Vertex target) {
        Edge persisted = g.addE("categorization")
                .from(origin)
                .to(target)
                .next();

        g.tx().commit();

        return persisted;
    }
}
