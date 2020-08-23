package Task;

public class Event extends Task {
    public Event(int position, String taskDescription) {
        super(position, taskDescription);
    }

    public void setTime(String givenDate) {
        date = givenDate;
    }

    @Override
    public String toString() {
        String base = "[E] ";
        if (taskCompleted) {
            base = base + "[✓]";
        } else {
            base = base + "[✗]";
        }
        base = base + taskDescription + "(at:" + date + ")";
        return base;
    }
}
