package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static final String INDENTATION = "  ";
    private ArrayList<Task> taskList;

    /**
     * Construct a TaskList object.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to taskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the task in String.
     *
     * @param task Task to be printed.
     */
    public String addedTaskToString(Task task) {
        String message = "Got it. I've added this task: \n";
        message += INDENTATION + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.\n";
        return message;
    }

    /**
     * Returns taskList in string.
     */
    public String taskListToString() {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String s = INDENTATION + (i + 1) + ". " + taskList.get(i).toString();
            message += s + "\n";
        }
        return message;
    }

    /**
     * Marks the nth task as done.
     *
     * @param n Index of the task to be marked as done.
     */
    public String markTaskAsDone(int n) {
        // Asserts that index is within the correct range.
        assert n <= taskList.size();
        String message = "Nice! I've marked this task as done: \n";
        Task task = taskList.get(n - 1);
        task.markAsDone();
        message += INDENTATION + task.toString() + "\n";
        return message;
    }

    /**
     * Marks the nth task as done.
     *
     * @param n Index of the task to be marked as done.
     */
    public String tagTaskInList(int n, String tag) {
        // Asserts that index is within the correct range.
        assert n <= taskList.size();
        String message = "Got it! I've tagged this task as: " + tag + "\n";
        Task task = taskList.get(n - 1);
        task.addTag(tag);
        return message;
    }

    /**
     * Deletes the nth task.
     *
     * @param n Index of the task to be deleted.
     */
    public String deleteTask(int n) {
        // Asserts that index is within the correct range.
        assert n <= taskList.size();
        String message = "Noted. I've removed this task:\n";
        Task task = taskList.get(n - 1);
        taskList.remove(n - 1);
        message += INDENTATION + task.toString() + "\n";
        message += "Now you have " + taskList.size() + " tasks in the list.\n";
        return message;
    }

    /**
     * Returns the taskList.
     *
     * @return TaskList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of task in the list.
     *
     * @return Integer indicating number of task in list.
     */
    public int getNumOfTask() {
        return taskList.size();
    }

    /**
     * Prints the tasks containing the keyword.
     *
     * @param keyword Keyword.
     */
    public String findTaskByKeyword(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskName().contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasksWithKeyword.size(); i++) {
            String s = INDENTATION + (i + 1) + ". " + tasksWithKeyword.get(i).toString();
            message += s + "\n";
        }
        return message;
    }
}
