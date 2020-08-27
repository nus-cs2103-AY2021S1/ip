package duke.tasks;

import java.time.LocalDate;

public class Event extends TaskDDL {

    public Event(String task, LocalDate ddl) {
        super(task, ddl);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", getDateTime());
    }

    @Override
    public String fileString() {
        return String.format("E|%s|%s", super.fileString(), ddl.toString());
    }
}
