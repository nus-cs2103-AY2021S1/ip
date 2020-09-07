package butler.task;

import butler.exception.ButlerException;
import java.util.ArrayList;

// contains the task list
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void completeTask(int i) throws ButlerException {
        try {
            taskList.get(i - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please give a valid index. \""
                    + i + "\" is not a valid index.\n");
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws ButlerException {
        try {
            taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please give a valid index. \""
                    + index + "\" is not a valid index.\n");
        }
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Produces a sub list of tasks that contains the given <code>keyword</code>.
     *
     * @param keyword Keyword by which tasks are filtered by.
     * @return A list of tasks that contains the given <code>keyword</code>.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getSummary().contains(keyword)) {
                filteredList.add(t);
            }
        }
        return new TaskList(filteredList);
    }
}
