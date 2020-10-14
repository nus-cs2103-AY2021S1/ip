import java.util.ArrayList;
import java.util.List;

/**
 * Stores the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates a new TaskList with the given pre-existing tasks.
     *
     * @param read The list of tasks read from the save file.
     */
    public TaskList(List<Task> read) {
        this.taskList = read;
    }

    /**
     * Checks if the tasks list is empty.
     *
     * @return Whether the list is empty.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Adds a task to the task list.
     *
     * @param toAdd The Task to be added.
     */
    public void add(Task toAdd) {
        taskList.add(toAdd);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param toDelete The Task to be deleted.
     */
    public void delete(int toDelete) {
        taskList.remove(toDelete);
    }

    /**
     * Gets the task from the specified index in the task list.
     *
     * @param index The index of the task.
     * @return The task at index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Completes a task in the task list.
     *
     * @return The error or complete message.
     */
    public String completeTask(int index) {
        String result;
        Task task;
        if (index < 0 || index >= taskList.size()) {
            result = Responses.BAD_INDEX;
        } else {
            task = taskList.get(index);
            assert task != null : "task is null";
            if (task.isDone) {
                result = Ui.errorMsg("you have already completed " + task.task + "!");
            } else {
                task.complete();
                result = Ui.print("congrats on finishing your task :) it's marked as done:\n\t" + task);
            }
        }
        return result;
    }

    /**
     * Deletes a task from the task list.
     *
     * @return The error or deleted message.
     */
    public String deleteTask(int index) {
        String result;
        Task task;
        if (index < 0 || index >= taskList.size()) {
            result = Responses.BAD_INDEX;
        } else {
            task = get(index);
            assert task != null : "task is null";
            delete(index);
            result = Ui.print("i've removed the following task from the list:\n\t" + task
                    + "\nnow you have " + size() + " items in your tasklist.");
        }
        return result;
    }

    /**
     * Converts tasks in taskList to String format.
     *
     * @return String representation of the tasks in list, or a message if it is empty.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (isEmpty()) {
            result = new StringBuilder("this list doesn't contain any tasks!");
        } else {
            result.append("1. ").append(taskList.get(0));
            for (int i = 2; i <= taskList.size(); i++) {
                result.append(" ✰\n✰ ").append(i).append(". ").append(taskList.get(i - 1));
            }
        }
        return result.toString();
    }

    /**
     * Finds a task containing the given keyword.
     * @param keyword The search keyword.
     * @return The list of tasks matching this keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> temp = new ArrayList<Task>();
        for (Task t : taskList) {
            if (t.task.contains(keyword)) {
                temp.add(t);
            }
        }
        return new TaskList(temp);
    }

}
