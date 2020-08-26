package duke;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the specified task from the list of tasks.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    /**
     * Returns the Task object corresponding to the task at the specified index.
     * @param index The index of the task to be obtained.
     * @return Task object corresponding to the task at the specified index.
     */
    public Task getTask(int index) {
        // index is bounded from 1 to len, so -1 to convert to array index
        return taskList.get(index - 1);
    }

    /**
     * Returns the entire list of tasks as an ArrayList.
     * @return ArrayList containing the current tasks stored so far.
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Returns the number of Tasks stored in the TaskList.
     * @return The number of elements in the data structure which stores the Tasks.
     */
    public int numTasks() {
        return taskList.size();
    }

    /**
     * Returns a String object containing all the tasks, that is to be passed to the UI for 
     * pretty printing.
     * @return Formatted String enumerating all the tasks in the task list.
     */
    public String getAllTasksAsString() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            output = output + ((i + 1) + ". " + taskList.get(i)) + "\n";
        }
        return output;
    }
}
