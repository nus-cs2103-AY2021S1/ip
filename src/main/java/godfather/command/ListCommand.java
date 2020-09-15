package godfather.command;

import java.util.ArrayList;

import godfather.TaskList;
import godfather.enums.Message;
import godfather.task.Task;
import godfather.ui.Ui;


/**
 * Lists all the tasks that are in the TaskList
 */
public class ListCommand implements Command {
    public ListCommand(String[] parsedInput) {
    }
    /**
     * Prints out all the tasks in the TaskList
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        // involves making the ui print everything.
        ArrayList<Task> arr = tasks.getAllTasks();
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.FETCHING_MSG.getMsg());
        for (int i = 0; i < arr.size(); i++) {
            lines.add((i + 1) + "." + tasks.getTask(i).toString());
        }
        ui.display(lines);
        return Command.listLinesToString(lines);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
