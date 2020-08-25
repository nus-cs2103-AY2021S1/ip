public class Deadline extends Task {
    private String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    protected static Deadline createDeadline(String details) throws InvalidDeadlineException {
        String[] info = details.split("/");
        if (info.length == 1) {
            throw new InvalidDeadlineException();
        }
        String desc = info[0];
        String by = info[1].replaceFirst("by ", "");
        return new Deadline(desc, by);
    }

    @Override
    public String toSaveString() {
        return (isDone ? 1 : 0) + "deadline " + description + "/by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
