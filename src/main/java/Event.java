import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String done, LocalDateTime at) {
        super(description, done);
        this.at = at;
    }

    public String[] getStringArr() {
        String[] stringArr = this.at.toString().split("T");
        String[] timeArr = stringArr[1].split(":");
        String t = String.format("%s %s%s", stringArr[0], timeArr[0], timeArr[1]);
        String[] arr = {"E", super.done, super.description, t};
        return arr;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Duke.parseDateTime(this.at) + ")";
    }
}