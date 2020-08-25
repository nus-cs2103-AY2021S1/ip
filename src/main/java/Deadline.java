public class Deadline extends Task {
    private String dateTime;

    Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    Deadline(String name, Boolean isDone, String dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    public String encode() {
        return isDone
                ? String.format("D | 1 | %s | %s", name, dateTime)
                : String.format("D | 0 | %s | %s", name, dateTime);
    }
    
    @Override
    public String toString() {
        String dateTimeFormat = String.format(" (by: %s)", dateTime);
        return "[D]" + super.toString() + dateTimeFormat;
    }
}