package main.java.seedu.duke;

import java.util.Scanner;

/**
 * Provides user interaction for Duke.
 */
public class Ui {
    private int lengthOfLine = 55;
    private String line;
    private Scanner sc;

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

    public void greet() {
        String logo =
                "                 .   ,\n" +
                        "               .';_.';\n" +
                        "                    _   \\\n" +
                        "             .     (.) (.)--._\n" +
                        "            .       \"   \"     `.\n" +
                        "           .                   :\n" +
                        "          .           `\"-.___.\"\n" +
                        "         .   .         `.\n" +
                        "         .    .  `.      .\n" +
                        ",,.      .      ` . `.    .\n" +
                        "\\W;      .         \"`     .\n" +
                        "   `--'    ,    __,..-   '  .\n" +
                        "          .   .'     `.   `' ;\n" +
                        "          `.   `,      `.  .'\n" +
                        "            \"._.'        `'";
        System.out.println("Hello from Moomin\n" + logo);
        System.out.println("I'm your personal assistant.");
    }
}
