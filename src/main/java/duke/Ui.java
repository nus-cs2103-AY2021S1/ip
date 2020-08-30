package duke;

import duke.task.Task;

/**
 * In charge of interactions with the user.
 */
public class Ui {

    private static final String BORDER =
            "==========================================";

    /**
     * Prints out the farewell message to the user.
     * @return String farewell message.
     */
    public String printFarewell() {
        return "Thanks for chatting with me, "
                + "see you soon!\n"
                + BORDER;
    }

    /**
     * Prints out the tasks in the list to the user.
     * @return String tasks in the list.
     */
    public String printList() {
        return "Here are the tasks"
                + " in your list:";
    }

    /**
     * Prints out the message telling user that task is empty.
     * @return String message telling user that task is empty.
     */
    public String printNoTasks() {
        return "Oops! Your list is empty!";
    }

    /**
     * Prints out the saving tasks to hard disk message to user.
     * @return String saving tasks message.
     */
    public String printSave() {
        return "List saved to hard disk "
                + "at data/tasks.csv";
    }

    /**
     * Prints out the task added message to the user.
     * @param task Task object user added.
     * @param taskNumber Integer number representing number
     *                   of tasks in list.
     * @return String task added message.
     */
    public String printAdded(Task task, int taskNumber) {
        return ">" + "added: " + task + "<\n"
                + "You now have "
                + taskNumber
                + " task(s) in your list.";
    }

    /**
     * Prints message informing user that task has been deleted.
     * @param task Task object that user deleted.
     * @param taskNumber Integer number representing number of
     *                   tasks in list.
     * @return String task deleted message.
     */
    public String printDeleted(Task task, int taskNumber) {
        return "I have deleted this task: \n" + task + "\n"
                + "You now have "
                + taskNumber
                + " task(s) in your list.";
    }

    /**
     * Prints out the task that user has marked as done.
     * @param tasks TaskList object of the chat bot.
     * @param taskNumber Integer number representing the index of the task
     *                   that was marked as done.
     * @return String done message.
     */
    public String printDone(TaskList tasks, int taskNumber) {
        return "I have marked this task as done: \n"
                + tasks.getTask(taskNumber);
    }

    /**
     * Prints out the task that matches what user intends to find.
     * @return String task find message.
     */
    public String printFind() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Prints out to the user that no tasks corresponding
     * to the keyword was found.
     * @param match String input of user.
     * @return String message informing user of no matches.
     */
    public String printNoMatch(String match) {
        return "Sorry! No matches found for " + match + ".";
    }
}
