package duke;

import java.util.ArrayList;
import java.util.List;

import duke.comparators.DeadlineAndEventComparator;
import duke.comparators.TaskComparator;
import duke.task.Task;

public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs a TaskList object with an ArrayList of Task
     * @param taskList ArrayList of Task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Sorts tasklist by shoving toDo tasks towards the back and sorting them lexicographically.
     * Deadlines and Events at the front are then sorted by dates in ascending order followed by lexicographic order
     */
    public void sort() {
        this.taskList.sort(new TaskComparator()
                .thenComparing(new DeadlineAndEventComparator()));
    }

    /**
     * Returns ArrayList of Task found in TaskList
     * @return ArrayList of Task
     */
    public List<Task> getList() {
        return taskList;
    }

    /**
     * Appends the specified Task at the end of the ArrayList of Task found in TaskList
     * @param task Task object to be added
     * @return true when successful, false when unsuccessful
     */
    public boolean add(Task task) {
        return this.taskList.add(task);
    }

    /**
     * Removes the Task at the specific position from the ArrayList of Task found in TaskList
     * @param index index of the task to remove
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns the Task at the specified position from the ArrayList of Task found in TaskList
     * @param index index of the task to return
     * @return
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the number of elements from the ArrayList of Task found in TaskList
     * @return the number of elements from the ArrayList of Task found in TaskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a String that represents a TaskList object
     * @return String that represents a TaskList object
     */
    public String toString() {
        return this.taskList.toString();
    }
}
