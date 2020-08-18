public class DukeInvalidTaskTimeException extends DukeTaskException {
    @Override
    public String toString() {
        return "ERROR: Please specify a date/time for this task!";
    }
}
