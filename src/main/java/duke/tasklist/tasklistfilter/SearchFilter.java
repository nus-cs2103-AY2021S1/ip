package duke.tasklist.tasklistfilter;

import duke.task.Task;

/**
 * Search the tasklist for tasks containing the given keyword.
 */
public class SearchFilter implements TaskListFilter {
    private String keyword;

    /**
     * SearchFilter Constructor.
     *
     * @param keyword The keyword.
     */
    public SearchFilter(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Check if the task description contains the keyword.
     *
     * @param task The current task.
     * @return A boolean if the task contains the keyword.
     */
    public boolean filter(Task task) {
        return task.getDescription().contains(keyword);
    }
}
