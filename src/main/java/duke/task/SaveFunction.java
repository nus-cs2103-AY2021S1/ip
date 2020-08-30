package duke.task;

/**
 * This is a functional interface for the save function in the connectStorage method in the TaskList.
 */
public interface SaveFunction {

    /**
     * Save the given TaskList to disk.
     *
     * @param list the TaskList to save
     */
    public void save(TaskList list);
}
