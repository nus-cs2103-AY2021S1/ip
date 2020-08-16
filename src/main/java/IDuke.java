import java.util.List;

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
     * Stores a task in the bot.
     *
     * @param task User input task.
     * @return Updated Duke object.
     */
    IDuke storeTask(ITask task);

    /**
     * Prints the current list.
     */
    void displayTasks();

    /**
     * Get a specific task from the list
     *
     * @return The specified task
     */
    ITask getTask(int id);

    /**
     * Marks a specified task as done.
     * Task to be done is specified by its index id.
     *
     * @return Duke with task done.
     */
    IDuke doneTask(int id);
}
