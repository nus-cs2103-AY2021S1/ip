package duke.tasklist.tasklistfilter;

import duke.task.Task;

public class SearchFilter implements TaskListFilter {
    private String keyword;
    public SearchFilter(String keyword) {
        this.keyword = keyword;
    }
    public boolean filter(Task task) {
        return task.getDescription().contains(keyword);
    }
}
