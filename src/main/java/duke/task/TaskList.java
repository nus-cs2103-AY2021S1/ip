package duke.task;

import java.util.ArrayList;

/**
 * Contains Duke's list of tasks and operations for adding, deleting, updating,
 * finding, and marking as done tasks in the list.
 */
public class TaskList {
    /**
     * List of <code>Task</code>s
     */
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Default constructor
     */
    public TaskList() {}

    /**
     * Adds a given <code>Task</code> to the <code>taskList</code>.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a specified task from the <code>taskList</code>.
     *
     * @param taskNumber index of the task to be deleted in <code>taskList</code>
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    /**
     * Updates the description of a specified task.
     *
     * @param taskNumber  index of the task to be updated in <code>taskList</code>
     * @param newTaskDesc new description to update the task to
     * @return            the edited task
     */
    public Task updateTaskDesc(int taskNumber, String newTaskDesc) {
        Task taskToBeEdited = taskList.get(taskNumber);
        taskToBeEdited.editDescription(newTaskDesc);
        return taskToBeEdited;
    }

    /**
     * Marks a specified task as done by calling the <code>Task</code> method <code>markAsDone</code>.
     *
     * @param taskNumber  index of the task to be updated in <code>taskList</code>
     */
    public void markAsDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
    }

    /**
     * Searches for all tasks whose description contains a specified keyword,
     * and returns a <code>TaskList</code> of all those tasks.
     *
     * @param keyword  the String used to search for tasks
     * @return         a list of tasks with descriptions containing <code>keyword</code>
     */
    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList(); // to be returned

        // process the keyword to make it easier to match
        String searchTerm = keyword.toLowerCase();

        for (Task t : taskList) {
            // process the task's string to make it easier to match
            String processedTaskString = t.toString().toLowerCase();

            if (processedTaskString.contains(searchTerm)) {
                foundTasks.addTask(t);
            }
        }

        return foundTasks;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }
}
