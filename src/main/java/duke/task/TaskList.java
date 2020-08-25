package duke.task;

import duke.common.CustomException;

import java.util.ArrayList;

/**
 * Represents all of the users tasks in a list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task to the task list.
     * @param taskindex The index of the task to be deleted in the task list.
     */
    public void deleteTask(int taskindex) {
        tasks.remove(taskindex);
    }

    /**
     * Marks a task in the task list as done
     * @param taskindex The index of the task in the task list that is to be marked as done.
     */
    public void markAsDone(int taskindex) {
        tasks.get(taskindex).markAsDone();
    }

    /**
     * Returns current task list.
     * @return current task list.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Gets a task to the task list.
     * @param taskindex The index of the task to get in the task list.
     */
    public Task getTask(int taskindex) {
        return tasks.get(taskindex);
    }

    /**
     * Returns number of tasks in the task list
     * @return number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Filters for tasks that contain the keyword.
     *
     * @return tasks that contains the keyword in an array list.
     */
    public ArrayList<Task> findTask(String keyword) throws CustomException {
        ArrayList<Task> result = new ArrayList<>();
        if (!keyword.isEmpty()) {
            for (Task task : tasks) {
                if (task.haveKeyword(keyword)) {
                    result.add(task);
                }
            }
        } else {
            throw new CustomException("keyword is left blank.");
        }
        return result;
    }
}
