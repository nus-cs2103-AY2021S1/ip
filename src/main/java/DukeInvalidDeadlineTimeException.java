public class DukeInvalidDeadlineTimeException extends DukeInvalidTaskTimeException {
    @Override
    public String toString() {
        return "ERROR: Usage: <deadline> <description> /by <time>";
    }
}
