package duke;

public enum TaskType {
    TODO, DEADLINE, EVENT, LIST, DONE, DELETE, BYE, FIND;

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
        case "find":
            return FIND;
        default:
            throw new IllegalArgumentException(str + " is not a valid TaskType.");
        }
    }
}
