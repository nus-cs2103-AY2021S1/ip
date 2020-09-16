package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Represents the current the task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList instance.
     *
     * @param taskList An array list of previously saved tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates a TaskList instance.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Lists all current tasks in list.
     *
     * @return A string representation of all the current tasks.
     */
    public String listAllTasks() {
        if (taskList.size() <= 0) {
            return "There's nothing in my stomach right now :c";
        } else {
            String res = "";
            for (int i = 0; i < taskList.size(); i++) {
                res += String.format("%d. %s", i + 1, taskList.get(i));
                if (i < taskList.size() - 1) {
                    res += "\n";
                }
            }
            return res;
        }
    }

    /**
     * Adds new task to task list.
     *
     * @param newTask A new task.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Marks a task in task list to be done.
     *
     * @param taskNum The position of task in task list.
     * @return The task that has been marked done.
     * @throws DukeException If taskNum less than 0 or exceeds total number of tasks in task list.
     */
    public Task doneTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task task = taskList.get(taskNum - 1);
            task.doTask();
            return task;
        } else {
            throw new DukeException("What are you done with? Gotta specify a valid task number!");
        }
    }

    /**
     * Deletes task from task list.
     *
     * @param taskNum The position of task in task list.
     * @return The task that has been deleted.
     * @throws DukeException If taskNum less than 0 or exceeds total number of tasks in task list.
     */
    public Task removeTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task task = taskList.get(taskNum - 1);
            taskList.remove(taskNum - 1);
            return task;
        } else {
            throw new DukeException("What are you deleting? Gotta specify a valid task number!");
        }
    }

    /**
     * Lists all current tasks in list that matches keyword.
     *
     * @param keyword A string representing the keyword to be found in tasks.
     * @return A string representation of all the current tasks that matches keyword.
     */
    public String findMatchingTasks(String keyword) {
        String res = "";
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTask().contains(keyword)) {
                if (count != 1) {
                    res += "\n";
                }
                res += String.format("%d. %s", count, task);
                count++;
            }
        }
        return res;
    }


    /**
     * Returns size of current task list.
     *
     * @return Size of current task list
     */
    public int getTaskCount() {
        return taskList.size();
    }


}
