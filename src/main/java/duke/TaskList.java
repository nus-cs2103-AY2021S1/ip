package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Contains the list of <code>Task</code> object. <code>TaskList</code> saves all existing <code>Task</code> in a
 * list and contains functionalities that supports the addition and deletion of individual <code>Task</code> in the
 * list.
 */
public class TaskList {
    private final List<Task> listOfTask;

    public TaskList(List<Task> taskList) {
        this.listOfTask = taskList;
    }

    /**
     * Adds the specified <code>Task</code> into the list.
     *
     * @param task Task to be added
     * @return the total number of existing <code>Task</code> objects in the list at this point in time
     */
    public int addTask(Task task) {
        listOfTask.add(task);

        return listOfTask.size();
    }

    /**
     * Deletes the <code>Task</code> at the specified index in the list.
     *
     * @param index Index of <code>Task</code> to be deleted
     * @return <code>Task</code> deleted from the list
     * @throws IndexOutOfBoundsException if an invalid index is given
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        Task t = listOfTask.get(index);
        listOfTask.remove(index);
        return t;
    }

    /**
     * Returns the list containing all <code>Task</code>.
     *
     * @return Returns the list containing all <code>Task</code>
     */
    public List<Task> getList() {
        return listOfTask;
    }

    /**
     * Returns the total number of <code>Task</code> in the list at this point in time.
     *
     * @return Returns the total number <code>Task</code> in the list at this point in time
     */
    public int total() {
        return listOfTask.size();
    }

    /**
     * Marks the specified <code>Task</code> as done.
     *
     * @param index Index of <code>Task</code> in list to be marked as completed
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws IndexOutOfBoundsException if invalid index is given
     */
    public String markDone(int index) throws IndexOutOfBoundsException {
        return listOfTask.get(index).markAsDone();
    }

    /**
     * Iterates through the whole list of <code>Task</code> and returns a list containing all <code>Task</code> on
     * the specified date.
     *
     * @param date Date to be checked
     * @return a list containing all <code>Task</code> on that day.
     */
    public List<Task> checkDate(LocalDate date) {

        List<Task> sameDates = new ArrayList<>();
        for (Task t : listOfTask) {
            if (t.compareDate(date)) {
                sameDates.add(t);
            }
        }

        return sameDates;
    }

    /**
     * Iterates through the whole list of <code>Task</code> and returns a list containing all <code>Task</code> with
     * the specified keyword.
     *
     * @param keywords to be searched
     * @return a list containing all <code>Task</code> with the keyword.
     */
    public List<Task> searchTask(String ... keywords) {
        List<Task> validTask = new ArrayList<>();
        for (Task t : listOfTask) {
            for (String keyword : keywords) {
                if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    validTask.add(t);
                }
            }
        }
        return validTask;
    }

}
