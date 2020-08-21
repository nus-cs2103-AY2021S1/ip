package task;

import exception.EmptyTaskException;
import exception.MissingDateException;

public class Event extends Task {
    String date;

    private Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public static Event create(String task)
            throws EmptyTaskException, MissingDateException {
        if (task.length() <= 6) throw new EmptyTaskException("event");
        String[] taskInfo = task.substring(6).split(" /at ", 2);
        if (taskInfo.length < 2) throw new MissingDateException();
        return new Event(taskInfo[0], taskInfo[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
