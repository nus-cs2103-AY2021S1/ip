package luoyi.duke.data;

import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;

public interface IDuke {
    /**
     * Prints greeting message.
     */
    void greet();

    /**
     * Prints goodbye message.
     */
    void bye();

    /**
     * Returns a specific task from the list.
     *
     * @return The specified task.
     */
    ITask getTask(int id);

    /**
     * Returns a list of all the tasks.
     *
     * @return The specified task
     */
    TaskList getTasks();

    /**
     * Gets the number of tasks in the list.
     *
     * @return Number of tasks currently in the list.
     */
    int getNumTask();

    /**
     * Handles a given command and returns the resulting Duke.
     *
     * @param command A user command.
     * @return Resulting Duke object.
     */
    IDuke handleCommand(String command);

    Storage getStorage();
}
