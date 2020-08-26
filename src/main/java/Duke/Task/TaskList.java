package Duke.Task;

import java.util.ArrayList;

/**
 * Represents the list containing all the tasks.
 */
public class TaskList {
    
    protected ArrayList<Task> taskList;

    /**
     * Construct a TaskList object.
     * @param taskList a list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }
    
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Count the number of tasks in the list.
     * @return number of tasks in the list in string format
     */
    public String countNum() {
        int num = this.taskList.size();
        return "    Now you have " + num + " tasks in the list.";
    }

    /**
     * Count the number of tasks in the list.
     * @return number of tasks in the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Get a specific task from the list.
     * @param num index.
     * @return task at index num
     */
    public Task get(int num) {
        return taskList.get(num);
    }

    /**
     * Remove a specific task from the list.
     * @param num index.
     */
    public void remove(int num) {
        taskList.remove(num);
    }

    /**
     * Add a new To-do task to the list.
     * @param newToDo new to-do item.
     */
    public void addToDo(Todo newToDo) {
        this.taskList.add(newToDo);
    }

    /**
     * Add a new deadline task to the list.
     * @param newDdl new deadline.
     */
    public void addDeadline(Deadline newDdl) {
        this.taskList.add(newDdl);
    }

    /**
     * Add a new event task to the list.
     * @param newEvent new event.
     */
    public void addEvent(Event newEvent) {
        this.taskList.add(newEvent);
    }
}
