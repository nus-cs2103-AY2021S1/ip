import java.time.format.DateTimeParseException;

/**
 * Parser processes strings.
 */
public class Parser {
    static int whichTask = -1;

    /**
     * All valid command types.
     */
    enum Command {
        BYE, DONE, DELETE, LIST, OTHERS;
    }

    private static boolean isNum(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Processes string and assign it to a command type.
     * @param s command as a String
     * @param size size of tasklist
     * @return a Command enum item
     */
    static Command parse(String s, int size) {
        String[] done = s.split(" ");
        if (s.equals("bye")) {
            return Command.BYE;
        } else if (done.length == 2 && (done[0].equals("done")
                || done[0].equals("delete"))
                    && isNum(done[1]) && Integer.parseInt(done[1]) <= size
                    && Integer.parseInt(done[1]) > 0) {
            whichTask = Integer.parseInt(done[1]) - 1;
            if (done[0].equals("done")) {
                return Command.DONE;
            } else {
                return Command.DELETE;
            }
        } else if (s.equals("list")) {
            return Command.LIST;
        } else {
            return Command.OTHERS;
        }
    }

    /**
     * Processes strings that could be task making commands and returns the
     * task with corresponding type if applicable.
     * @param s command as a String
     * @return a Task item
     */
    static Task createTask(String s) throws DukeException, DateTimeParseException {
        String[] task = s.split(" ");
        Task.Type type;
        if (task.length > 0) {
            if (task[0].equals("todo")) {
                type = Task.Type.TODO;
            } else if (task[0].equals("deadline")) {
                type = Task.Type.DEADLINE;
            } else if (task[0].equals("event")) {
                type = Task.Type.EVENT;
            } else {
                throw new DukeException(DukeException.IGNORE);
            }
        } else {
            throw new DukeException(DukeException.EMPTY);
        }
        switch (type) {
            case DEADLINE:
                if ((task = s.split(" /by ")).length != 2) {
                    throw new DukeException(DukeException.WRONG_DEADLINE);
                }
                return new Deadline(task[0].replaceFirst("deadline ", ""), task[1]);
            case EVENT:
                if ((task = s.split(" /at ")).length != 2) {
                    throw new DukeException(DukeException.WRONG_EVENT);
                }
                return new Event(task[0].replaceFirst("event ", ""), task[1]);
            default:
                if ((s.split(" ")).length < 2) {
                    throw new DukeException(DukeException.EMPTY_TODO);
                }
                return new Todo(s.replaceFirst("todo ", ""));
        }
    }
}
