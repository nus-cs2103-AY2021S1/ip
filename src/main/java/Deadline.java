import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String done, LocalDateTime by) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String[] getStringArr() {
        String[] stringArr = this.by.toString().split("T");
        String[] timeArr = stringArr[1].split(":");
        String t = String.format("%s %s%s", stringArr[0], timeArr[0], timeArr[1]);
        String[] arr = {"D", super.done, super.description, t};
        return arr;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.parseDateTime(this.by) + ")";
    }
}