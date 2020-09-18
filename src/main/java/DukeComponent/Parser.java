package DukeComponent;

import Command.Command;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.DoneCommand;
import Command.ListCommand;
import Command.ErrorCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.UndoCommand;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

/**
 * Components.Parser processes strings.
 */
public class Parser {
    private static Command lastCommand;

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
     * Processes string and assigns it to a command type.
     * @param s command as a String
     * @param size size of tasklist
     * @return a Commands.Command enum item
     */
    public static Command parse(String s, int size) {
        String[] done = s.split(" ");
        if (s.equals("bye")) {
            return lastCommand = new ExitCommand();
        } else if (done.length == 2 && (done[0].equals("done")
                || done[0].equals("delete"))
                    && isNum(done[1]) && Integer.parseInt(done[1]) <= size
                    && Integer.parseInt(done[1]) > 0) {
            Integer whichTask = Integer.parseInt(done[1]) - 1;
            assert (whichTask != null);
            if (done[0].equals("done")) {
                return lastCommand = new DoneCommand(whichTask);
            } else {
                return lastCommand = new DeleteCommand(whichTask);
            }
        } else if (s.equals("list")) {
            return lastCommand = new ListCommand();
        } else if (s.equals("undo")) {
            return lastCommand = new UndoCommand(lastCommand);
        } else if (done[0].equals("find") && done.length > 1) {
            String searchText = s.replaceFirst("find ", "");
            return lastCommand = new FindCommand(searchText);
        } else {
            return lastCommand = createTask(s);
        }
    }

    /**
     * Processes strings that could be task making commands and returns the
     * task with corresponding type if applicable.
     * @param s command as a String
     * @return a Tasks.Task item
     */
    public static Command createTask(String s) {
        try {
            String[] task = s.split(" ");
            Task.Type type;
            if (task.length > 1) {
                if (task[0].equals("todo")) {
                    type = Task.Type.TODO;
                } else if (task[0].equals("deadline")) {
                    type = Task.Type.DEADLINE;
                } else if (task[0].equals("event")) {
                    type = Task.Type.EVENT;
                } else {
                    throw new DukeException(DukeException.IGNORE);
                }
            } else if (task.length > 0 && task[0].equals("todo")) {
                throw new DukeException(DukeException.EMPTY);
            } else {
                throw new DukeException(DukeException.IGNORE);
            }
            switch (type) {
            case DEADLINE:
                if ((task = s.split(" /by ")).length != 2) {
                    throw new DukeException(DukeException.WRONG_DEADLINE);
                }
                return new AddCommand(new Deadline(task[0].replaceFirst("deadline ", ""), task[1]));
            case EVENT:
                if ((task = s.split(" /at ")).length != 2) {
                    throw new DukeException(DukeException.WRONG_EVENT);
                }
                return new AddCommand(new Event(task[0].replaceFirst("event ", ""), task[1]));
            default:
                assert (type == Task.Type.TODO);
                if ((s.split(" ")).length < 2) {
                    throw new DukeException(DukeException.EMPTY_TODO);
                }
                return new AddCommand(new Todo(s.replaceFirst("todo ", "")));
            }
        } catch (Exception e) {
            return new ErrorCommand(e);
        }
    }
}
