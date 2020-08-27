package duke.tasks;

import java.time.LocalDate;

public class Deadline extends TaskDDL {

    public Deadline(String task, LocalDate ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDateTime());
    }

    @Override
    public String fileString() {
        return String.format("D|%s|%s", super.fileString(), ddl.toString());
    }
}
