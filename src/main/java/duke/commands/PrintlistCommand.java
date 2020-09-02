package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class PrintlistCommand extends Command {

    /**
     * Prints out all the tasks in the duke.task list.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.displayList(TaskList.getList(), "Check out your missions!")
                + ui.printNumberOfTasks(TaskList.getList().size());
    }
}
