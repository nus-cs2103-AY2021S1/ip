public enum Command {
    SAVE("save"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String repr;

    Command(String repr) {
        this.repr = repr;
    }

    static Command toCommand(String repr) throws DukeException {
        switch (repr) {
            case "save":
                return SAVE;
            case "list":
                return LIST;
            case "done":
                return DONE;
            case "delete":
                return DELETE;
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            default:
                throw new DukeException("Aww! The first word of your input is wrong!");
        }
    }
}
