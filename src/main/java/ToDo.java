import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return done
                ? "[T][✓] " + text
                : "[T][✗] " + text;
    }

    public boolean compareTime(LocalDateTime now, long hours) {
        return false;
    }
}