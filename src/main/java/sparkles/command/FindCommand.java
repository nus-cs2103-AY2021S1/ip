package sparkles.command;

import java.util.List;

import sparkles.SparklesException;
import sparkles.task.Task;
import sparkles.task.TaskList;
import sparkles.util.Storage;
import sparkles.util.Ui;

/**
 * Represent a find command.
 */
public class FindCommand extends Command {

    public FindCommand(String command) {
        super(command);
    }

    /**
     * Deals with user's command.
     * Finds all tasks that is related
     * to the keyword and display to
     * the user.
     *
     * @param taskList TaskList object containing list
     *                  of tasks
     * @param ui       Ui Object that interacts with user
     * @param storage  storage object dealing with
     *                  local disk file
     * @return response to the command
     * @throws SparklesException that handles exception of Sparkles
     *                           such as StringIndexOutOfBoundsException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        String response = "";

        if (taskList.isEmpty()) {
            ui.print("     OOPS!! Task list is empty!");

            return "OOPS!! Task list is empty!";
        }

        try {
            response = this.getResponse(ui, null, taskList);

        } catch (Exception ex) {
            if (ex instanceof StringIndexOutOfBoundsException) {
                throw new SparklesException(
                        "     OOPS!! Related keyword of task in the list to find is not specified!");
            }
        }
        return response;
    }

    @Override
    public String getResponse(Ui ui, Task task, TaskList taskList) {
        assert task == null;

        String response = "";
        List<Task> listFromTaskList = taskList.getStorage();

        String find = this.command.substring(5).trim();
        int index = 1;

        for (int i = 0; i < listFromTaskList.size(); i++) {
            Task t = listFromTaskList.get(i);
            String desc = t.getDescription();

            if (desc.contains(find)) {
                response += t.printTask(index).trim() + "\n";
                index++;
            }
        }

        if (index == 1) {
            ui.print("     OOPS!!No such task!");

            response = "OOPS!!No such task!";
        }

        return response;
    }
}
