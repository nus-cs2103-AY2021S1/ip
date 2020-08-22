package exception;

public class EmptyEventException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The description or time of an event cannot be empty. Format: " +
                "event [description] /at [time]";
    }
}
