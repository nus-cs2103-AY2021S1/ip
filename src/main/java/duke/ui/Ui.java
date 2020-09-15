package duke.ui;

import java.util.Scanner;

import duke.tasks.TaskList;

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
     * Prints goodbye message to the user.
     */
    public String sayGoodBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints all the items within the list.
     *
     * @param taskList list containing all the tasks.
     */
    public String printList(TaskList taskList) {
        String s = "\n";
        for (int x = 0; x < taskList.listSize(); x++) {
            s = s + (int) (x + 1) + ":" + taskList.getTask(x).toString() + "\n";
        }
        return s;
    }

    /**
     * Prints the size of the list.
     *
     * @param taskList list containing all the tasks.
     */
    public String printListCount(TaskList taskList) {
        return "Now you have " + taskList.listSize() + " tasks in the list";
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
    public String printResponse(String response) {
        return response;
    }
}
