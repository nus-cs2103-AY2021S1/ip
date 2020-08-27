import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Represents the user interface which prints messages to interact with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a Ui object.
     */
    public Ui () {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome logo.
     */
    public void printWelcome() {
        String logo =
                " ____                    _\n"
                        + "|  _ \\                  | |\n"
                        + "| |_| |___  _ _  __  ___| | ___  _\n"
                        + "| ___/  _ \\| | |/  |/ _   |/ _ \\| |\n"
                        + "| |  | |_|   |  _  | |_|  | |_|   |\n"
                        + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot\n\n";

        System.out.println(logo + "Hello! I'm PandaBot.\n" + "What can I do for you?\n");
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println(" ___________________________________________________");
    }

    /**
     * Returns a String representation of the user input.
     * 
     * @return a String representation of the user input
     */
    public String readCmd() {
        return scanner.nextLine();
    }

    /**
     * Prints an error message.
     * 
     * @param message the error message to be printed
     */
    public void printError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the bye message.
     */
    public void printOnExit() {
        System.out.println("Bye! Remember to finish the rest of your work! See you soon~");
        scanner.close();
    }


    /**
     * Prints the entire list of tasks.
     * 
     * @param tasks the TaskList to be printed
     */
    public void printOnList(TaskList tasks) {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("WOOTS! You don't have any tasks to do at the moment.");
        } else {
            System.out.println("These are the task(s) you have: ");
            int i = 0;
            for (Task t : tasks.getTaskList()) {
                if (t != null) {
                    System.out.println((i + 1) + ". " + t.toString());
                    i++;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Prints the done message.
     * 
     * @param task the task that is done
     */
    public void printOnDone(Task task) {
        System.out.println("Great! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the delete message.
     * 
     * @param task the Task to be deleted
     * @param numOfTasks the number of tasks in the list
     */
    public void printOnDelete(Task task, int numOfTasks) {
        System.out.println("Will do! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " task(s) in this list.");
    }

    /**
     * Prints the add task message.
     * 
     * @param task the Task to be printed
     * @param numOfTasks the number of tasks in the list
     */
    public void printOnAddTask(Task task, int numOfTasks) {
        System.out.println("Noted! I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + numOfTasks + " task(s) in this list.");
    }
    
    public void printOnFind(TaskList tasks) {
        System.out.println("Here are the matching tasks: ");
        int i = 0;
        for (Task t : tasks.getTaskList()) {
            if (t != null) {
                System.out.println((i + 1) + ". " + t.toString());
                i++;
            } else {
                break;
            }
        }   
    }
    
}
