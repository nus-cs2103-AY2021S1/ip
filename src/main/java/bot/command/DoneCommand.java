package bot.command;
import java.io.IOException;

import bot.Storage;
import bot.TaskList;
import bot.task.Task;

/**
 * A type of command that mark the given Task at certain index as Done.
 */
public class DoneCommand extends Command {
    private int itemIndex;

    /**
     * Constructor for the class.
     *
     * @param cmd A string that is the keyword for command.
     * @param ind Index of the task in task list.
     */
    public DoneCommand(String cmd, int ind) {
        super(cmd);
        itemIndex = ind - 1;
    }

    /**
     * The task of the index, itemIndex, has its attribute isDone marked as True
     * from the taskList and its storage is updated accordingly.
     *
     * @param taskList taskList where the task is amended.
     * @param storage storage associated with the taskList.
     * @return Response shown to the user.
     * @throws IllegalArgumentException Input is not valid.
     * @throws IOException Error saving to storage file.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws IllegalArgumentException, IOException {
        try {
            Task task = taskList.get(itemIndex);
            task.markAsDone();
            storage.saveUserData(taskList);
            return "Nice! I've marked this task as done: \n    "
                    + task + "\n";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sorry, do what? Please give me a valid input."
                    + " Thank you.");
        } catch (IOException e) {
            throw new IOException("Sorry, I can't seem to save it.");
        }
    }
}
