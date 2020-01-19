package rocks.isor.todoqu.model.dto;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Vertex
public class Task {

    @Id
    @GeneratedValue
    private String id;

    private boolean done;

    private String title;

    private String description;

    private Date dueDate;

    private Date procrastinateUntil;
}
