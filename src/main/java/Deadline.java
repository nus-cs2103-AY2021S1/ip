import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = Parser.getLocalDateTimeBy(by);
        } catch (Exception e){
            throw new DukeException("invalidDeadlineDateTime");
        }
    }

    @Override
    public String toString() {
        String datetime = Parser.getDateTime(by);
        String day = Parser.getDay(by);
        return "[D]" + super.toString() + " (by: " + day + ", " + datetime + ")";
    }
}