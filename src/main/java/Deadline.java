import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    public String getDate(){
        return this.by;
    }

    @Override
    public String getOriginal() {
        return "deadline " + getTask() + " /by " + getDate();
    }

    private String getDeadlineDate() {
        String[] dateList = this.by.split(" ",2);
        LocalDate deadlineDate = LocalDate.parse(dateList[0]);
        DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");

        if(dateList.length > 1){
            String deadlineTime = dateList[1];
            return deadlineDate.format(FormatDate) + " " + deadlineTime;
        }
        else{
            return deadlineDate.format(FormatDate);
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineDate() + ")";
    }
}
