package task;

import exception.DukeException;

public class Deadline extends Task {
    private String datetime;

    private static String DEADLINE = "[D]";
    public static String DEADLINE_BREAK = "/by";

    Deadline(String task, boolean completed, String datetime) {
        super(task, completed);
        this.datetime = datetime;
    }

    public static Deadline createDeadline(String[] commands) throws DukeException{
        if (commands.length <= 2) {
            throw new DukeException("Please fill in the description and date or time.");
        }
        return new Deadline(commands[1], false, commands[2]);
    }

    @Override
    public String toString() {
        String byDatetime = String.format("(by: %s)", this.datetime);
        return DEADLINE + toStringSuffix() + " " + byDatetime;
    }
}