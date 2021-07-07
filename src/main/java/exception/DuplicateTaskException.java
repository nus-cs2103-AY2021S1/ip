package exception;

public class DuplicateTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The task has already been added before!";
    }
}
