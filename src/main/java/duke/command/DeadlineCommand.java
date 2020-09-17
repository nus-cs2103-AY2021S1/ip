package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidFunctionException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a new deadline task to the user's list of tasks.
 */
public class DeadlineCommand extends Command {

    /** Parsed commands containing details of the deadline task. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new DeadlineCommand object.
     *
     * @param parsedCommand String array that contains the deadline task information.
     */
    public DeadlineCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Creates a new deadline task, adds it to the user's list of tasks
     * and saves it into the designated file that stores the user's tasks.
     *
     * @param tasks List of tasks which the new deadline task will be added into.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful creation of the deadline task.
     * @throws DukeException If the deadline task could not be saved or created due to invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

            String description = parsedCommand[0];
            assert !description.isBlank() : "deadline description cannot be empty";
            String[] timeStamp = parsedCommand[1].split(" ");

            LocalDate deadlineDate = LocalDate.parse(timeStamp[0], dateFormatter);
            LocalTime deadlineTime = LocalTime.parse(timeStamp[1], timeFormatter);

            Task deadline = new Deadline(description, deadlineDate, deadlineTime);
            tasks.addTask(deadline);
            storage.saveToFile(tasks);
            return ui.showNewTask(deadline, tasks.getListSize());
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
