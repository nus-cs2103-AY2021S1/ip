import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String deadline;
    protected LocalDateTime dateTime;

    public Deadline(String taskname, boolean status, String deadline) {
        super(taskname, status);
        this.deadline = deadline;
        this.dateTime = null;
    }
    
    protected void updateDateTime() throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
            if (this.deadline.length() == 15) {
                this.dateTime = LocalDateTime.parse(this.deadline, formatter);
            }
        } catch (Exception ex) {
            throw new DukeException("The format for a deadline with specific date" +
                    "and time should be yyyy/MM/dd HHmm.");
        }
    } 

    @Override
    public String toString() {
        if (this.dateTime == null) {
            return "[D]" + super.toString() + " (by: " + this.deadline + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        }
        
    }
}
