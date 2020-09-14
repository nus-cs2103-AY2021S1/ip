package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list of Tasks that uses an ArrayList
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructor for the TaskList Class
     * Creates a new ArrayList of Tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for the TaskList Class
     * Takes in an existing lists of tasks
     *
     * @param taskList existing list of tasks
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task into the list
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task from the list
     *
     * @param task task to be deleted
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Retrives the task from the list
     *
     * @param index index of the task
     * @return task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the taskList
     *
     * @return size
     */
    public int getSize() {
        return taskList.size();
    }
    /**
     * Get the current list of tasks
     *
     * @return list of tasks
     */
    public String printList() {
        String content = "";
        int i = 1;

        if (taskList.isEmpty()) {
            content += "List is Empty";
        } else {
            for (Task t : taskList) {
                content += i + "." + t.toString() + "\n";
                i++;
            }
            content = content.substring(0, content.length() - 1);
        }
        return content;
    }

    /**
     * Gets the list of matching description
     *
     * @param description
     */
    public String printMatching(String description) {
        String content = "";
        int index = 1;
        for (Task task : taskList) {
            if (task.toString().contains(description)) {
                content += "\n" + index + ". " + task.toString();
                index++;
            }
        }
        return content;
    }
}
