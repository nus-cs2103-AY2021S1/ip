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

    private static boolean isDelete(String s) {
        return s.length() >= 6 && s.substring(0, 6).toLowerCase().equals("delete");
    }

    private static boolean isFind(String s) {
        return s.length() >= 4 && s.substring(0, 4).toLowerCase().equals("find");
    }
    
    public int parseDone(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    public int parseDelete(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    public String parseFind(String s) {
        return s.split(" ")[1];
    }
    
    public Task parseInputTask(String s) throws IllegalArgumentException, IndexOutOfBoundsException {
        String[] processed;
        Task task;
        try {
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
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unrecognizable task command");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Task formatted wrongly.");
        }
        return task;
    }

    public boolean isTask(String s) {
        try {
            parseInputTask(s);
            return true;
        } catch (Exception e) {
            return false;
        }
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
        } else if (isFind(input)) {
            return PARSER_RESULT.FIND;
        } else if (isTask(input)) {
            return PARSER_RESULT.ADD;
        } else {
            throw new IllegalArgumentException("Unrecognizable command.");
        }
    }

    public static Task parseSavedTask(String s) {
        String[] arg = s.split(" @@ ");
        Task task;
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
        default:
            throw new IllegalArgumentException("Save file corrupted");
        }
        if (arg[1].equals("1")) {
            task.markDone();
        }
        return task;
    }
}
