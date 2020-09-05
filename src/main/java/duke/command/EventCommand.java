package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.exception.InvalidTaskException;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful creation of event task.
     * @throws DukeException If the event task cannot be created due to invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

            String[] eventInfo = retrieveEventInfo();
            String[] timeStamp = eventInfo[1].split(" ");

            LocalDate eventDate = LocalDate.parse(timeStamp[0], dateFormatter);
            LocalTime eventTime = LocalTime.parse(timeStamp[1], timeFormatter);

            Task event = new Event(eventInfo[0], eventDate, eventTime);
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
     * Retrieves the details of the event task and stores it in an array.
     *
     * @return String array containing the event description and event time stamp.
     * @throws InvalidTaskException If the event information is invalid or is missing arguments.
     */
    public String[] retrieveEventInfo() throws InvalidTaskException {
        try {
            String[] eventArguments = this.parsedCommand[1].split(" /at ");
            if (!this.parsedCommand[1].contains(" /at ") && !this.parsedCommand[1].endsWith("/at")) {
                String error = "Your event task has an incorrect format. The task cannot be created.";
                throw new InvalidTaskException(error);
            } else if (this.parsedCommand[1].trim().equals("/at")) {
                String error = "Your event task is missing a description and time stamp. "
                        + "The task cannot be created.";
                throw new InvalidTaskException(error);
            } else if (this.parsedCommand[1].trim().endsWith("/at")) {
                String error = "Your event task is missing a time stamp. The task cannot be created.";
                throw new InvalidTaskException(error);
            } else if (eventArguments[0].isBlank()) {
                String error = "Your event task is missing a description. The task cannot be created.";
                throw new InvalidTaskException(error);
            }
            String description = eventArguments[0].trim();
            String time = eventArguments[1].trim();
            return new String[]{description, time};
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Your event task has missing arguments. The task cannot be created.";
            throw new InvalidTaskException(error);
        }
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
