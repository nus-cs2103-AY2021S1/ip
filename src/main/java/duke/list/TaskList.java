package duke.list;

import java.util.ArrayList;

import duke.task.Task;

/** Represents the list of <code>Tasks</code> that Duke stores in. */
public class TaskList extends StorableList<Task> {

    /**
     * Constructor method.
     * Initialise a new <code>ArrayList</code> of <code>Task</code>.
     */
    public TaskList() {
        super(new ArrayList<>());
    }

    /**
     * Changes the specified <code>Task</code> in <code>TaskList</code> to completed.
     *
     * @param index the index of the <code>Task</code> that is to be changed.
     * @return the <code>Task</code> that has been completed.
     */
    public Task completeTask(int index) {
        Task task = list.get(index - 1);
        task.completeTask();
        assert task.isTaskCompleted() : "Task is not marked as complete";
        return task;
    }

    /**
     * Finds all <code>Tasks</code> containing the specified search word.
     *
     * @param searchWord the <code>String</code> that is to be search with.
     * @return a <code>String</code> containing all <code>Tasks</code> that are found.
     */
    @Override
    public String search(String searchWord) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getCurrCapacity(); i++) {
            Task task = list.get(i);
            if (task.getTaskDescription().contains(searchWord)) {
                String taskString = String.format("%d. %s", i + 1, task.toString());
                sb.append(taskString);
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
