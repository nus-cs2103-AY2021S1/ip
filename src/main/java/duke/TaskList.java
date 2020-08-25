package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    
    /** List of tasks **/
    private final ArrayList<Task> taskList;

    /**
     * Initializes tasklist to a new list of tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes tasklist to the specified list of tasks
     * 
     * @param taskList list of tasks
     */
    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the specified task to the list
     * 
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Removes a task from the list
     * 
     * @param index position of task in the list to be removed 
     * @throws IndexOutOfBoundsException If index >= size of list
     */
    public void removeTask (int index) throws IndexOutOfBoundsException{
        taskList.remove (taskList.get(index));
    }

    /**
     * Sets a task, specified by the index, as done
     * 
     * @param index position of task in the list to be marked done
     */
    public void setDone (int index) {
        taskList.get(index).markDone();
    }

    /**
     * Returns the number of tasks in the list
     * 
     * @return number of tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index of the list
     * 
     * @param index position in the list to retrieve the task
     * @return Task in the list
     * @throws IndexOutOfBoundsException If index >= size of list
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }
    
    public TaskList filter(String s) {
        TaskList filter = new TaskList();
        
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getDescription().contains(s)) {
                filter.addTask(this.get(i));
            }
        }
        return filter;
    }
    
}
