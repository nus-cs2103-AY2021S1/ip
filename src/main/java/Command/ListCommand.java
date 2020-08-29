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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

    /**
     * Check if the current command is an exit command
     **/
    @Override
    public boolean isExit() {
        return false;
    }

}
