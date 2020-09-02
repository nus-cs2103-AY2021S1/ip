package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class CheckCommand extends Command {
    final int checkInt;
    public CheckCommand(int checkInt) {
        this.checkInt = checkInt;
    }

    /**
     * Marks a duke.task as done and prints a success message.
     * txt file and prints a success message.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        Task task = TaskList.getList().get(checkInt - 1);
        task.markAsDone();
        storage.save(TaskList.getList());
        /*ui.checkList(duke.task.toString(), duke.task.getTaskStatusIcon());
        ui.printNumberOfTasks(duke.task.TaskList.toDoList.size());*/
        return ui.checkList(task.toString(), task.getTaskStatusIcon())
                + ui.printNumberOfTasks(TaskList.getList().size());
    }
}
