package duke.io;

import java.util.Scanner;

/**
 * Class for asking for and handling all inputs for user.
 */
public class InputHandler {

    Scanner scanner;

    /**
     * Initializes a new <code>InputHandler</code>.
     */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Requests for next input from user.
     * @return Input line from user.
     */
    public String input() {
        return this.scanner.nextLine();
    }
}
