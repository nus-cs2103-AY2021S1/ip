package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineStub extends Task {
    private final LocalDate toDoBy;
    private final LocalTime time;

    DeadlineStub(String description, LocalDate toDoBy, LocalTime time) {
        super(description);
        this.toDoBy = toDoBy;
        this.time = time;
    }

    public static Task createTask() {
        return new DeadlineStub("project", LocalDate.parse("2019-02-20"), LocalTime.parse("12:00"));
    }

    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (FINISH by: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(toDoBy);
        if (time == null) {
            return s + ")";
        } else {
            return s + " " + time.toString() + ")";
        }
    }

}
