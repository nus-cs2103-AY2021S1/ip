package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;


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
     * @param type differentiates between todo, deadline and event.
     * @param command is the description entered by the user.
     */
    public AddCommand(Type type, String command) {
        super(command);
        this.type = type;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.init(taskList, ui, storage);
        if (type == Type.TODO) {
            addTodo();
        }
        if (type == Type.DEADLINE) {
            addDeadline();
        }
        if (type == Type.EVENT) {
            addEvent();
        }
    }

    private void addTodo() {
        try {
            Todo todo = new Todo(super.command);
            super.taskList.addTask(todo);
            super.ui.printAddTaskMessage(super.taskList, todo);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addDeadline() {
        try {
            String detail = formatTimingInput("/by", super.command)[0];
            String timing = formatTimingInput("/by", super.command)[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

            Deadline deadline = new Deadline(detail, dateTime);
            super.taskList.addTask(deadline);

            super.ui.printAddTaskMessage(super.taskList, deadline);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter timing in '/by 02-30-2020 23:59' format");
        }
    }

    private void addEvent() {
        try {
            String detail = formatTimingInput("/at", super.command)[0];
            String timing = formatTimingInput("/at", super.command)[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(timing, formatter);

            Event event = new Event(detail, dateTime);
            super.taskList.addTask(event);

            super.ui.printAddTaskMessage(super.taskList, event);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter timing in '/at 02-30-2020 23:59' format");
        }
    }

    /**
     * Returns a string array where the first item is the description of the task
     * and the next item is the timing of the task.
     *
     * @param format is to differentiate between a deadline and an event
     * @param input is the entire string to be split
     * @return a string array with first item description second item time
     * @throws DukeException if the user does not include the timing in his/her input
     */
    public String[] formatTimingInput(String format, String input) throws DukeException {
        if (!input.contains(format)) {
            String message = "Don't forget to add a timing in '"
                    + format + " 12-12-2020 23:59' format";
            throw new DukeException(message);
        }
        return input.trim().split(format);
    }
}

