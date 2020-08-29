package command;

import exceptions.UnSpecifiedFind;
import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class FindCommand extends Command {

    /**
     * Initializes FindCommand
     *
     * @param command
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Executes the command
     **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnSpecifiedFind {
        try {
            ui.showFoundTasks(tasks, this.command.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new UnSpecifiedFind("☹ OOPS!!! Please specify the keyword to be found.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
