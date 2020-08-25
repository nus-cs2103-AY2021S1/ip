package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime timing;

    public Event(String detail, LocalDateTime timing) throws DukeException {
        super(detail);
        this.timing = timing;
    }

    public Event(int doneStatus, String detail, LocalDateTime timing) {
        super(doneStatus, detail);
        this.timing = timing;
    }


    @Override
    public String formatTaskForDatabase() {
        int status = super.isDone ? 1 : 0;
        return "E|" + status + "|" + super.description + "|" + timing;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " " + super.description + "(at:" + timing.format(dateTimeFormatter) + ")";
    }
}
