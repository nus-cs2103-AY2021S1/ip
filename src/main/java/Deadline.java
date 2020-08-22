import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadline;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");

    public Deadline(String work, LocalDateTime date){
        super(work);
        this.deadline = date;
    }


    public String toString(){
        return "[D]" + super.toString() + "(by: " + deadline.format(format) + ")";
    }
}
