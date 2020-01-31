package rocks.isor.todoqu.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Builder;
import lombok.Data;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Task {

    @JsonProperty(access = Access.READ_ONLY)
    private UUID uuid;

    @JsonProperty(access = Access.READ_ONLY)
    private Date createdDate;

    @JsonProperty(access = Access.READ_ONLY)
    private Date updatedDate;

    @JsonProperty(access = Access.READ_ONLY)
    private Date deletedDate;

    private String title;
    private String description;
    private Date procrastinateUntil;
    private Date dueDate;

    @JsonProperty(access = Access.READ_ONLY)
    private boolean done;

    @JsonProperty(access = Access.READ_ONLY)
    private List<Task> todos;

    @JsonProperty(access = Access.READ_ONLY)
    private Task parent;

    public static Task fromVertex(Vertex vertex) {
        TaskBuilder builder = Task.builder();

        vertex.property("uuid").ifPresent(object -> builder.uuid((UUID) object));
        vertex.property("createdDate").ifPresent(object -> builder.createdDate((Date) object));
        vertex.property("updatedDate").ifPresent(object -> builder.updatedDate((Date) object));
        vertex.property("deletedDate").ifPresent(object -> builder.deletedDate((Date) object));

        vertex.property("title").ifPresent(object -> builder.title((String) object));
        vertex.property("description").ifPresent(object -> builder.description((String) object));
        vertex.property("procrastinateUntil").ifPresent(object -> builder.procrastinateUntil((Date) object));
        vertex.property("dueDate").ifPresent(object -> builder.dueDate((Date) object));

        vertex.property("done").ifPresent(object -> builder.done((Boolean) object));

        return builder.build();
    }

}
