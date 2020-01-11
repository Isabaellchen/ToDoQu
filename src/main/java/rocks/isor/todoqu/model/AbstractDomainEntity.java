package rocks.isor.todoqu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import rocks.isor.todoqu.hibernate.UUIDGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractDomainEntity {

    @Id
    @GeneratedValue(generator = UUIDGenerator.NAME)
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false)
    private UUID id;

    @Column(insertable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdDate;

    @Column(insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedDate;

    @Column(insertable = false)
    @JsonIgnore
    private Date deletedDate;
}
