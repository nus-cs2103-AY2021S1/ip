package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list contains the task list.
 */
public class TaskList {
    @SuppressWarnings("CanBeFinal")
    private List<Task> lst = new ArrayList<>();
    private static int numberOfDoneTasks = 0;
    private static LocalDate lastLoginDate = LocalDate.now();

    /** Returns number of done tasks.
     * @return number of done tasks
     */
    public static int getNumberOfDoneTasks() {
        return numberOfDoneTasks;
    }

    /** Returns last log in date.
     * @return last log in date
     */
    public static LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    /** Resets number of done tasks as 0. */
    public static void resetNumberOfDoneTasks() {
        numberOfDoneTasks = 0;
    }

    /** Sets number of done tasks as input i.
     * @param i the new number of done tasks
     */
    public static void setNumberOfDoneTasks(int i) {
        numberOfDoneTasks = i;
    }

    /** Increments number of done tasks by 1. */
    public static void incrementNumberOfDoneTasks() {
        numberOfDoneTasks++;
    }

    /** Sets last log in data as date.
     * @param date the date to be set as last log in date
     */
    public static void setLastLoginDate(LocalDate date) {
        lastLoginDate = date;
    }

    /**
     * Returns the array list task list.
     * @return task list
     */
    public List<Task> getLst() {
        return lst;
    }

    /**
     * Adds a task to the task list.
     * @param task task to be added
     */
    public void add(Task task) {
        lst.add(task);
    }

    /**
     * Adds a task of a certain type to the task list.
     * @param message task message
     * @param taskType task type
     */
    public void addOfType(String message, TaskType taskType) {
        switch (taskType) {
        case T:
            lst.add(new Todo(message));
            break;
        case D:
            lst.add(new Deadline(message));
            break;
        case E:
            lst.add(new Event(message));
            break;
        // We only use this method in our own implementation
        // with specified task type T, D, or E,
        // thus task type is always valid.
        // No need to throw exception
        default:
        }
    }

    /** Returns size of task list in int form.
     * @return list size
     */
    public int size() {
        return lst.size();
    }

    /** Returns the task from task list at index i.
     * @param i index
     * @return the (i+1)th task in the list
     */
    public Task get(int i) {
        return lst.get(i);
    }

    /** Deletes the task from task list at index i.
     * @param i index
     */
    public void delete(int i) {
        lst.remove(i);
    }
}
