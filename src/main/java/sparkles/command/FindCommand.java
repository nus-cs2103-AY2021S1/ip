package sparkles.command;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

import java.util.List;

/**
 * Represent a find command.
 */
public class FindCommand extends Command{
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Finds all tasks that is related
     * to the keyword and display to
     * the user.
     *
     * @param taskList, TaskList object containing list
     *                  of tasks.
     * @param ui,       Ui Object that interacts with user.
     * @param storage,  storage object dealing with
     *                  local disk file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        List<Task> listFromTaskList = taskList.getStorage();

        if (listFromTaskList.isEmpty()) {
            ui.print("     OOPS!! Task list is empty!");
        } else {
            try {
                String find = command.substring(5).trim();

                int index = 1;

                for (int i = 0; i < listFromTaskList.size(); i++) {
                    Task task = listFromTaskList.get(i);
                    String desc = task.getDescription();

                    if (desc.contains(find)) {
                        task.printTask(index);
                        index++;
                    }
                }

                if (index == 1) {
                    ui.print("     OOPS!!No such task!");
                }
            } catch (Exception ex) {
                if (ex instanceof StringIndexOutOfBoundsException) {
                    throw new SparklesException("     OOPS!! Related keyword of task in the list to find is not specified!");
                }
            }
        }
    }
}
