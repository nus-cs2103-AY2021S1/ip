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

    private static boolean isValidIndex(String s, int size) {
        try {
            int num = Integer.parseInt(s);
            if (num >= 1 && num <= size) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Processes string and assigns it to a command type.
     * @param s command as a String
     * @param size size of tasklist
     * @return a Commands.Command enum item
     */
    public static Command parse(String s, int size) {
        String[] split = s.split(" ");
        try {
            if (s.length() <= 0) {
                throw new DukeException(DukeException.EMPTY);
            } else if (s.equals("bye")) {
                return lastCommand = new ExitCommand();
            } else if (s.equals("list")) {
                return lastCommand = new ListCommand();
            } else if (s.equals("undo")) {
                return lastCommand = new UndoCommand(lastCommand);
            } else {
                return lastCommand = parseMultiWordCommand(s, size);
            }
        } catch (Exception e) {
            return new ErrorCommand(e);
        }
    }

    private static Command parseMultiWordCommand(String s, int size) throws DukeException {
        String[] split = s.split(" ");
        assert (split.length > 0);
        if (split[0].equals("done") || split[0].equals("delete")) {
            if (split.length != 2 || !isValidIndex(split[1], size)) {
                throw new DukeException(DukeException.WRONG_DONE_OR_DELETE);
            }
            Integer whichTask = Integer.parseInt(split[1]) - 1;
            assert (whichTask != null);
            return split[0].equals("done")
                    ? new DoneCommand(whichTask)
                    : new DeleteCommand(whichTask);
        } else if (split[0].equals("find")) {
            if (split.length <= 1) {
                throw new DukeException(DukeException.WRONG_FIND);
            }
            String searchText = s.replaceFirst("find ", "");
            return new FindCommand(searchText);
        } else {
            return createTask(s);
        }
    }

    private static Task.Type getTaskType(String s) throws DukeException {
        String[] task = s.split(" ");
        Task.Type type;
        assert (task.length > 0);
        if (task[0].equals("todo")) {
            type = Task.Type.TODO;
        } else if (task[0].equals("deadline")) {
            type = Task.Type.DEADLINE;
        } else if (task[0].equals("event")) {
            type = Task.Type.EVENT;
        } else {
            throw new DukeException(DukeException.IGNORE);
        }
        return type;
    }

    /**
     * Processes strings that could be task making commands and returns the
     * task with corresponding type if applicable.
     * @param s command as a String
     * @return a Tasks.Task item
     */
    private static Command createTask(String s) throws DukeException {
        Task.Type type;
        String[] task;
        switch (type = getTaskType(s)) {
        case DEADLINE:
            if ((task = s.split(" /by ")).length != 2
                || task[0].split(" ").length <= 1) {
                throw new DukeException(DukeException.WRONG_DEADLINE);
            }
            return new AddCommand(new Deadline(task[0].replaceFirst("deadline ", ""), task[1]));
        case EVENT:
            if ((task = s.split(" /at ")).length != 2
                || task[0].split(" ").length <= 1) {
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
    }
}
