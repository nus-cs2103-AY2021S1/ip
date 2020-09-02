package duke;

import java.util.ArrayList;

/**
 * Contains the overall user's task list, and it provide operations to modify task in the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * TaskList Constructor.
     *
     * @param tasks The overall user's task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList contains all user's tasks in the task list.
     *
     * @return The ArrayList contains all user's tasks in the task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds new to-do type task to the task list and calls the Ui class to display a message.
     *
     * @param taskTitle A string of Event task name.
     * @param tasks The overall user's task list.
     */
    public static String addNewTodoTask(String taskTitle, ArrayList<Task> tasks) {
        ToDos newTodoTask = new ToDos(taskTitle, false);
        tasks.add(newTodoTask);
        return Ui.addTodoTaskMsg(tasks);
    }

    /**
     * Adds new deadline type task to the task list and calls the Ui class to display a message.
     *
     * @param taskTitle A string of Event task name.
     * @param deadlineTime A string of deadline time
     * @param tasks The overall user's task list.
     */
    public static String addNewDeadlineTask(String taskTitle, String deadlineTime, ArrayList<Task> tasks) {
        Deadlines newDeadline = new Deadlines(taskTitle, deadlineTime, false);
        tasks.add(newDeadline);
        return Ui.addDeadlineTaskMsg(tasks);
    }

    /**
     * Adds new event type task to the task list and calls the Ui class to display a message.
     *
     * @param taskTitle A string of Event task name.
     * @param eventTime A string of Event time.
     * @param tasks The overall user's task list.
     */
    public static String addNewEventTask(String taskTitle, String eventTime, ArrayList<Task> tasks) {
        Events newEvent = new Events(taskTitle, eventTime, false);
        tasks.add(newEvent);
        return Ui.addEventTaskMsg(tasks);
    }

    /**
     * Deletes a task from the task list and calls the Ui class to display a message.
     *
     * @param index The int of the task series number in the task list.
     * @param tasks The overall user's task list.
     */
    public static String deleteTask(int index, ArrayList<Task> tasks) {
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        int newSizeOfTasks = tasks.size();
        return Ui.deleteTaskMsg(index, newSizeOfTasks, taskToDelete);
    }

    /**
     * Marks a task as done in the task list and calls the Ui class to display a message.
     *
     * @param index The int of the task series number in the task list.
     * @param tasks The overall user's task list.
     */
    public static String doneTask(int index, ArrayList<Task> tasks) {
        tasks.get(index - 1).markAsDone();
        return Ui.doneTaskMsg(index, tasks);
    }

    /**
     * Finds all tasks that match with the keyword in the task list.
     *
     * @param keyword A string of keyword to find the matching tasks.
     * @param tasks An ArrayList of all the tasks that match with the keyword.
     */
    public static String findTask(String keyword, ArrayList<Task> tasks) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getTaskTitle().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return Ui.findMatchingTasks(keyword, matchedTasks);
    }
}
