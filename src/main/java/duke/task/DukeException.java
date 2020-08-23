package duke.task;

public class DukeException extends Exception {
    public DukeException(String message) {
        super("\u2639 oh no! " + message + "\n\tPlease type help for a list of commands");
    }
    
    @Override
    public String toString() {
        return getMessage();
    }
}
