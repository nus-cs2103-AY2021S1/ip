package task;

import exceptions.InvalidDescriptionException;
import exceptions.MissingTimingException;

public class Deadline extends Task {
    private String detail;
    private String deadline;
    public Deadline(String s) throws MissingTimingException, InvalidDescriptionException {
        super(s);
        if (s.isBlank()) {
            throw new InvalidDescriptionException("Please add a description for your deadline!");
        } else if (!s.contains("/")) {
            throw new MissingTimingException("Don't forget to add a timing for your deadline!");
        }
        this.detail = super.description.substring(0, super.description.indexOf("/"));
        this.deadline = super.description.substring(super.description.indexOf('/') + 1);
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.isDone ? 1 : 0;
        return "D|" + status + "|" + detail + "|" + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()+ detail + "(by: " + deadline + ")";
    }
}
