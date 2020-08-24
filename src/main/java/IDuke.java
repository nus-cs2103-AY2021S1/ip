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
     * Gets a specific task from the list.
     *
     * @return The specified task
     */
    ITask getTask(int id);

    /**
     * Gets a list of all the tasks.
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
