package task;

import exceptions.InvalidDescriptionException;
import exceptions.MissingTimingException;

public class Deadline extends Task {
    public Deadline(String s) throws MissingTimingException, InvalidDescriptionException {
        super(s);
        if (s.isBlank()) {
            throw new InvalidDescriptionException("Please add a description for your deadline!");
        } else if (!s.contains("/")) {
            throw new MissingTimingException("Don't forget to add a timing for your deadline!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().replace("/by", "(by:") + ")";
    }
}
