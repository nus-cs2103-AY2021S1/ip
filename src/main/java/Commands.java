public enum Commands {
    EXIT("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find");

    private final String keyword;

    Commands(String keyword) {
        this.keyword = keyword;
    }

    public static Commands findCommand(String keyword) throws DukeException {
        for (Commands c : values()) {
            if (keyword.equals(c.keyword)) {
                return c;
            }
        }
        throw new DukeException("DukeException: ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
