public class DukeInvalidTaskException extends DukeException {

    public DukeInvalidTaskException() {
        super("That task does not exist in the list!");
    }
}
