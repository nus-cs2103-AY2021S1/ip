package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a new event task to the user's list of tasks.
 */
public class EventCommand extends Command {

    /** Parsed commands containing details of the event task. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new EventCommand object.
     *
     * @param parsedCommand String array that contains the event task information.
     */
    public EventCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Creates a new event task, adds it to the user's list of tasks
     * and saves it into the designated file that stores the user's tasks.
     *
     * @param tasks List of tasks which the new event task will be added into.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful creation of the event task.
     * @throws DukeException If the event task could not be saved or created due to invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

            String description = parsedCommand[0];
            assert !description.isBlank() : "event description cannot be empty";
            String[] timeStamp = parsedCommand[1].split(" ");

            LocalDate eventDate = LocalDate.parse(timeStamp[0], dateFormatter);
            LocalTime eventTime = LocalTime.parse(timeStamp[1], timeFormatter);

            Task event = new Event(description, eventDate, eventTime);
            tasks.addTask(event);
            storage.saveToFile(tasks);
            return ui.showNewTask(event, tasks.getListSize());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
            String error = "The task date format is incorrect. \n"
                    + "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00";
            throw new InvalidFunctionException(error);
        }
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
