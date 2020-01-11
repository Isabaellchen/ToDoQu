package rocks.isor.todoqu.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitAnyDiscriminatorColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitAnyKeyColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitCollectionTableNameSource;
import org.hibernate.boot.model.naming.ImplicitDiscriminatorColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitIdentifierColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitIndexColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitIndexNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.naming.ImplicitMapKeyColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitPrimaryKeyJoinColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitTenantIdColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitUniqueKeyNameSource;

public class CustomImplicitNamingStrategy implements ImplicitNamingStrategy {

    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineCollectionTableName(ImplicitCollectionTableNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineDiscriminatorColumnName(ImplicitDiscriminatorColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineTenantIdColumnName(ImplicitTenantIdColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineIdentifierColumnName(ImplicitIdentifierColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determinePrimaryKeyJoinColumnName(ImplicitPrimaryKeyJoinColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineAnyDiscriminatorColumnName(ImplicitAnyDiscriminatorColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineAnyKeyColumnName(ImplicitAnyKeyColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineMapKeyColumnName(ImplicitMapKeyColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineListIndexColumnName(ImplicitIndexColumnNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineForeignKeyName(ImplicitForeignKeyNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineUniqueKeyName(ImplicitUniqueKeyNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }

    @Override
    public Identifier determineIndexName(ImplicitIndexNameSource source) {
        throw new NotImplementedException("Implicit naming strategy not yet implemented!");
    }
}