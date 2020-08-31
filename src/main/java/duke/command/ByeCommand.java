package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.Response;

/**
 * Command for users to exit the app. Created by using "bye".
 */
public class ByeCommand extends Command {

    /**
     * Instructs the GUI to exit after displaying a given goodbye message to user.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @return Response containing a goodbye message to be displayed by the GUI and an instruction for the GUI to exit
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) {
        return new Response(true, ui.formatMessage("Bye! See you again soon! :-)"));
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
