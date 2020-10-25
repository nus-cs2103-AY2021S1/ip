package duke;

import java.util.ArrayList;

import task.Task;

/**
 * A Ui object deals with interactions with the user.
 *
 * @author ameliatjy
 * @version 2.0
 * @since 2020-09-01
 */
public class Ui {

    /**
     * Adds task to list and displays successful message.
     *
     * @param newTask Task to be added to the list.
     * @param currList Current lists of tasks.
     * @return String representing success message.
     */
    public String addTask(Task newTask, TaskList currList) {
        currList.add(newTask);
        return "Got it. I've added this task:\n"
                + newTask.toString()
                + "\nYou have " + currList.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Deletes task from list and displays successful message.
     *
     * @param taskNumber Task number to be deleted as specified by user.
     * @param currTask Task to be deleted from the list.
     * @param currList Current lists of tasks.
     * @return String representing success message.
     */
    public String deleteTask(int taskNumber, Task currTask, TaskList currList) {
        currList.remove(taskNumber - 1);
        return "Noted. I've removed this task:\n"
                + currTask.toString()
                + "\nNow you have " + currList.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Marks a task in the list as complete.
     *
     * @param currTask Task to be marked as complete.
     * @return String representing success message.
     */
    public String completeTask(Task currTask) {
        currTask.markAsComplete();
        return "Nice! I've marked this task as done:\n" + currTask.toString();
    }

    /**
     * Displays list of tasks with matching keywords.
     *
     * @param searchResult List of tasks with matching keywords.
     * @return String representing search result.
     */
    public String findTask(ArrayList<Task> searchResult) {
        TaskList result = new TaskList(searchResult);
        return result.displayTasks();
    }

    /**
     * Undo the previous action and displays successful message together with updated list of tasks.
     *
     * @param currList Current lists of tasks.
     * @return String representing success message.
     */
    public String undoCommand(TaskList currList) {
        return "Got it. I've undone your previous action!\n"
                + "Here is your updated list of tasks.\n"
                + currList.displayTasks();
    }

    public String endDuke() {
        return "Good bye!";
    }
}
