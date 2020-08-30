package duke;

import task.Task;

import java.util.ArrayList;

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

    Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey! That number is not in the list!");
        }
    }

    /**
     * List all tasks currently stored in the system.
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

    boolean addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
        return true;
    }

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
     * as done.
     *
     * @param taskNum The task number to mark as done.
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