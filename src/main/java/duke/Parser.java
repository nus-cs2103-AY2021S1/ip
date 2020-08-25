package duke;

public class Parser {
    private static boolean isBye(String s) {
        return s.toLowerCase().equals("bye");
    }

    private static boolean isList(String s) {
        return s.toLowerCase().equals("list");
    }

    private static boolean isDone(String s) {
        return s.length() >= 4 && s.substring(0, 4).toLowerCase().equals("done");
    }

    private static boolean isDelete(String s){
        return s.length() >= 6 && s.substring(0, 6).toLowerCase().equals("delete");
    }
    
    public int parseDone(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    public int parseDelete(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }
    
    public Task parseInputTask(String s) throws IllegalArgumentException {
        String[] processed;
        Task task;
        switch (s.split(" ")[0]) {
        case "todo":
            task = new ToDoTask(s.substring(5));
            break;
        case "event":
            processed = s.substring(6).split(" /at ");
            task = new EventTask(processed[0], processed[1]);
            break;
        case "deadline":
            processed = s.substring(9).split(" /by ");
            task = new DeadlineTask(processed[0], processed[1]);
            break;
        default:
            throw new IllegalArgumentException("Formatting error, please try again.");
        }
        return task;
    }

    public PARSER_RESULT parseInput(String input) throws IllegalArgumentException {
        if (isBye(input)) {
            return PARSER_RESULT.BYE;
        } else if (isDelete(input)) {
            return PARSER_RESULT.DELETE;
        } else if (isDone(input)) {
            return PARSER_RESULT.DONE;
        } else if (isList(input)) {
            return PARSER_RESULT.LIST;
        } else {
            try {
                parseInputTask(input);
                return PARSER_RESULT.ADD;
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
    }

    public static Task parseSavedTask(String s) {
        String[] arg = s.split(" @@ ");
        Task task = null;
        switch (arg[0]) {
        case "T":
            task = new ToDoTask(arg[2]);
            break;
        case "D":
            task = new DeadlineTask(arg[2], arg[3]);
            break;
        case "E":
            task = new EventTask(arg[2], arg[3]);
            break;
        }
        if (arg[1].equals("1")) {
            task.markDone();
        }
        return task;
    }
}
