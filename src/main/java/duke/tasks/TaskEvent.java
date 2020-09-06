package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskEvent extends Task {

    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public TaskEvent(String description, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"E", isDone ? "1" : "0", description,
                String.format("%s %s-%s", eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                        startTime.format(DateTimeFormatter.ofPattern("Hmm")),
                        endTime.format(DateTimeFormatter.ofPattern("Hmm")))};
    }

    @Override
    public String toString()
    {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description,
                String.format(
                        eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + ", " + startTime.format(DateTimeFormatter.ofPattern("Hmm'hrs'"))
                        + "-" + endTime.format(DateTimeFormatter.ofPattern("Hmm'hrs'"))
                        ));
    }

}
