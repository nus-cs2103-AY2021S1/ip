/**
 * enum Commands states constant keywords that are used to determine the command inputted by user
 */
public enum Commands {
    EXIT("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String keyword;

    Commands(String keyword) {
        this.keyword = keyword;
    }

    /**
     * compares keyword with the enum commands
     * @param keyword
     * @return Commands that corresponds to the keyword
     * @throws DukeException if it is unable to match any of the enum commands
     */
    public static Commands findCommand(String keyword) throws DukeException {
        for (Commands c : values()) {
            if (keyword.equals(c.keyword)) {
                return c;
            }
        }
        throw new DukeException("DukeException: ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
