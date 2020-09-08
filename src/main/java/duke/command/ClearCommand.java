package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.task.Task;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Removes ALL tasks.\n\n"
        + "Example: " + COMMAND_WORD;

    /**
     * Clears the task list, printing success and saving updated list to save file.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) throws DukeException {
        ArrayList<Task> taskList;
        taskList = handler.clearList();
        for (Task t1 : taskList) {
            System.out.println(t1);
        }
        storage.saveToFile(taskList);
    }
}
