package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskEvent extends Task {

    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;


    public TaskEvent(String description) {
        super(description.split(" /")[0]);
        String[] output = description.split("/");
        String pattern = ("(at?)(\\s)(\\S+)(\\s)(\\d{4})([-])(\\d{4})");
        eventDate = LocalDate.parse(output[1].replaceAll(pattern, "$3"));
        String startTemp = output[1].replaceAll(pattern, "$5");
        String endTemp = output[1].replaceAll(pattern, "$7");
        startTime = LocalTime.parse(startTemp.substring(0,2) + ":"
            + startTemp.substring(2,4));
        endTime = LocalTime.parse(endTemp.substring(0,2) + ":"
                + endTemp.substring(2,4));
    }

    public TaskEvent(String description, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TaskEvent(String description, boolean done, String eventTime) {
        super(description);
        isDone = done;

        String pattern = ("(\\S+)(\\s)(\\d{4})([-])(\\d{4})");
        eventDate = LocalDate.parse(eventTime.replaceAll(pattern, "$1"));
        String startTemp = eventTime.replaceAll(pattern, "$3");
        String endTemp = eventTime.replaceAll(pattern, "$5");
        startTime = LocalTime.parse(startTemp.substring(0,2) + ":"
                + startTemp.substring(2,4));
        endTime = LocalTime.parse(endTemp.substring(0,2) + ":"
                + endTemp.substring(2,4));
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
