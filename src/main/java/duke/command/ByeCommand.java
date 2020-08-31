package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to say bye to the user before exiting the programme.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a <code>ByeCommand</code> object.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Says bye to the user before exiting the programme.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        String responseMessage = "Bye. Hope to see you again soon!";
        boolean shouldExit = getIsExit();
        storage.save(tasks);
        return new CommandResponse(responseMessage, shouldExit);
    }
}
