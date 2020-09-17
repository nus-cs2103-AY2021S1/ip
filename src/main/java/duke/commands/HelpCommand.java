package duke.commands;

import duke.util.Ui;
import duke.util.Storage;
import duke.tasks.TaskList;

/**
 * commands.Command that list out the task in the TaskList.
 */
public class HelpCommand extends Command {

    /**
     * List out all available commands for DukeBB.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @return outputString Command output.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printHelpMessage();
    }
}