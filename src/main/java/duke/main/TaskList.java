package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The class that is responsible of taking care of the user's list of tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Returns a TaskList that contains all the tasks which has the keyword in its
     * description.
     * @param keyword The keyword
     * @return The TaskList that contains all the tasks with the keyword
     */
    public TaskList findList(String keyword) {
        TaskList keywordList = new TaskList();
        for (Task task : this) {
            if (task.getDescription().contains(keyword)) {
                keywordList.add(task);
            }
        }
        return keywordList;
    }

}
