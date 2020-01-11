package rocks.isor.todoqu.hibernate;

import org.hibernate.annotations.GenericGenerator;

public final class UUIDGenerator {
    @GenericGenerator(name = UUIDGenerator.NAME, strategy = "uuid2")
    public static final String NAME = "system-uuid";

    private UUIDGenerator() {}
}
