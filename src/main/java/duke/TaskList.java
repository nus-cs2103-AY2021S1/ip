package duke;

import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initializes TaskList to a new list of tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes TaskList to the specified list of tasks
     * 
     * @param taskList list of tasks
     */
    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the specified task to the list
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Removes a task from the list
     * @param index position of task in the list to be removed
     */
    public void removeTask (int index) {
        taskList.remove (taskList.get(index));
    }

    /**
     * Sets a task, specified by the index, as done
     * @param index position of task in the list to be marked done
     */
    public void setDone (int index) {
        taskList.get(index).markDone();
    }

    /**
     * Returns the number of tasks in the list
     * @return number of tasks
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index of the list
     * @param index position in the list to retrieve the task
     * @return Task in the list
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Filters the task list to find task descriptions which contain the specified string
     * @param s string to search for in task descriptions
     * @return New TaskList which only contains tasks with descriptions that contain the specified string
     */
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
