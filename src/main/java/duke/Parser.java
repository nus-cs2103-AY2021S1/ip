package duke;

/**
 * Parses user inputs or saved strings.
 */
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

    /**
     * Returns the index of the task to be marked done.
     *
     * @param s The input.
     * @return An integer index.
     */
    public int parseDone(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    /**
     * Returns the index of the task to be deleted.
     *
     * @param s The input.
     * @return An integer index.
     */
    public int parseDelete(String s) {
        return Integer.parseInt(s.split(" ")[1]) - 1;
    }

    /**
     * Returns the keyword to be searched.
     *
     * @param s The input.
     * @return A string keyword.
     */
    public String parseFind(String s) {
        return s.split(" ")[1];
    }

    /**
     * Returns a task represented by the input.
     *
     * @param s The input.
     * @return A task.
     * @throws IllegalArgumentException Unrecognizable task command.
     * @throws IndexOutOfBoundsException Task formatted wrongly.
     */
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
            throw new IllegalArgumentException("Unrecognizable task command.");
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Task formatted wrongly.");
        }
        return task;
    }

    private boolean isTask(String s) {
        try {
            parseInputTask(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Determines the type of command.
     *
     * @param input The input.
     * @return A PARSER_RESULT value.
     * @throws IllegalArgumentException Unrecognizable command.
     */
    public PARSER_RESULT parseInput(String input) throws IllegalArgumentException {
        input = input.trim();
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

    /**
     * Parse a saved string to a task.
     *
     * @param s The saved string.
     * @return The saved task.
     */
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
