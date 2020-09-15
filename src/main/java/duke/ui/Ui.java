package duke.ui;

import java.util.Scanner;

import duke.tasks.TaskList;

/**
 * Deals with handling interactions with the user.
 */
public class Ui {


    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Prints goodbye message to the user.
     */
    public String sayGoodBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out all the Task within the TaskList.
     *
     * @param taskList TaskList that contains the Tasks.
     */
    public String printList(TaskList taskList) {
        String s = "\n";
        for (int x = 0; x < taskList.listSize(); x++) {
            s = s + (int) (x + 1) + ":" + taskList.getTaskAtIndex(x).toString() + "\n";
        }
        return s;
    }

    /**
     * Prints the size of the TaskList.
     *
     * @param taskList TaskList that contains the Tasks.
     */
    public String printListCount(TaskList taskList) {
        return "Now you have " + taskList.listSize() + " tasks in the list";
    }

    /**
     * Prints the response to user's command.
     *
     * @param response String to be printed to the user.
     */
    public String printResponse(String response) {
        return response;
    }
}
