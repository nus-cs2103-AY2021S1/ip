package chatterbox.task;

import chatterbox.Parser;

/**
 * Task with a deadline (must be done by a certain date).
 */
public class Deadline extends Task {
    private String format(String s) {
        if (s.contains("/")) {
            String[] split = s.split("/", 2);
            String dateTime = split[1].substring(split[1].indexOf(' ') + 1);
            deadline = Parser.parseDateTime(dateTime);
            if (deadline != null) dateTime = deadline.format(DF);
            return split[0] + "(" + split[1].split(" ")[0] + ": " + dateTime + ")";
        } else {
            return s;
        }
    }

    /**
     * Stores the original user input including the command word, then formats and sets task content.
     *
     * @param contents  User input without the command word.
     */
    public Deadline(String contents) {
        inputString = this.getClass().getSimpleName().toLowerCase() + " " + contents;
        setContents(format(contents));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
