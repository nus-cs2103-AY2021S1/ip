package duke.component;

import java.util.LinkedList;

import duke.task.Task;
/**
 * List that stores the task list.
 * Operations related to a task list has been abstracted in this class
 */
public class TaskList{
    private LinkedList<Task> list;

    /**
     * Initializes a task list using the given Task LinkedList
     */
    public TaskList() { this.list = new LinkedList<>(); }

    /**
     * Initializes an empty task list
     */
    public TaskList(LinkedList<Task> list) {
        this.list = list;
    }

    /**
     * Adds the given task to the task list
     * @param newTask the task to be added
     */
    public void add(Task newTask) { list.add(newTask); }

    /**
     * Removes the task at the given index
     * @param index the index of the task to be removed
     */
    public void remove(int index) { list.remove(index); }

    /**
     * Gets the task at the given index
     * @param index the index given indicating the position of the task
     * @return the task at the given index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Gets the task list in LinkedList format
     * @return the task LinkedList
     */
    public LinkedList<Task> getList() {
        return list;
    }

    /**
     * Marks the task at the given index as done
     * @param index the index given indicating the position of the task
     */
    public void markAsDone(int index) { list.get(index).markAsDone(); }

    /**
     * Returns the size of the task list
     * @return the size of the task list
     */
    public int size() { return list.size();}

}
