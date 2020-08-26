import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone){
        super(description, isDone);
        this.at = at;
    }

    public String getTime(){
        return this.at;
    }

    @Override
    public String getOriginal() {
        return "event " + getTask() + " /at " + getTime();
    }

    private String getEventDate() {
        String[] dateList = this.at.split(" ",2);
        LocalDate eventDate = LocalDate.parse(dateList[0]);
        DateTimeFormatter FormatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");

        if(dateList.length > 1){
            String eventTime = dateList[1];
            return eventDate.format(FormatDate) + " " + eventTime;
        }

        else{
            return eventDate.format(FormatDate);
        }


    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getEventDate() + ")";
    }
}
