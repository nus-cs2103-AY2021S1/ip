package duke.command;

import duke.DukeException;
import duke.storage.TaskList;
import duke.ui.Ui;
import duke.parser.DeleteParser;

public class DeleteCommand implements Command {
    private TaskList lines;
    private DeleteParser deleteParser;

    /**
     * The constructor for the DeleteCommand object
     * @param lines the TaskList to be manipulated
     * @param deleteParser The deleteParser object to check the validity of the command
     */
    public DeleteCommand(TaskList lines, DeleteParser deleteParser) {
        this.lines = lines;
        this.deleteParser = deleteParser;
    }

    /**
     * Executes the delete command and returns a String to be output to the user, a success message if the execution was
     * successful, and an error message otherwise
     * @return The output message for the user
     */
    public String execute() {
        String message;
        try {
            int itemNumber = deleteParser.checkIfValid();
            String deletedTask = lines.getTask(itemNumber - 1);
            lines.removeTask(itemNumber - 1);
            message = Ui.deletedTask(deletedTask, lines.getNumberOfItems());
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}
