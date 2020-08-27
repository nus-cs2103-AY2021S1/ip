import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String at) throws DukeException {
        super(description);

        // Check for format 
        try {
            String[] dateTimeArr = at.split(" to ");
            String parsableStart = dateTimeArr[0].replace(" ", "T");
            String parsableEnd = dateTimeArr[1].replace(" ", "T");
            LocalDateTime localStart = LocalDateTime.parse(parsableStart);
            LocalDateTime localEnd = LocalDateTime.parse(parsableEnd);
            
            if (localEnd.isAfter(localStart)) {
                start = localStart;
                end = localEnd;
            } else {
                throw new DukeException("invalidEventChronology");
            }
        } catch (Exception e){
            throw new DukeException("invalidEventDateTime");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
        String strStart = start.format(formatter);
        String strEnd = end.format(formatter);
        String startDay = start.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
        String endDay = end.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("en"));
        
        return "[E]" + super.toString() + " (at: " + startDay + ", " + strStart + " to " + endDay + ", " + strEnd + ")";
    }
}