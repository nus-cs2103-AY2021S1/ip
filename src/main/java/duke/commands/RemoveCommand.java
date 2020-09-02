package duke.commands;


import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class RemoveCommand extends Command {
    final int removeInt;

    public RemoveCommand(int removeInt) {
        this.removeInt = removeInt;
    }

    /**
     * Removes a duke.task from the duke.task list, saves the changes to a txt file
     * and prints a success message.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        String returnStr = ui.removePrint(removeInt);
        TaskList.removeFromList(removeInt);
        storage.save(TaskList.getList());
        return returnStr + ui.printNumberOfTasks(TaskList.getList().size());
    }
}
