package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String detail, LocalDateTime deadline) throws DukeException {
        super(detail);
        this.deadline = deadline;
    }

    public Deadline(int doneStatus, String detail, LocalDateTime deadline) {
        super(doneStatus, detail);
        this.deadline = deadline;
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.isDone ? 1 : 0;
        return "D|" + status + "|" + super.description + "|" + this.deadline;
    }


    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString()+ " " + super.description + "(by:" + deadline.format(dateTimeFormatter) + ")";
    }
}
