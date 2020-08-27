import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            LocalDateTime localStart = Parser.getLocalDateTimeStart(at);
            LocalDateTime localEnd = Parser.getLocalDateTimeEnd(at);
           
            if (localEnd.isAfter(localStart)) {
                this.start = localStart;
                this.end = localEnd;
            } else {
                throw new DukeException("invalidEventChronology");
            }
        } catch (Exception e){
            throw new DukeException("invalidEventDateTime");
        }
    }

    @Override
    public String toString() {
        String strStart = Parser.getStringStart(start);
        String strEnd = Parser.getStringEnd(start);
        String startDay = Parser.getStartDay(start);
        String endDay = Parser.getEndDay(start);
        return "[E]" + super.toString() + " (at: " + startDay + ", " + strStart + " to " + endDay + ", " + strEnd + ")";
    }
}