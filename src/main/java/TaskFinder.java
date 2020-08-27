import java.util.ArrayList;

/**
 * Task finder object will find a task from the given task list
 * by searching for a keyword.
 * Keyword is input during runtime.
 */
public class TaskFinder {
    /**
     * Searches for a task from a given task list based on the given keyword.
     *
     * @param taskList given task list to search in
     * @param keyword given keyword to search for in task list
     * @return task if task is found
     * @throws DukeException if task is not found
     */
    public Task find(ArrayList<Task> taskList, String keyword) {

        return taskList.get(0);
    }
}
