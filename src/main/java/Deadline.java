import java.util.Date;
import java.util.Optional;

public class Deadline extends Task {

    private String deadline;
    private Optional<Date> optDate;
    private DateManager dateManager;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
        this.dateManager = new DateManager();
        this.optDate = dateManager.getDate(deadline);
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.printTask());
        if (!optDate.isPresent()) {
            sb.append(" (by: " + this.deadline + ")");
        } else {
            sb.append(" (by: " + dateManager.getDateAsString(deadline) + ")");
        }

        return sb.toString();
    }

}