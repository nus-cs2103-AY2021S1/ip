package duke;

/**
 * Implementors can create a copy of themselves containing all their relevant state.
 */
public interface Copiable {

    /**
     * Creates a copy of itself.
     *
     * @return an independent copy of the calling Copiable
     */
    public Copiable getCopyOf();
}
