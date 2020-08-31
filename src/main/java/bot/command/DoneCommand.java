package bot.command;

import bot.Storage;
import bot.TaskList;
import bot.task.Task;
import bot.util.InvalidInputException;

import java.io.IOException;

public class DoneCommand extends Command {
    private int itemIndex;

    public DoneCommand(String cmd, int ind) {
        super(cmd);
        itemIndex = ind-1;
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IllegalArgumentException, IOException {
        try {
            Task task = taskList.get(itemIndex);
            task.markAsDone();
            storage.saveUserData(taskList);
            return "Nice! I've marked this task as done: \n    " +
                    task + "\n";
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sorry, do what? Please give me a valid input." +
                    " Thank you.");
        } catch (IOException e) {
            throw new IOException("Sorry, I can't seem to save it.");
        }
    }
}
