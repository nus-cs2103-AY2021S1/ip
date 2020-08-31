import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;


    public EventTask(String description) {
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
