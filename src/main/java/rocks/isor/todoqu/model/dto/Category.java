package rocks.isor.todoqu.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Category {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date deletedDate;

    private String name;
    private String color;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Task> tasks;

    public static Category fromVertex(Vertex vertex) {
        CategoryBuilder builder = Category.builder();

        vertex.property("name").ifPresent(object -> builder.name((String) object));
        vertex.property("color").ifPresent(object -> builder.color((String) object));

        return builder.build();

    }

}
