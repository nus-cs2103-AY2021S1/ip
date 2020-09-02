package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    final Task myTask;
    public AddCommand(Task myTask) {
        this.myTask = myTask;
    }

    /**
     * Adds a duke.task to toDoList in duke.task.TaskList, saves the new duke.task list in a
     * txt file and prints a success message.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        TaskList.addToList(myTask);
        storage.save(TaskList.getList());
        String returnStr;
        returnStr = ui.printFormat(myTask.toString())
                + ui.printNumberOfTasks(TaskList.getList().size());
        return returnStr;
    }
}
