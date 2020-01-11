package rocks.isor.todoqu.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import rocks.isor.todoqu.model.AbstractDomainEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;

import static rocks.isor.todoqu.hibernate.CustomPhysicalNamingStrategy.TABLE_PREFIX;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"tasks"})
@Entity(name = TABLE_PREFIX + "TASKS")
public class Todo extends AbstractDomainEntity {

    @OneToMany(mappedBy = "parent")
    private List<Todo> tasks;

    @ManyToOne
    private Todo parent;

    private boolean done;

    private String title;

    private String description;
}
