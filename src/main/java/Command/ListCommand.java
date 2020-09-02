package command;

import parserstorageui.Storage;
import parserstorageui.Ui;
import task.TaskList;

public class ListCommand extends Command {

    /**
     * Initializes ListCommand
     *
     * @param command
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the command
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks);
    }

    /**
     * Check if the current command is an exit command
     **/
    @Override
    public boolean isExit() {
        return false;
    }

}
