package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidFileException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * EventCommand class to execute command that add an event into
 * the TaskList.
 */
public class EventCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";
    public static final String MESSAGE_PARSE_ERROR = "Invalid date and time format.\n"
            + "Please enter date and time in the format: yyyy-MM-dd HH:mm";

    public EventCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to add an event into the task list.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input or wrong format for date and time.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 5) {
            throw new InvalidInputException(
                    "☹ OOPS!!! The description of a event cannot be empty.\n");
        }
        try {
            String[] split = super.input.substring(6).split("/at ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(split[1], formatter);
            Task event = new Event(split[0], date);
            tasks.addTask(event);
            storage.save(tasks);
            return ui.printMessage(MESSAGE_SUCCESS + event.toString() + "\nNow you have "
                    + tasks.taskListSize() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_PARSE_ERROR);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
