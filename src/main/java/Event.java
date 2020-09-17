import java.util.Date;
import java.util.Optional;

public class Event extends Task {

    private String time;
    private Optional<Date> optTime;
    private DateManager dateManager;

    public Event(String name, String time) {
        super(name);
        this.time = time;
        this.dateManager = new DateManager();
        this.optTime = dateManager.getDate(time);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.printTask());
        if (!optTime.isPresent()) {
            sb.append(" (at: " + this.time + ")");
        } else {
            sb.append(" (by: " + dateManager.getDateAsString(time) + ")");
        }
        return sb.toString();
    }

}