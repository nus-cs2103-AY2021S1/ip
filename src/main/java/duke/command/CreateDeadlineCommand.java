package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
/**
 * Represents the CreateDeadline Command
 */
public class CreateDeadlineCommand implements Command {
    private String title;
    private String dateTime;

    /**
     * Return new Command that create a Deadline.
     * @param input Input parsed by the Parser.
     * @throws DukeException when the format is corrupted.
     */
    public CreateDeadlineCommand(String[] input) throws DukeException {
        if (!isValidInput(input)) {
            throw new DukeException("Error: Please key in as: \n "
                    + "    deadline [title] /by YYYY-MM-DD HH:MM");
        }
        this.title = input[1];
        this.dateTime = input[2];
    }
    private boolean isValidInput(String[] input) {
        if (input.length != 3) {
            return false;
        }
        return !input[1].equals("") || !input[2].equals("");
    }
    /**
     * Execute the command.
     *
     * @param storage             Storage to save data to.
     * @param tasks               The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The response of Duke to the user Input.
     * @throws DukeException if the system fails to execute.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        Task addedDeadline = tasks.addDeadLine(title, dateTime);
        return Ui.showTaskAdded(addedDeadline.toString(), tasks.getTotalTask());
    }
}
