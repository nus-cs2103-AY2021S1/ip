package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Command for users to exit the app. Created by using "bye".
 */
public class ByeCommand extends Command {

    /**
     * Print goodbye message to user.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Bye! See you again soon! :-)");
    }

    /**
     * Whether the Command causes the app to terminate.
     * Since the Command is used for users to exit the app, return true.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof  ByeCommand) {
            return true;
        }
        return false;
    }
}
