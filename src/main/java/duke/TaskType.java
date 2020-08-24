package duke;

public enum TaskType {
    TODO, DEADLINE, EVENT, LIST, DONE, DELETE, BYE;

    public static TaskType of(String str) {
        str = str.toLowerCase();
        switch(str) {
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "list":
            return LIST;
        case "done":
            return DONE;
        case "delete":
            return DELETE;
        case "bye":
            return BYE;
        default:
            throw new IllegalArgumentException(str + " is not a valid TaskType.");
        }
    }
}
