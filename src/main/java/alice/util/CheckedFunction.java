package alice.util;

import alice.command.InvalidCommandException;

/**
 * Represents a function that accepts one argument and produces a result.
 * It can take in function that throws an {@code InvalidCommandException}.
 *
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws InvalidCommandException;
}
