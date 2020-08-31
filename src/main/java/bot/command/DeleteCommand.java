package bot.command;

import bot.Storage;
import bot.TaskList;
import bot.task.Task;

import java.io.IOException;

/**
 * A type of command that removes Task from TaskList.
 */
public class DeleteCommand extends Command{
    private int itemIndex;

    public DeleteCommand(String cmd, int ind) {
        super(cmd);
        itemIndex = ind-1;
    }

    /**
     * The task of the index, itemIndex, is removed from the taskList and its storage is updated accordingly.
     *
     * @param taskList taskList where the task is removed from.
     * @param storage storage associated with the taskList.
     * @return Response shown to the user.
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws IllegalArgumentException, IOException {
        try {
            Task task = taskList.remove(itemIndex);
            storage.saveUserData(taskList);
            return "Noted. I've removed this task: \n    " +
                    task + "\n    " +
                    "Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sorry, do what? Please give me a valid input." +
                    " Thank you.");
        } catch (IOException e) {
            throw new IOException("Sorry, I can't seem to save it.");
        }
    }
}
