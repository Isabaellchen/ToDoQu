package rocks.isor.todoqu.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    public static final String TABLE_PREFIX = "TBL_";
    public static final String VIEW_PREFIX = "VW_";
    public static final String TRIGGER_PREFIX = "TRG_";

    @Value("${spring.jpa.hibernate.naming.domain-prefix}")
    private String domainPrefix;

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier,
                                            final JdbcEnvironment jdbcEnv) {

        return CustomNamingStrategyUtils.convertToLowerSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier,
                                           final JdbcEnvironment jdbcEnv) {

        return CustomNamingStrategyUtils.convertToLowerSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier,
                                           final JdbcEnvironment jdbcEnv) {

        return CustomNamingStrategyUtils.convertToLowerSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier,
                                             final JdbcEnvironment jdbcEnv) {

        return CustomNamingStrategyUtils.convertToLowerSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier,
                                          final JdbcEnvironment jdbcEnv) {

        return CustomNamingStrategyUtils.convertToUpperSnakeCase(identifier, domainPrefix);
    }
}