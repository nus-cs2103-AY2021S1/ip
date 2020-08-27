package duke.command;

import duke.exception.*;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Event;

import duke.Ui;
import duke.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a new event task to the user's list of tasks.
 */
public class AddEventCommand extends Command {

    private final String[] parsedCommand;

    /**
     * Creates and initialises a new AddEventCommand object.
     *
     * @param parsedCommand String array that contains the event task information.
     */
    public AddEventCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Creates a new event task, adds it to the list of tasks
     * and saves it into the designated file containing the user's list of tasks.
     *
     * @param tasks List of tasks which the new event task will be added into.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @throws DukeException If the task cannot be created due to invalid inputs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        try {
            String[] eventInfo = retrieveEventInfo();
            String[] timeStamp = eventInfo[1].split(" ");

            LocalDate eventDate = LocalDate.parse(timeStamp[0], dateFormatter);
            LocalTime eventTime = LocalTime.parse(timeStamp[1], timeFormatter);

            Task toAdd = new Event(eventInfo[0], eventDate, eventTime);

            tasks.addTask(toAdd);

            String successReply = "Success! This event task has been added: \n\t" +
                    toAdd.toString() + "\nYou have " + tasks.getListSize() + " tasks in your list now.";
            ui.printReply(successReply);

            storage.saveFile(tasks);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
            String err = "The task date format is incorrect. \n"
                    + "Please input a valid date using the format: 'dd/mm/yyyy hh:mm'. For eg, 10/8/2020 18:00";
            throw new InvalidFunctionException(err);
        }
    }

    /**
     * Retrieves the details of the event task and stores it in an array.
     *
     * @return String array containing the event description and event time stamp.
     * @throws InvalidTaskException If the event information is invalid and is missing arguments.
     */
    public String[] retrieveEventInfo() throws InvalidTaskException {
        String[] eventInfo = new String[2];
        String description = "";
        String time = "";
        if (this.parsedCommand.length == 0) {
            String err = "Your event task has missing arguments and has an incorrect format. "
                    + "The task cannot be created.\n"
                    + "Type '/commands' to view the correct command for task creation!";
            throw new InvalidTaskException(err);
        } else {
            String[] taskInputArray = this.parsedCommand[1].split(" /at ");
            if (!this.parsedCommand[1].contains(" /at ") && !this.parsedCommand[1].endsWith("/at")) {
                String err = "Your event task has an incorrect format. The task cannot be created. \n"
                        + "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (this.parsedCommand[1].trim().equals("/at")) {
                String err = "Your event task is missing a description and time stamp. "
                        + "The task cannot be created. \n"
                        + "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (this.parsedCommand[1].trim().endsWith("/at")) {
                String err = "Your event task is missing a time stamp. The task cannot be created. \n"
                        + "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else if (taskInputArray.length == 1 || taskInputArray[0].isBlank()) {
                String err = "Your event task is missing a description. The task cannot be created. \n"
                        + "Type '/commands' to view the correct command for task creation!";
                throw new InvalidTaskException(err);
            } else {
                description = taskInputArray[0].trim();
                time = taskInputArray[1].trim();
            }
        }
        eventInfo[0] = description;
        eventInfo[1] = time;
        return eventInfo;
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
