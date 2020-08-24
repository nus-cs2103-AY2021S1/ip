package task;

import exceptions.InvalidDescriptionException;
import exceptions.MissingTimingException;
import task.Task;

public class Event extends Task {
    private String detail;
    private String timing;

    public Event(String s) throws MissingTimingException, InvalidDescriptionException {
        super(s);
        if (s.isBlank()) {
            throw new InvalidDescriptionException("Please add a description for your event!");
        } else if (!s.contains("/")) {
            throw new MissingTimingException("Don't forget to add a timing for your event!");
        }
        this.detail = super.description.substring(0, super.description.indexOf("/"));
        this.timing = super.description.substring(super.description.indexOf('/') + 1);
    }

    public Event(int doneStatus, String detail, String timing) {
        super(detail);
        if (doneStatus == 1) super.isDone = true;
        this.detail = detail;
        this.timing = timing;
    }


    @Override
    public String formatTaskForDatabase() {
        int status = super.isDone ? 1 : 0;
        return "E|" + status + "|" + detail + "|" + timing;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + detail + "(at: " + timing + ")";
    }
}
