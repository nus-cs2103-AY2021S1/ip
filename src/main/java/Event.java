package main.java;

public class Event extends Task {
    String day;

    public Event(String taskName, String day) throws DukeInvalidDayException, DukeInvalidTaskException {
        super(taskName);
        if(!day.equals(null) && !day.equals(" ")) {
            this.day = day;
        } else {
            throw new DukeInvalidDayException();
        }
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[E]" + "[" + finished + "] " + taskName + " (at: " + day + ")";
        return toReturn;
    }
}
