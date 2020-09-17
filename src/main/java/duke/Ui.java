package duke;

import java.util.Scanner;

/**
 * Provides user interaction for Duke.
 */
public class Ui {
    public static final String ADD_MSG = "Got it. I've added this task:";
    public static final String EMPTY_TASK_LIST_MSG = "You don't have any tasks.";
    public static final String DELETE_MSG = "Noted. I've removed this task:";
    public static final String DONE_MSG = "Nice! I've marked this task as done:";
    public static final String INVALID_TASK_NO_MSG = "Please provide a correct task number.";
    public static final String LIST_TASK_MSG = "Here are the tasks in your list:";

    private int lengthOfLine = 55;
    private String line;
    private Scanner sc;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.line = getHorizontalLine();
        this.sc = new Scanner(System.in);
    }

    /**
     * Outputs a horizontal line.
     * @return An horizontal line in a string.
     */
    public String getHorizontalLine() {
        String line = "";
        for (int i = 0; i < lengthOfLine; i++) {
            line = line + "-";
        }
        return line;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void closeScanner() {
        sc.close();
    }

    public void showLine() {
        System.out.println(line);
    }

    /**
     * Reads command line inputs from the user.
     * @return The user input.
     * @throws DukeException If there is no more input when user press 'Enter'.
     */
    public String readCommand() throws DukeException {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            throw new DukeException("There is no next line");
        }
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the graphical representation of Duke.
     */
    public void greet() {
        String logo =
                "                 .   ,\n"
                        + "               .';_.';\n"
                        + "                    _   \\\n"
                        + "             .     (.) (.)--._\n"
                        + "            .       \"   \"     `.\n"
                        + "           .                   :\n"
                        + "          .           `\"-.___.\"\n"
                        + "         .   .         `.\n"
                        + "         .    .  `.      .\n"
                        + ",,.      .      ` . `.    .\n"
                        + "\\W;      .         \"`     .\n"
                        + "   `--'    ,    __,..-   '  .\n"
                        + "          .   .'     `.   `' ;\n"
                        + "          `.   `,      `.  .'\n"
                        + "            \"._.'        `'";
        System.out.println("Hello from Moomin\n" + logo);
        System.out.println("I'm your personal assistant.");
    }
}
