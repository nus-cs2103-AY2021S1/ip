package duke;

public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "["+ getTaskType() +"]" +"["+ getStatusIcon()+ "]"+ description + " " + "(at: " + date + ")";
    }
}
