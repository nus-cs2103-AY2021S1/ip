package bot.command;

import bot.Storage;
import bot.TaskList;
import bot.task.Task;

/**
 * A type of command that shows every tasks in the TaskList.
 */
public class ListCommand extends Command {
    public ListCommand(String cmd) {
        super(cmd);
    }

    /**
     * Displayed all tasks in tasklist
     *
     * @param taskList taskLIst where the items are to be shown.
     * @param storage storage associated with taskList.
     * @return A string of all Tasks in tasklist.
     */
    @Override
    public String run(TaskList taskList, Storage storage) {
        int index = 0;
        StringBuilder string = new StringBuilder("Here are the tasks in your list:\n    ");
        for (Task item : taskList.getList()) {
            index++;
            string.append(index).append(".").append(item).append("\n    ");
        }
        if (index == 0) {
            return "Nothing in the list";
        }
        string.delete(string.length() - 5, string.length());
        return string.toString();
    }
}
