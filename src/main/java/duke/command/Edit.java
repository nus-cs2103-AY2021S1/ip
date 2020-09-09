package duke.command;

/**
 * This interface represents an edit that can be applied to an object.
 *
 * @param <T> The type of object to edit.
 */
public interface Edit<T> {
    void apply(T object) throws EditingException;
}
