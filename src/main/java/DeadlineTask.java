import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private LocalDateTime deadline;

    public DeadlineTask(String description) {
        super(description.split(" /")[0]);
        String[] output = description.split("/");
        String pattern = ("(by?)(\\s)([\\S]+)(\\s)([\\S]+)");
        deadlineDate = LocalDate.parse(output[1].replaceAll(pattern, "$3"));
        String timeTemp = output[1].replaceAll(pattern, "$5");
        deadlineTime = LocalTime.parse(timeTemp.substring(0,2)
                + ":" + timeTemp.substring(2,4));
        deadline = deadlineDate.atTime(deadlineTime);
    }

    @Override
    public String toString()
    {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(),
                description, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy',' Hmm'hrs'")));
    }
}
