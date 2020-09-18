package task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    public Deadline(String description, LocalDateTime dueDate, boolean done) {
        super(description, dueDate, done);
    }

    public Deadline(String description, LocalDateTime dueDate) {
        super(description, dueDate);
    }

    public String writeToFile() {
        return "D|" + super.writeToFile() + "|" + super.getDueDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n\t  (by : " + super.getDateString() + ")";
    }
}
