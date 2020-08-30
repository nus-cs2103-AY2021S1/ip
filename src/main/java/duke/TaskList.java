package duke;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * Represents a task list to store user's tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Creates a task list object with ArrayList as the underlying data structure.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task into the task list.
     *
     * @param newTask New Task to be added.
     */
    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Gets a task from the task list at a given index.
     *
     * @param index Index to retrieve Task from the task list.
     * @return Task represented at given index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Gets total number of tasks currently stored in the task list.
     *
     * @return Size of the task list.
     */
    public int getNumTask() {
        return this.taskList.size();
    }

    /**
     * Lists tasks currently in the task list.
     *
     */
    public void listItems() {
        for (int i = 0; i < this.taskList.size(); i++) {
            String currentLine = "      " + (i + 1) + ". " + this.taskList.get(i);
            System.out.println(currentLine);
        }
    }

    /**
     * Removes the task at a given index.
     *
     * @param index Given index to remove the task in the task list.
     * @return Task removed.
     */
    public Task removeTask(int index) {
        return this.taskList.remove(index);
    }
}
