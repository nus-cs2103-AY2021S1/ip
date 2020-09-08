/**
 * An subclass of DukeException.
 * Shows error for todo tasks.
 */
public class UndoException extends DukeException {
    public UndoException() { super("There is no previous action."); }
    public UndoException(String msg) {
        super(msg);
    }
}
