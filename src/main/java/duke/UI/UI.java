package duke.UI;

import java.util.Scanner;

/**
 * The class UI denotes the interface user will use.
 *
 * @author Alvin Chee
 */
public class UI {

    private Scanner sc = new Scanner(System.in);
    /**
     * Prints out the bot's introduction.
     */
    public void showIntro() {
        System.out.println("\tHi handsome! My name is Duck. What can I do for you?");
    }

    /**
     * Reads command given by user.
     */
    public String readCommand() {
        String task = "";
        if (sc.hasNextLine()) {
            task = sc.nextLine();
        }
        return task;
    }
}
