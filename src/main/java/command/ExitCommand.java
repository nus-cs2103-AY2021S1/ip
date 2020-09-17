package command;

import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.TaskList;

/**
 * Represents the command to close logic.Duke
 */
public class ExitCommand extends Command {

    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to save the files for closing.
     * @param tasks List of tasks that logic.Duke is handling.
     * @param ui Handles what the user reads.
     * @param storage Writes the save file.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidSaveFileException {
        storage.saveFile(tasks.getTasks());
        return ui.printOutput("Bye. Hope to see you again soon!");
    }
}
