package rocks.isor.todoqu.service;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.*;
import org.janusgraph.core.schema.EdgeLabelMaker;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.core.schema.SchemaStatus;
import org.janusgraph.core.schema.VertexLabelMaker;
import org.janusgraph.graphdb.database.management.ManagementSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GremlinService {

    private JanusGraph graph;

    @PostConstruct
    public void setup() {
        graph = JanusGraphFactory
                .open("conf/janusgraph-cassandra-es.properties");
    }

    @Bean
    public JanusGraph graph() {
        return graph;
    }

    @Bean
    public GraphTraversalSource traversal() {
        return graph.traversal();
    }

    public EdgeLabel createEdgeLabel(String name) {
        return createEdgeLabel(name, Multiplicity.SIMPLE, false);
    }

    public EdgeLabel createEdgeLabel(String name, Multiplicity multiplicity) {
        return createEdgeLabel(name, multiplicity, false);
    }

    public EdgeLabel createEdgeLabel(String name, Multiplicity multiplicity, boolean undirected) {
        graph.tx().rollback();
        JanusGraphManagement management = graph.openManagement();
        EdgeLabel edgeLabel = management.getEdgeLabel(name);

        if (edgeLabel == null) {
            EdgeLabelMaker edgeLabelMaker = management.makeEdgeLabel(name)
                    .multiplicity(multiplicity);

            if (undirected) {
                edgeLabelMaker.unidirected();
            }

            edgeLabel = edgeLabelMaker.make();

            management.commit();
            graph.tx().commit();
        } else {
            management.rollback();
            graph.tx().rollback();
        }

        return edgeLabel;
    }

    public VertexLabel createVertexLabel(String name) {
        graph.tx().rollback();
        JanusGraphManagement management = graph.openManagement();
        VertexLabel vertexLabel = management.getVertexLabel(name);

        if (vertexLabel == null) {
            VertexLabelMaker vertexLabelMaker = management.makeVertexLabel(name);
            vertexLabel = vertexLabelMaker.make();

            management.commit();
            graph.tx().commit();
        } else {
            management.rollback();
            graph.tx().rollback();
        }

        return vertexLabel;
    }

    public PropertyKey createVertexProperty(String name, Class<?> clazz) {
        return this.createVertexProperty(name, clazz, Cardinality.SINGLE);
    }


    public PropertyKey createVertexProperty(String name, Class<?> clazz, Cardinality cardinality) {
        graph.tx().rollback();
        JanusGraphManagement management = graph.openManagement();
        PropertyKey propertyKey = management.getPropertyKey(name);

        if (propertyKey == null) {
            propertyKey = management.makePropertyKey(name)
                    .dataType(clazz)
                    .cardinality(cardinality)
                    .make();

            management.commit();
            graph.tx().commit();
        } else {
            management.rollback();
            graph.tx().rollback();
        }

        return propertyKey;
    }

    public JanusGraphIndex createPropertyKeyIndex(String name, Class<?> clazz) {
        return this.createPropertyKeyIndex(name, clazz, Cardinality.SINGLE);
    }

    public JanusGraphIndex createPropertyKeyIndex(String propertyKeyName, Class<?> clazz, Cardinality cardinality) {
        graph.tx().rollback();
        JanusGraphManagement management = graph.openManagement();

        String indexName = new StringBuilder()
                .append("by")
                .append(propertyKeyName.substring(0, 1).toUpperCase())
                .append(propertyKeyName.substring(1))
                .toString();

        JanusGraphIndex graphIndex = management.getGraphIndex(indexName);
        PropertyKey propertyKey = management.getPropertyKey(propertyKeyName);

        if (propertyKey == null && graphIndex == null) {
            propertyKey = management.makePropertyKey(propertyKeyName)
                    .dataType(clazz)
                    .cardinality(cardinality)
                    .make();

            graphIndex = management.buildIndex(indexName, Vertex.class)
                    .addKey(propertyKey)
                    .unique()
                    .buildCompositeIndex();


            management.commit();
            graph.tx().commit();
//            try {
//                ManagementSystem.awaitGraphIndexStatus(graph, indexName).status(SchemaStatus.REGISTERED).call();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } else {
            management.rollback();
            graph.tx().rollback();
        }

        return graphIndex;
    }
}
