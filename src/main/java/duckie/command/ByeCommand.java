package duckie.command;

import duckie.Storage;
import duckie.Ui;
import duckie.task.TaskList;

/**
 * Command to signify the exit of the chatbot
 */
public class ByeCommand extends Command {
    /**
     * Check if the Command is exiting
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Display the ending message and end the chatbot
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnding();
    }
}
