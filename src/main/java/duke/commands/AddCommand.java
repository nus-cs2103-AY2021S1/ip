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
    private static final String DATE_TIME_FORMAT = "02-30-2020 23:59";
    private Type taskType;

    public enum Type {
        TODO, DEADLINE, EVENT
    }

    /**
     * Creates AddCommand object that handles adding of todo, deadline and event.
     *
     * @param taskType differentiates between todo, deadline and event.
     * @param command  is the description entered by the user.
     */
    public AddCommand(Type taskType, String command) {
        super(command);
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        super.init(tasks, storage);
        switch (taskType) {
        case TODO:
            return addTodo();
        case EVENT:
            return addEvent();
        case DEADLINE:
            return addDeadline();
        default:
            assert false : "Unknown type";
        }
        return null;
    }

    private String addTodo() {
        Todo todo;
        try {
            todo = new Todo(super.command);
            super.tasks.addTask(todo);
        } catch (DukeException e) {
            return e.getMessage();
        }
        assert todo != null : "todo is not properly created";
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
            super.tasks.addTask(deadline);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter timing in '/by " + DATE_TIME_FORMAT + "' format";
        }
        assert deadline != null : "Deadline is not properly created";
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
            super.tasks.addTask(event);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter timing in '/at " + DATE_TIME_FORMAT + "' format";
        }
        assert event != null : "Event is not properly created";
        return formatMessage(event);
    }

    private String[] formatTimingInput(String format, String input) throws DukeException {
        if (!input.contains(format)) {
            String message = "Don't forget to add a timing in '"
                    + format + " " + DATE_TIME_FORMAT + "' format";
            throw new DukeException(message);
        }
        return input.trim().split(format);
    }

    private String formatMessage(Task task) {
        return String.format("Got it I've added this task:\n%s"
                        + "\nYou now have %s task(s) in the list.\n",
                task.toString(), tasks.size());
    }
}

