package duke;

import task.Task;

import java.util.ArrayList;

/**
 * Contains Tasks stored in an ArrayList. Includes other
 * useful methods such as adding, deleting, and listing of tasks.
 */
class TaskList {

    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    int getSize() {
        return tasks.size();
    }

    ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Gets Task from the list based on the index supplied
     * @param index index of Task to retrieve from the task list.
     * @return Task from the task list
     * @throws DukeException Exception while getting Task from task list.
     */
    Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! That number is not in the list!");
        }
    }

    /**
     * List all tasks currently stored in the system.
     * @return String format of all the Tasks stored in the task list.
     */
    String listAllTasks() {
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
     * Adds a Task into the Task list and returns true
     * if successful.
     * @param taskToAdd Task to add into taskList.
     * @return true if adding of Task was successful.
     */
    boolean addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
        return true;
    }

    /**
     * Removes a task from the task list based on its
     * number in the list.
     * @param taskNum the number of the task to remove.
     * @return true if deleting of task was successful.
     * @throws DukeException Exception while deleting of task.
     */
    boolean deleteTask(int taskNum) throws DukeException {
        try {
            if (taskNum > 0 && taskNum <= tasks.size()) {
                tasks.remove(taskNum - 1);
                return true;
            } else {
                throw new DukeException("Invalid task number for current task list.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! That number is not in the list!");
        }
    }

    /**
     * Given a particular task number, mark that task in the task list as done.
     * @param taskNum The task number to mark as done.
     * @return true if marking of task as done was successful.
     * @throws DukeException Exception while marking task as done.
     */
    boolean markTaskDone(int taskNum) throws DukeException {
        try {
            if (taskNum < 0 || taskNum > tasks.size()) {
                throw new DukeException("task.Task number does not exist.");
            } else {
                Task t = tasks.get(taskNum - 1);
                t.markDone();
                return true;
            }
        } catch (DukeException e) {
            throw e;
        }
    }
}