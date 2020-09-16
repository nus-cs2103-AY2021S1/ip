package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Interacts with the user and answers user commands
 * Prints out answers in the terminal
 */

public class Ui {
    protected final Scanner input;
    protected final PrintStream output;
    private static final String Message_GREETING
                    = "Hello :))!  I'm your daily manager, Ka To! Welcome Back! \n"
                    + "how could I serve you now? \n"
                    + "You could ask me any question if you like! \n"
                    + "____________________________________________________________";
    private static final String Message_Bye = "Ka To: \n"
                    + "I am happy to serve you. See you soon!";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.output = out;
    }

    public void printGreet() {
        System.out.println(Message_GREETING);
    }

    public void printBye() {
        System.out.println(Message_Bye);
    }

    public String getInput() {
        return input.nextLine();
    }

    /**
     * Prints out the food left: "apple"
     */

    public void replyFood() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Only an apple pie");
    }

    /**
     * Prints out the allowance left in the account;
     */

    public void replyAllowance() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("I have checked, it is 2000000 SGD");
    }

    /**
     * Prints out the current task list
     *
     * @param taskList Task List to be read and printed
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("This is your task list: \n");
        for (int i = 1; i < taskList.taskCounts + 1; i++) {
            Task task = taskList.tasks.get(i - 1);
            System.out.println("" + i + "." + task);
        }

    }

    /**
     * Prints out the message of empty list
     */
    public void printEmptyList() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, the task list is empty");
    }

    /**
     * Prints out the current task list
     *
     * @param added Task added successfully
     * @param list  Task List to be added into
     */
    public void addTaskSuccessful(Task added, TaskList list) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Congratulations! This is added! \n");
        System.out.println(added);
        System.out.println(" You have " + list.getTaskCounts() + " tasks in the list");
    }

    /**
     * Deletes the task from the list
     *
     * @param deleted Task deleted successfully
     * @param list    Task List to be deleted from
     */
    public void deleteTaskSuccessful(Task deleted, TaskList list) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Kay, this task now is deleted: \n");
        System.out.println(deleted);
        System.out.println("Now you have " + list.getTaskCounts() + " tasks in the list");
    }

    /**
     * Marks the task as done
     *
     * @param done Task to be changed status into Done
     */
    public void markTaskDone(Task done) {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Congratulations! This is now marked as done!\n");
        System.out.println(done);
    }

    public void failToMarkDone() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify the task index to be marked");
    }

    /**
     * Warns the user to include task index when finding task to mark done
     */
    public void failToFindTask() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please ensure the task index to be marked is correct");
    }

    /**
     * Warns the user to include task index when deleting task
     */
    public void failToDelete() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify the task index to be deleted");
    }

    /**
     * Warns the user to include task details
     */
    public void failToFindDetails() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify your task");
    }

    /**
     * Warns the user to include task intended date
     */

    public void failToFindTime() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Oops, please specify the time for the task");
    }

    /**
     * Warns the user to key in correct commands
     */

    public void failToUnderstand() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Sorry, I could not answer that ..");
    }

    public void findDuplicates() {
        System.out.println("Ka To:");
        System.out.println(" ");
        System.out.println("Sorry, this task is already added..");
    }

    /**
     * Prints the relevant task to the key word
     *
     * @param keyWord String keyword to be searched
     * @param taskList TaskList taskList to be searched within
     */

    public void printFound(String keyWord, TaskList taskList){
        for (int i = 1; i < taskList.taskCounts + 1; i++) {
            Task task = taskList.tasks.get(i - 1);
            if (task.toString().contains(keyWord)) {
                System.out.println("" + i + "." + task);
            }
        }
    }
}