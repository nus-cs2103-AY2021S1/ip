package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Class that simulates containing all the task.
 */
public class TaskList {

    private ArrayList<Task> listTasks;

    /**
     * Initialize the list of tasks.
     */
    public TaskList() {
        listTasks = new ArrayList<>();
    }

    /**
     * Return the number of tasks in the list.
     *
     * @return Integer value of the size of the list
     */
    public int size() {
        return listTasks.size();
    }

    /**
     * Retrieve the task at that index 'index' of the list.
     *
     * @param index Integer value.
     * @return A task at that index 'index'.
     */
    public Task get(int index) {
        return listTasks.get(index);
    }

    /**
     * Add the tasks into the list of tasks.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        listTasks.add(task);
    }

    /**
     * Remove the task at that index 'index' of the list.
     *
     * @param index Integer value.
     */
    public void remove(int index) {
        listTasks.remove(index);
    }

    /**
     * Set the task at that index 'n' of the list.
     *
     * @param index Integer value.
     * @param task Task to be inserted.
     */
    public void set(int index, Task task) {
        listTasks.set(index, task);
    }

    /**
     * Compare the task priority for reminder *Todo takes the highest priority*.
     *
     * @param task1 First task
     * @param task2 Second task
     * @return True if task1 is more important in terms of type and deadline than task2.
     */
    private boolean compare(Task task1, Task task2) {
        if (task1.getPriority() > task2.getPriority()) {
            // implies task1 is of todo and task2 is event/deadline
            return true;
        } else if (task1.getPriority() < task2.getPriority()) {
            // implies task2 is of todo and task1 is event/deadline
            return false;
        } else { // same type
            if (task1.getPriority() == 1) {
                // both task are of task todo
                return true;
            } else {
                // both task are of event/deadline
                return task1.getDueDate().isBefore(task2.getDueDate());
            }
        }
    }

    /**
     * Sort the task in the list according to due dates *Todo takes the highest priority*.
     */
    public void sortByDueDate() {
        sort(this, 0, this.size() - 1);
    }

    /**
     * Sort the task in the list according to due dates *Todo takes the highest priority*.
     *
     * @param taskList List of tasks.
     * @param left Index pointing to the left of array.
     * @param right Index pointing to the right of array.
     */
    private void sort(TaskList taskList, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(taskList, left, mid);
            sort(taskList, mid + 1, right);
            merge(taskList, left, mid, mid + 1, right);
        }
    }

    /**
     * Sort the task in the list according to due dates *Todo takes the highest priority*.
     *
     * @param taskList List of tasks.
     * @param left1 Index pointing to the left of the first half of the array.
     * @param right1 Index pointing to the right of the first half of the array.
     * @param left2 Index pointing to the left of the second half of the array.
     * @param right2 Index pointing to the right of the second half of the array.
     */
    private void merge(TaskList taskList, int left1, int right1, int left2, int right2) {
        int left = left1;
        int right = left2;
        int tempIdx = 0;
        Task[] temp = new Task[right2 - left1 + 1];
        while (left <= right1 && right <= right2) {
            if (compare(taskList.get(left), (taskList.get(right)))) { // implement comparator
                temp[tempIdx++] = taskList.get(left++);
            } else {
                temp[tempIdx++] = taskList.get(right++);
            }
        }
        while (right <= right2) {
            temp[tempIdx++] = taskList.get(right++);
        }
        while (left <= right1) {
            temp[tempIdx++] = taskList.get(left++);
        }
        for (int i = 0; i < temp.length; i++) {
            taskList.set(i + left1, temp[i]);
        }
    }

    /**
     * Check if a task already exist in the list of tasks.
     *
     * @param task The new to be added task.
     * @return Boolean value representing whether the task exist in the list of tasks.
     */
    public boolean checkExistBefore(Task task) {
        for (int i = 0; i < listTasks.size(); i++) {
            if (listTasks.get(i).toString().substring(7).equals(task.toString().substring(7))) {
                return true;
            }
        }
        return false;
    }
}
