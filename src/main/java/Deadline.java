public class Deadline extends Task {
    private String dateTime;

    Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String dateTimeFormat = String.format(" (by: %s)", dateTime);
        return "[D]" + super.toString() + dateTimeFormat;
    }
}