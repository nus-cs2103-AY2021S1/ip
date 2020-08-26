import java.awt.*;

public class Deadline extends Task {
    private TaskDate dueTime;

    Deadline(String name, String dueTime) {
        super(name);
        this.dueTime = DateParser.parseDate(dueTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueTime);
    }
}