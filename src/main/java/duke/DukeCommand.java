package duke;

public enum DukeCommand {
    LIST, DELETE, TODO, DONE, DEADLINE, EVENT, BYE;
    public static DukeCommand getCommand(String str) {
        switch(str) {
            case "list":
                return LIST;
            case "delete":
                return DELETE;
            case "todo":
                return TODO;
            case "done":
                return DONE;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "bye":
                return BYE;
            default:
                return null;
        }
    }
}
