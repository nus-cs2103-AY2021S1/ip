package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class AddCommand extends Command {
    final Task[] myTasks;
    public AddCommand(Task ... myTasks) {
        this.myTasks = myTasks;
    }

    /**
     * Adds a duke.task to toDoList in duke.task.TaskList, saves the new duke.task list in a
     * txt file and prints a success message.
     * @param ui a duke.Ui instance to enable calling of duke.Ui functions
     * @param storage a duke.Storage instance to enable calling of duke.Storage functions
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        String returnStr = "";
        for (Task t : myTasks) {
            TaskList.addToList(t);
            returnStr = returnStr + (ui.printFormat(t.toString()));
        }
        storage.save(TaskList.getList());
        returnStr = returnStr + ui.printNumberOfTasks(TaskList.getList().size());
        return returnStr;
    }
}
