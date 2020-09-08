package bot.command;

import bot.Storage;
import bot.TaskList;
import bot.task.Task;

import java.util.ArrayList;

/**
 * A type of command that lists out all Task that contains the item.
 */
public class FindCommand extends Command {
    private String item;

    public FindCommand(String cmd, String item) {
        super(cmd);
        this.item = item;
    }

    /**
     * Displays a list of tasks where the name of the tasks contains the item.
     * The item can be a substring of the task's name.
     *
     * @param taskList the TaskList to be filter.
     * @param storage the storage associated with tasklist.
     * @return Response shown to the user.
     * @throws IllegalArgumentException
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws IllegalArgumentException {
        try {
            ArrayList<Task> currList = taskList.filter(item);
            int index = 0;
            StringBuilder string = new StringBuilder("Here are the tasks in your list:\n    ");
            for (Task item : currList) {
                index++;
                string.append(index).append(".").append(item).append("\n    ");
            }
            if (index == 0) {
                return "Nothing in the list";
            }
            string.delete(string.length() - 5, string.length());
            return string.toString();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sorry, do what? Please give me a valid input." +
                    " Thank you.");
        }
    }
}
