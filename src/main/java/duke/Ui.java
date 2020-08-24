package duke;

import duke.task.*;

/**
 * In charge of interactions with the user.
 */
public class Ui {

    private static final String BORDER = "==========================================";

    /**
     * Prints out the greeting message to the user.
     */
    public void printGreeting() {
        System.out.println(BORDER
                + "\nHi, my name is Duke."
                + "\nWhat can I do for you today?"
                + "\n==========================================");
    }

    /**
     * Prints out the farewell message to the user.
     */
    public void printFarewell() {
        System.out.println("Thanks for chatting with me, see you soon!\n"
                + BORDER);
    }

    /**
     * Prints out the tasks that the user have made so far from the list.
     * @param tasks TaskList object of the chat bot.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints out the message to inform user that tasks have been saved onto tasks.csv file.
     */
    public void printSave() {
        System.out.println("List saved to hard disk at data/tasks.csv");
    }

    /**
     * Prints out the task that user has added into TaskList object and also the number of tasks
     * currently in the list.
     * @param task Task object user added.
     * @param taskNumber Integer number representing number of Task objects in the TaskList object.
     */
    public void printAdded(Task task, int taskNumber) {
        System.out.println(">" + "added: " + task + "<");
        System.out.println("You now have " + taskNumber + " task(s) in your list.");
    }

    /**
     * Prints out the task that user deleted and also the number of tasks
     * currently in the list.
     * @param task Task object user deleted.
     * @param taskNumber Integer number represent number of Task objects remaining in the TaskList object.
     */
    public void printDeleted(Task task, int taskNumber) {
        System.out.println("I have deleted this task: \n" + task);
        System.out.println("You now have " + taskNumber + " task(s) in your list.");
    }

    /**
     * Prints out the task that user has marked as done.
     * @param tasks Task object marked done by user.
     * @param taskNumber Integer number representing the index of the task
     *                   that was marked as done.
     */
    public void printDone(TaskList tasks, int taskNumber) {
        System.out.println("I have marked this task as done: \n"
                + tasks.getTask(taskNumber));
    }
}
