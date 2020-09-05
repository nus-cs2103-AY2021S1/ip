package duke.component;

import java.util.Scanner;

/**
 * The class that receive user's input and print corresponding responses on the screen.
 */
public class Ui {
    public static final String greeting = "Hello! I'm Duke\n" + "\t What can I do for you?";
    private static final String divider = "\t____________________________________________________________\n";
    private Scanner sc;

    /**
     * Initializes a Ui object with a new scanner
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays loading error message on the screen
     *
     * @param e the exception describing what errors occured
     */
    public void showLoadingError(DukeException e) {
        giveResponse(e.getMessage());
    }

    /**
     * Prints out the response on the screen
     */
    public String giveResponse(String response) {
        String formattedResponse = divider + "\t " + response + "\n" + divider;
        System.out.println(formattedResponse);
        return response;
    }

    /**
     * Displays the task list given
     *
     * @param taskList the task list to be displayed
     */
    public String displayList(TaskList taskList, String description) {

        String list = description + "\n";
        for (int i = 1; i <= taskList.size(); i++) {
            list += "\t " + i + ". " + taskList.get(i - 1) + "\n";
        }

        //remove the extra "\n"
        if (!list.isEmpty()) {
            list = list.substring(0, list.length() - 1);
        }

        giveResponse(list);
        return list;
    }

    /**
     * Gets the next input.
     *
     * @return the next input in String format
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints out the welcome message.
     */
    public void welcome() {
        giveResponse(Ui.greeting);
    }

}
