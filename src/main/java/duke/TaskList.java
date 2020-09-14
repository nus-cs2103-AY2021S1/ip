package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Contains Tasks stored in an ArrayList. Includes other
 * useful methods such as adding, deleting, and listing of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Gets Task from the list based on the index supplied
     *
     * @param index index of Task to retrieve from the task list.
     * @return Task from the task list
     * @throws DukeException Exception while getting Task from task list.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! That number is not in the list!");
        }
    }

    /**
     * List all tasks currently stored in the system.
     *
     * @return String format of all the Tasks stored in the task list.
     */
    public String getAllTasks() {
        int numEntries = tasks.size();
        String output = "";
        if (numEntries > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numEntries; i++) {
                sb.append((i + 1) + ". " + tasks.get(i));
                if (i < numEntries - 1) {
                    sb.append("\n");
                }
            }
            output = sb.toString();
        }
        return output;
    }

    /**
     * Gets all Tasks with descriptions matching the keyword(s)
     * specified by the user.
     *
     * @param keywords keyword(s) to filter Tasks.
     * @return String containing all Tasks in string
     * format appended together which also match the
     * keyword specified.
     */
    public String getMatchingTasks(String keywords) {
        String output = "";
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        tasks.forEach(task -> {
            if (task.getDescription().toLowerCase().contains(keywords.toLowerCase())) {
                filteredTaskList.add(task);
            }
        });
        int taskListSize = filteredTaskList.size();
        if (taskListSize > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < taskListSize; i++) {
                sb.append((i + 1) + ". " + filteredTaskList.get(i));
                if (i < taskListSize - 1) {
                    sb.append("\n");
                }
            }
            output = sb.toString();
        }
        return output;
    }

    /**
     * Adds a Task into the Task list and returns true
     * if successful.
     *
     * @param taskToAdd Task to add into taskList.
     * @return true if adding of Task was successful.
     */
    public boolean addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
        return true;
    }

    /**
     * Removes a task from the task list based on its
     * number in the list.
     *
     * @param taskNum the number of the task to remove.
     * @return true if deleting of task was successful.
     * @throws DukeException Exception while deleting of task.
     */
    public boolean deleteTask(int taskNum) throws DukeException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DukeException("Invalid task number for current task list.");
        } else {
            tasks.remove(taskNum - 1);
            return true;
        }
    }

    /**
     * Given a particular task number, mark that task in the task list as done.
     *
     * @param taskNum The task number to mark as done.
     * @return true if marking of task as done was successful.
     * @throws DukeException Exception while marking task as done.
     */
    public boolean markTaskDone(int taskNum) throws DukeException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DukeException("Task number does not exist.");
        } else {
            Task t = tasks.get(taskNum - 1);
            t.markDone();
            return true;
        }
    }
}
