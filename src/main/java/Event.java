import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate eventDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public Event(String description, LocalDate date, LocalTime start, LocalTime end) {
        super(description);
        this.eventDate = date;
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " +
                startTime.format(DateTimeFormatter.ofPattern("hh.mma")) + " to " +
                endTime.format(DateTimeFormatter.ofPattern("hh.mma")) + ")";
    }
}


//System.out.println(dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

//    System.out.println(time.format(DateTimeFormatter.ofPattern("hh-mm a")));