package duke.ui;

import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Scanner to take in user's input.
     */
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Greeting message to the user.
     */
    public void greetUser() {
        String logo = "Duke Melvin";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
    }

    /**
     * Goodbye message to the user.
     */
    public void sayGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the items within the list.
     *
     * @param taskList list containing all the tasks.
     */
    public void printList(TaskList taskList) {
        for (int x = 0; x < taskList.listSize(); x++) {
            System.out.println(x + 1 + ":" + taskList.getTask(x).toString());
        }
    }

    /**
     * Prints the size of the list.
     *
     * @param taskList list containing all the tasks.
     */
    public void printListCount(TaskList taskList) {
        System.out.format("Now you have %d tasks in the list\n", taskList.listSize());
    }

    /**
     * @return string containing the user's input.
     */
    public String readCommand() {
        return in.nextLine();
    }


    /**
     * Prints the response to user's command.
     *
     * @param response string to be output.
     */
    public void printResponse(String response) {
        System.out.println(response);
    }
}
