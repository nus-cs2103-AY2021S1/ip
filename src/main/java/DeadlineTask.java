import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    private LocalDate deadline;
    public DeadlineTask(String name, boolean isCompleted, String deadline) {
        super(name,isCompleted);
        this.deadline = LocalDate.parse(deadline);
    }

    public String getType(){
        return "D";
    }

    public String getTime(){
        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
