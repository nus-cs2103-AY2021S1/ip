public interface ITask {
    /**
     * Returns a task which is completed.
     *
     * @return Completed Task.
     */
    ITask markComplete();

    /**
     * Returns true if the task is done, false otherwise.
     *
     * @return true if the task is done, false otherwise.
     */
    boolean isDone();

    /**
     * Returns a string to represent data in this task.
     *
     * @return A string to represent the data in this task.
     */
    String getDataString();
}
