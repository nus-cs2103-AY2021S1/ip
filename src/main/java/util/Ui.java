package util;

import task.Task;

/**
 * The UI class deals with interactions with the user and prints responses accordingly.
 */
public class Ui {
    /**
     * Returns the welcome message.
     *
     * @return returns the welcome message.
     */
    public String showWelcome() {
        return "Serina here, how can I assist you? Enter 'help' to learn what I can do for you.";
    }

    /**
     * Prints the help message with instructions on how to use Serina.
     */
    public String showHelp() {
        return "I am Serina, your personal toDo Manager. These are the commands you can issue me: " + "\n"
                + "1. list" + " => lists all your current tasks " + "\n"
                + "2. todo {task description} => creates a new to do task with given description " + "\n"
                + "3. deadline {task description} /by {YYYY-MM-DD HHMM} => creates a new task with given "
                + "description and deadline" + "\n"
                + "4. event {task description} /at {YYYY-MM-DD HHMM} => creates a new task with given "
                + "description and event time" + "\n"
                + "5. done {task number} => marks the given task as done" + "\n"
                + "6. delete {task number} => deletes the given task" + "\n"
                + "7. tag {task number} {tag name} => tag selected task with given tag name" + "\n"
                + "8. tag {task number} delete => delete tag from selected task" + "\n"
                + "9. find {query} => lists out tasks based on your query" + "\n"
                + "10. bye => allow me to get some rest";
    }

    /**
     * Returns the string representation of a task.
     *
     * @param task Task to print
     * @param taskNum Task number of task
     * @return string representation of a task.
     */
    public String showTask(Task task, int taskNum) {
        return taskNum + ". " + task + "\n";
    }

    /**
     * Returns the add task acknowledgement string.
     *
     * @param task    Task to print
     * @param taskNum Task number of task
     * @return  Add task acknowledgement string.
     */
    public String showAddTask(Task task, int taskNum) {
        return "The following task has been added: \n" + this.showTask(task, taskNum);
    }

    /**
     * Returns the delete task acknowledgement string.
     *
     * @param task    Task to print
     * @param taskNum Task number of task
     * @return The delete task acknowledgement.
     */
    public String showDeleteTask(Task task, int taskNum) {
        return "The following task has been deleted: \n" + this.showTask(task, taskNum);
    }

    /**
     * Returns the task mark as done acknowledgement string.
     *
     * @param task    Task to print
     * @param taskNum Task number of task
     * @return The task mark as done acknowledgement string.
     */
    public String showDoneTask(Task task, int taskNum) {
        return "The following task has been marked as done: \n" + this.showTask(task, taskNum);
    }

    /**
     * Returns the list command statement string.
     *
     * @return List command statement.
     */
    public String showListStatement() {
        return "Here are your current tasks: \n";
    }

    /**
     * Returns the find command statement string.
     *
     * @return The find command statement string/
     */
    public String showFindStatement(boolean isEmpty) {
        if (isEmpty) {
            return "No tasks match your query, try searching for something else. \n";
        } else {
            return "Here are your search results: \n";
        }
    }

    /**
     * Returns the tag added to task acknowledgement string.
     *
     * @param task    Task to print
     * @param taskNum Task number of task
     * @return The task tag added to task acknowledgement string.
     */
    public String showTagAdded(Task task, int taskNum) {
        return "The following task has been tagged: \n" + this.showTask(task, taskNum);
    }

    /**
     * Returns the tag removed from task acknowledgement string.
     *
     * @param task    Task to print
     * @param taskNum Task number of task
     * @return The task tag added to task acknowledgement string.
     */
    public String showTagRemoved(Task task, int taskNum) {
        return "The following task's tag has been removed: \n" + this.showTask(task, taskNum);
    }

    /**
     * Returns the goodbye message string.
     *
     * @return Goodbye message string.
     */
    public String showGoodbye() {
        return "Alright, call me again if you need me.";
    }

    /**
     * Return the error string.
     *
     * @return Return error string.
     */
    public String showError(String err) {
        return err;
    }
}
