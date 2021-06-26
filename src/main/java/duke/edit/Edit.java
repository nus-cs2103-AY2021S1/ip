package duke.edit;

/**
 * An interface representing an edit that can be applied to an object.
 *
 * @param <T> Type of object to edit.
 */
public interface Edit<T> {

    /**
     * Applies this Edit to an object. See the documentation of the class itself for a description of what
     * this Edit does.
     *
     * @param object Object to edit.
     * @throws EditingException This is thrown if the attempted edit is invalid.
     */
    public void apply(T object) throws EditingException;
}
