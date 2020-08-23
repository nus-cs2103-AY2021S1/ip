import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy HHmm'H'");
    protected LocalDateTime deadline;

    private String contents;
    private boolean done = false;

    protected void setContents(String contents) {
        this.contents = contents;
    }

    public void markDone() {
        done = true;
    }

    @Override
    public String toString() {
        return (done ? "[✓]" : "[✗]") + " " + contents;
    }
}
