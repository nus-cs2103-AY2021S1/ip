import java.time.LocalDate;

public class EventTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public EventTask(String summary, LocalDate startDate, LocalDate endDate) {
        super(summary);
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskType = "event";
    }

    public String getTime() {
        return startDate.getDayOfMonth() + " "
                + startDate.getMonth().toString() + " "
                + startDate.getYear() + " to "
                + endDate.getDayOfMonth() + " "
                + endDate.getMonth().toString() + " "
                + endDate.getYear();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getTime() + ")";
    }
}
