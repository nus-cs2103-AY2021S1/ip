package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Type type;

    public enum Type {
        TODO, DEADLINE, EVENT
    }

    /**
     * Creates AddCommand object that handles adding of todo, deadline and event.
     *
     * @param type    differentiates between todo, deadline and event.
     * @param command is the description entered by the user.
     */
    public AddCommand(Type type, String command) {
        super(command);
        this.type = type;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        super.init(taskList, storage);
        if (type == Type.TODO) {
            return addTodo();
        } else if (type == Type.DEADLINE) {
            return addDeadline();
        } else {
            return addEvent();
        }
    }

    private String addTodo() {
        Todo todo;
        try {
            todo = new Todo(super.command);
            super.taskList.addTask(todo);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return formatMessage(todo);
    }

    private String addDeadline() {
        Deadline deadline;
        try {
            String detail = formatTimingInput("/by", super.command)[0];
            String timing = formatTimingInput("/by", super.command)[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

            deadline = new Deadline(detail, dateTime);
            super.taskList.addTask(deadline);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter timing in '/by 02-30-2020 23:59' format";
        }
        return formatMessage(deadline);
    }

    private String addEvent() {
        Event event;
        try {
            String detail = formatTimingInput("/at", super.command)[0];
            String timing = formatTimingInput("/at", super.command)[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

            event = new Event(detail, dateTime);
            super.taskList.addTask(event);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter timing in '/at 02-30-2020 23:59' format";
        }
        return formatMessage(event);
    }

    private String[] formatTimingInput(String format, String input) throws DukeException {
        if (!input.contains(format)) {
            String message = "Don't forget to add a timing in '"
                    + format + " 12-12-2020 23:59' format";
            throw new DukeException(message);
        }
        return input.trim().split(format);
    }

    private String formatMessage(Task task) {
        return String.format("Got it I've added this task:\n%s"
                        + "\nNow you have %s tasks in the list.\n",
                task.toString(), taskList.size());
    }
}

