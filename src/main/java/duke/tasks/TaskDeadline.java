package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TaskDeadline extends Task {

    private LocalDateTime deadline;

    public TaskDeadline(String description) {
        super(description.split(" /")[0]);
        String[] output = description.split("/");
        String pattern = ("(by?)(\\s)([\\S]+)(\\s)([\\S]+)");
        LocalDate deadlineDate = LocalDate.parse(output[1].replaceAll(pattern, "$3"));
        String timeTemp = output[1].replaceAll(pattern, "$5");
        LocalTime deadlineTime = LocalTime.parse(timeTemp.substring(0,2)
                + ":" + timeTemp.substring(2,4));
        deadline = deadlineDate.atTime(deadlineTime);
    }

    public TaskDeadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public TaskDeadline(String description, boolean done, String eventDeadline) {
        super(description);
        isDone = done;

        String pattern = ("(\\S+)(\\s)(\\S+)");
        LocalDate deadlineDate = LocalDate.parse(eventDeadline.replaceAll(pattern, "$1"));
        String timeTemp = eventDeadline.replaceAll(pattern, "$3");
        LocalTime deadlineTime = LocalTime.parse(timeTemp.substring(0,2)
                + ":" + timeTemp.substring(2,4));
        deadline = deadlineDate.atTime(deadlineTime);

    }

    @Override
    public String[] getSaveData() {
        return new String[] {"D", isDone ? "1" : "0", description,
                String.format("%s", deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm"))) };
    }

    @Override
    public String toString()
    {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(),
                description, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy',' Hmm'hrs'")));
    }
}
