package rocks.isor.todoqu.hibernate;

import org.hibernate.boot.model.naming.Identifier;

import java.util.Optional;
import java.util.function.Function;

public final class CustomNamingStrategyUtils {

    private CustomNamingStrategyUtils() {}

    protected static Identifier convertToLowerSnakeCase(final Identifier identifier) {
        return convertToSnakeCase(String::toLowerCase, identifier, null);
    }

    protected static Identifier convertToLowerSnakeCase(
            final Identifier identifier, final String prefix) {
        return convertToSnakeCase(String::toLowerCase, identifier, prefix);
    }

    protected static Identifier convertToUpperSnakeCase(final Identifier identifier) {
        return convertToSnakeCase(String::toUpperCase, identifier, null);
    }

    protected static Identifier convertToUpperSnakeCase(
            final Identifier identifier,final String prefix) {
        return convertToSnakeCase(String::toUpperCase, identifier, prefix);
    }

    private static Identifier convertToSnakeCase(
            final Function<? super String, ? extends String> mapperFunction,
            final Identifier identifier,
            final String prefix) {

        final String newName = Optional.ofNullable(camelToSnakeCase(identifier, prefix))
                .map(mapperFunction)
                .orElse(null);

        return Identifier.toIdentifier(newName);
    }

    private static String camelToSnakeCase(final Identifier identifier, String prefix) {
        if (identifier == null) {
            return null;
        }

        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";

        final String input = identifier.getText();

        String output = input.replaceAll(regex, replacement);
        if (prefix != null) {
            output = prefix + output;
        }

        return output;
    }
}
