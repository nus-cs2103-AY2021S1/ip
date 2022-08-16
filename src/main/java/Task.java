import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Class to handle fields and functions universal to all task types
 */
public class Task {
    protected DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yy-M-d H:mm");
    protected DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return isDone ? "O" : "X";
    }

    /**
     * Mark current task as done
     */
    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
