package chatterbox.task;

import chatterbox.Parser;

public class Event extends Task {
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

    public Event(String contents) {
        super.inputString = this.getClass().getSimpleName().toLowerCase() + " " + contents;
        setContents(format(contents));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
