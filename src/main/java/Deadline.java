public class Deadline extends Task {
    String datetimeDue;

    public Deadline(String content, String datetimeDue, boolean isComplete) throws DukeException {
        super(content, isComplete);
        if (content.replace(" ", "").equals("")) {
            throw new DukeException("Contents of a task cannot be empty.");
        }
        this.datetimeDue = datetimeDue;
    }

    public Deadline(String content, String datetimeDue) throws DukeException {
        super(content);
        if (content.replace(" ", "").equals("")) {
            throw new DukeException("Contents of a task cannot be empty.");
        }
        this.datetimeDue = datetimeDue;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetimeDue);
    }

    @Override
    public String toSaveString() {
        return String.format("D/%s/%s", super.toSaveString(), datetimeDue);
    }
}
