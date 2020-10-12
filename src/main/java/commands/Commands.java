package commands;

import exceptions.DukeException;

/**
 * enum Commands.Commands states constant keywords that are used to determine the command inputted by user
 */
public enum Commands {
    EXIT("bye", "B"),
    LIST("list", "L"),
    DONE("done", "D"),
    TODO("todo", "T"),
    DEADLINE("deadline", "DL"),
    EVENT("event", "E"),
    DELETE("delete", "DEL"),
    RESCHEDULE("reschedule", "R"),
    FIND("find", "F");

    private final String keyword;
    private final String abbr;

    /**
     * constructor for a Commands Object
     * @param keyword string related to the command
     * @param abbr abbreviation of the command string
     */
    Commands(String keyword, String abbr) {
        this.keyword = keyword;
        this.abbr = abbr;
    }

    /**
     * compares keyword with the enum commands
     *
     * @param keyword
     * @return Commands.Commands that corresponds to the keyword
     * @throws DukeException if it is unable to match any of the enum commands
     */
    public static Commands findCommand(String keyword) throws DukeException {
        for (Commands c : values()) {
            if (keyword.equals(c.keyword) || keyword.equals(c.abbr)) {
                return c;
            }
        }
        throw new DukeException("OOPS!!! I'm sorry, but Luigi is Italian and doesn't understand");
    }
}
