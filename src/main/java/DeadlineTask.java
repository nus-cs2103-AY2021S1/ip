public class DeadlineTask extends DukeTask {
    private final String datetime;

    public DeadlineTask(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",datetime);
    }
}