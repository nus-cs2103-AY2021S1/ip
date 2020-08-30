package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EventStub extends Task {
    private final LocalDate schedule;
    private final LocalTime startTime;
    private final LocalTime endTime;

    EventStub(String description, LocalDate schedule, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.schedule = schedule;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Task createTask() {
        return new EventStub("project meeting", LocalDate.parse("2019-02-20"), LocalTime.parse("12:00"),
                LocalTime.parse("14:00"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (APPEAR at: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(schedule)
                + " " + startTime + "-" + endTime + ")";
    }
}
