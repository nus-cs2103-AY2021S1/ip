package duke;

/**
 * Implementors can create a copy of themselves containing all their relevant state.
 */
public interface Copiable {

    public Copiable getCopyOf();
}
