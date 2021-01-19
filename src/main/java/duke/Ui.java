package duke;

import duke.task.Task;

/**
 * This class is responsible for anything related to the user interface, from printing output to reading input from the
 * user
 */
public class Ui {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static final String HELLO_MESSAGE = "Hello, and welcome to my humble abode. \n"
            + "I'm supposed to help you but I'll be the judge of that. Anyway what do you want.";
    private static final String BYE_MESSAGE = "Thank you for that utter waste of time.\n"
            + "Can't wait to see you again...";

    /**
     * Prints out a string which gives a greeting to the user
     */
    public void displayGreeting() {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getGreetingResponseAsString(), HORIZONTAL_RULE);
    }

    /**
     * Get's bot's greeting as a String
     *
     * @return String representing bot's opening greeting
     */
    public String getGreetingResponseAsString() {
        return HELLO_MESSAGE;
    }

    /**
     * Prints out a string for when the program exits
     */
    public void displayExit() {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getExitResponseAsString(), HORIZONTAL_RULE);
    }

    /**
     * Get's bot's response to an exit command, as a String
     *
     * @return String representing bot's response
     */
    public String getExitResponseAsString() {
        return BYE_MESSAGE;
    }

    /**
     * Prints out tasks that have been found from a find query
     *
     * @param tasks TaskList containing found Tasks
     */
    public void printFindResult(TaskList tasks) {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getFindResultAsString(tasks), HORIZONTAL_RULE);
    }

    /**
     * Get's bot's response to a Find query, as a String
     *
     * @param tasks TaskList containing all the current tasks
     * @return String representing bot's response
     */
    public String getFindResultAsString(TaskList tasks) {
        return getPrintListResponseAsString(tasks, "Found these for you, couch potato");
    }

    /**
     * Prints out all items in the specified TaskList
     *
     * @param tasks TaskList consisting of items to be printed
     */
    public void printList(TaskList tasks) {
        System.out.printf("%s\n%s\n%s%n", HORIZONTAL_RULE, getPrintListResponseAsString(tasks), HORIZONTAL_RULE);
    }

    /**
     * Get's the bot's response to a Print list request, as a String
     *
     * @param tasks A TaskList with all the current tasks
     * @return String representing bot's response
     */
    public String getPrintListResponseAsString(TaskList tasks) {
        return getPrintListResponseAsString(tasks, "Here are all your burdens");
    }

    private String getPrintListResponseAsString(TaskList tasks, String message) {
        String res = message + "\n";

        for (int i = 1; i <= tasks.numTasks(); i++) {
            Task item = tasks.getTask(i - 1);
            String fullItem = i + ". " + item.toString();

            res += "\n" + fullItem;
        }

        return res;
    }

    /**
     * Prints out string for when the user has completed a task
     */
    public void displayCompleteTask() {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getCompleteTaskResponseAsString(), HORIZONTAL_RULE);
    }

    /**
     * Get's the bot's response to Completing Task, as a String
     *
     * @return String representing bot's response
     */
    public String getCompleteTaskResponseAsString() {
        return "Congratulations, you actually did something";
    }

    /**
     * Prints out a string for when the user adds a Task
     *
     * @param task Task which the user has added
     * @param size Number of items in the TaskList
     */
    public void displayAddTask(Task task, int size) {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getAddTaskResponseAsString(task, size), HORIZONTAL_RULE);
    }

    /**
     * Get's the bot's response to adding the Task as a String
     *
     * @param task Task that is added
     * @param size Number of tasks added thus far
     * @return String representing bot's response
     */
    public String getAddTaskResponseAsString(Task task, int size) {
        return "One more task added for you sire\n"
                + task.toString()
                + "\nYou have " + size + " tasks. Enjoy!";
    }

    /**
     * Prints out a string for when the user deletes a Task
     *
     * @param removedTask The removed Task
     * @param size Number of items remaining in the TaskList
     */
    public void displayDeleteTask(Task removedTask, int size) {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getDeleteTaskResponseAsString(removedTask, size),
                HORIZONTAL_RULE);
    }

    /**
     * Get's bot's response to receving a delete command, as a String
     *
     * @param removedTask Task to be removed
     * @param size Current number of tasks
     * @return String representing bot's response
     */
    public String getDeleteTaskResponseAsString(Task removedTask, int size) {
        return "Finished so soon? Fine I've removed the following task. Good day.\n"
                + "    " + removedTask
                + "\nYou have " + size + "tasks. Enjoy!\n";
    }

    /**
     * Prints out a string for when the user undoes a command
     */
    public void displayUndoCommand() {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getUndoCommandResponseAsString(), HORIZONTAL_RULE);
    }

    /**
     * Get bot's response to undoing a previous command, as a String
     *
     * @return String respresenting bot's response
     */
    public String getUndoCommandResponseAsString() {
        return "Why do I always have to clean up after you";
    }

    /**
     * Prints out a string for when there is an error loading the stored data
     */
    public void showLoadingError() {
        System.out.printf("%s\n%s\n%s", HORIZONTAL_RULE, getLoadingErrorAsString(), HORIZONTAL_RULE);
    }

    /**
     * Get's the loading error message as a String
     *
     * @return String representing laoding error message
     */
    public String getLoadingErrorAsString() {
        return "Something messed up with your hard disk file i don't really know what. Figure it out.";
    }
}
