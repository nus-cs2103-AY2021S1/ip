package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the list of <code>Task</code> object. <code>TaskList</code> saves all existing <code>Task</code> in a
 * list and contains functionalities that supports the addition and deletion of individual <code>Task</code> in the
 * list.
 */
public class TaskList {
    private final List<Task> listOfTask = new ArrayList<>();

    /**
     * Converts the given string to <code>Task</code> object and adds it to the list.
     *
     * @param task Task in string format to be converted and added
     */
    public void checkTask(String task) {
        Task t;
        String taskType = task.substring(0, 3);
        String status = task.substring(3,6);
        boolean isDone = status.equals("[" + "\u2713" + "]");
        if (taskType.equals("[T]")) {
            t = new Todo(task.substring(7), isDone);
        } else if (taskType.equals("[D]")){
            int indOfTime = task.lastIndexOf("(FINISH by: ");
            t = new Deadline(task.substring(7, indOfTime),
                    task.substring(indOfTime + 11, task.lastIndexOf(")")).trim(), isDone);
        } else {
            int indOfTime = task.lastIndexOf("(APPEAR at: ");
            t = new Event(task.substring(7, indOfTime),
                    task.substring(indOfTime + 11, task.lastIndexOf(")")).trim(), isDone);
        }
        listOfTask.add(t);
    }

    /**
     * Adds the specified <code>Task</code> into the list.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        listOfTask.add(task);
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
     * @throws IndexOutOfBoundsException if invalid index is given
     */
    public void markDone(int index) throws IndexOutOfBoundsException {
        listOfTask.get(index).markAsDone();
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

    public List<Task> searchTask(String s) {
        List<Task> validTask = new ArrayList<>();
        for (Task t : listOfTask) {
            if (t.toString().toLowerCase().contains(s)) {
                validTask.add(t);
            }
        }
        return validTask;
    }

}
