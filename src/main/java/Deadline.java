import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate time;

    Deadline(String content, LocalDate time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        String mark = getCompleteMark();
        String content = getContent();
        return String.format("[D][%s] %s (by: %s)", mark, content, time);
    }
}