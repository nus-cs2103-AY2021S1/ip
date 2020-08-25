import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate time;

    Event(String content, LocalDate time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[E][%s] %s (at: %s)", mark, content, time);
    }
}