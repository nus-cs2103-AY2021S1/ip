package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;
import duke.task.Task;

import java.util.ArrayList;

public class ClearCommand extends Command {

    /**
     * Clears the task list, printing success and saving updated list to save file.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(taskListHandler handler, Storage storage) {
        ArrayList<Task> tasks;
        try {
            tasks = handler.clearList();
            for (Task t1 : tasks) {
                System.out.println(t1);
            }
            storage.saveToFile(tasks);
        } catch (DukeException e){
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
