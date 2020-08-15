package task;

import exception.DukeException;

public class Event extends Task{
    private String time;

    private static String EVENT= "[D]";
    public static String EVENT_BREAK = "/at";

    Event(String task, boolean completed, String time) {
        super(task, completed);
        this.time = time;
    }

    public static Event createEvent(String[] commands) throws DukeException {
        if (commands.length <= 2) {
            throw new DukeException("Please fill in the description and time.");
        }
        return new Event(commands[1], false, commands[2]);
    }

    @Override
    public String toString() {
        String atTime = String.format("(at: %s)", this.time);
        return EVENT + toStringSuffix() + " " + atTime;
    }
}
