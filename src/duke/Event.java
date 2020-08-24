package duke;

public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String description, Boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + this.getStatusIcon() + " " + this.description + " (at:" + this.date + ")";
    }

    @Override
    public Event markAsDone() {
        //int index = taskNum - 1;
        Event newTask = new Event(this.getDescription(), true, this.date);
        return newTask;
    }

    public String getDate() {
        return this.date;
    }
}
