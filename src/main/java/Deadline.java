public class Deadline extends Task {
    String datetimeDue;

    public Deadline(String content, String datetimeDue) throws DukeException {
        super(content);
        this.datetimeDue = datetimeDue;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetimeDue);
    }
}
