package duke.io;

import java.util.Scanner;

/**
 * Class for asking for and handling all inputs for user.
 */
public class InputHandler {

    Scanner sc;

    /**
     * Initializes a new <code>InputHandler</code>.
     */
    public InputHandler() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Requests for next input from user.
     * @return Input line from user.
     */
    public String input() {
        return this.sc.nextLine();
    }
}
