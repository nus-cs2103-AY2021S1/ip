package duke.task;

/**
 * Represents an object which can be serialised.
 */
public interface Serialisable {
    /**
     * Serialises the object into a string.
     *
     * @return A string representing the serialised object.
     */
    String serialise();
}
