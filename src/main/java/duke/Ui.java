package duke;

import duke.task.Task;

/**
 * In charge of interactions with the user.
 */
public class Ui {

    public static final String BYE_DESCRIPTION = "'bye' - Save task list "
            + "to local hard disk and exit bot";
    public static final String DEADLINE_DESCRIPTION = "'deadline [task] /by "
            + "[date and time]' - Adds a task that is to be done by"
            + " a certain deadline into the task list.";
    public static final String DONE_DESCRIPTION = "'done [number]'"
            + " - Marks the task corresponding with the task index"
            + " in the task list as done";
    public static final String DELETE_DESCRIPTION = "'delete [number]'"
            + "- Deletes the task corresponding with the task index"
            + " in the task list.";
    public static final String EVENT_DESCRIPTION = "'event [task] /at "
            + "[date and time]' - Adds an event that is to be attended into "
            + "the task list.";
    public static final String FIND_DESCRIPTION = "'find [keyword]' - Find "
            + "all tasks matching the keyword from the task list and display them";
    public static final String GREET_MESSAGE =
            "===================================" + "Hi, my name is Duke."
                    + "\nWhat can I do for you?"
                    + "\nFor more information on the commands you can use, "
                    + "type 'help'.";
    public static final String TODO_DESCRIPTION = "'todo [task]' - "
            + "Adds a task that is to be done into the task list.";
    public static final String LIST_DESCRIPTION = "'list' - Brings up "
            + "the task list, displaying all the current tasks "
            + "you have added thus far.";
    private static final String BORDER =
            "=================================";

    /**
     * Prints out the farewell message to the user.
     * @return String farewell message.
     */
    public String printFarewell() {
        return "Thanks for using this bot, "
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

    /**
     * Prints out the list of commands to use in the chat bot.
     * @return String list of all the commands and their descriptions.
     */
    public String printHelp() {
        return "Here are the list of commands you can use:"
                + "\n" + BYE_DESCRIPTION
                + "\n" + DEADLINE_DESCRIPTION
                + "\n" + DONE_DESCRIPTION
                + "\n" + DELETE_DESCRIPTION
                + "\n" + EVENT_DESCRIPTION
                + "\n" + FIND_DESCRIPTION
                + "\n" + TODO_DESCRIPTION
                + "\n" + LIST_DESCRIPTION;
    }
}
