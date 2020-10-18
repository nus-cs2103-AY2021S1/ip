package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a task list that stores all the tasks.
 * @version 1.0
 */
public class TaskList {
    private ArrayList<Task> list;
    private int activeTasks;

    /**
     * Creates a new TaskList object with and empty list and 0 active tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.activeTasks = 0;
    }

    /**
     * Creates a new TaskList object with the given list and count the number of active tasks.
     *
     * @param list The given list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        activeTasks = 0;
        for (Task t : list) {
            if (!t.getStatus()) {
                activeTasks++;
            }
        }
    }

    /**
     * Returns the current position of the last incomplete task.
     * @return index of the last incomplete task.
     */
    public int getActiveTasks() {
        return activeTasks;
    }

    /**
     * Adds a task to the TaskList to before all done tasks if it is undone and increment the active task count by 1,
     * or to the last of the list if it is done and do not increment the active task count.
     *
     * @param task The specified task.
     */
    public void addTask(Task task) {
        list.add(task.getStatus() ? list.size() : activeTasks, task);
        activeTasks += task.getStatus() ? 0 : 1;
    }

    /**
     * Deletes a task from the TaskList and decrement the active task count by 1 if it is undone,
     * or do not decrement the active task count if it is done.
     *
     * @param task The specified task.
     */
    public void deleteTask(Task task) {
        list.remove(task);
        activeTasks -= task.getStatus() ? 0 : 1;
    }

    /**
     * Marks a task status to be done and move it to the last.
     *
     * @param task The specified task.
     */
    public void markTaskAsDone(Task task) {
        task.setDone();
        activeTasks--;
        deleteTask(task);
        addTask(task);
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param index The specified index of a task.
     * @return The task at the specified index in the list.
     */
    public Task getTaskAtIndex(int index) {
        assert index <= list.size() + 1;
        return list.get(index - 1);
    }

    /**
     * Returns the ArrayList of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Prints the list with specified mode.
     * Prints all the undone tasks in the list if the mode is undone.
     * Prints all tasks in the list if the mode is all.
     * Prints all tasks with description containing the mode keyword in any other condition.
     *
     * @param mode The mode specified for printing the list.
     */
    public void printList(String mode) {
        int i = 1;
        for (Task t : list) {
            switch (mode) {
            case "Undone":
                if (t.getStatus()) {
                    continue;
                }
                break;
            case "All":
                break;
            default:
                if (!t.getDescription().contains(mode)) {
                    continue;
                }
            }
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }

    /**
     * Prints the tasks with the specified date information.
     *
     * @param date The date information.
     */
    public void printList(LocalDate date) {
        int i = 1;
        for (Task t : list) {
            if (!t.hasDate() || !t.getDate().equals(date)) {
                continue;
            }
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }
}
