package duke.ui;

import java.io.InputStream;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Handles input and output to the user.
 */
public class Ui {

    public static final String GOODBYE = "Bye. Try not to come again please...\n";
    private static final String LOGO = "   ___      _      _ __   _              \n"
            + "  /   \\    | |    | '_ \\ | |_     __ _   \n"
            + "  | - |    | |    | .__/ | ' \\   / _` |  \n"
            + "  |_|_|   _|_|_   |_|__  |_||_|  \\__,_|  \n"
            + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
            + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-' ";
    private static final String GREETING = "Hello, Alpha here... welcome to my help centre... again.\n"
            + "Would you like to explain what you want?\n";
    private static final String LINE_PREFIX = "    ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DIVIDER = "____________________________________________________________\n";
    private Scanner sc;
    private VBox dialogContainer;
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/enemy.png"));

    /**
     * Constructor for Ui object.
     * @param in input stream.
     * @param dialogContainer dialog container for Duke.
     */
    public Ui(InputStream in, VBox dialogContainer) {
        sc = new Scanner(in);
        this.dialogContainer = dialogContainer;
    }

    /**
     * Show welcome message.
     */
    public void showWelcomeMessage() {
        //print("Hello from\n" + LOGO);
        outputBlockToUser(GREETING);
    }

    public void showGoodbyeMessage() {
        outputBlockToUser(GOODBYE);
    }

    /**
     * Shows a message to user with indentation and divider blocks.
     */
    public void outputBlockToUser(String... message) {
        //System.out.print(LINE_PREFIX + DIVIDER.replace("\n", LINE_SEPARATOR));
        String output = "";
        for (String m : message) {
            // output += (LINE_PREFIX + m.replace("\n", LINE_SEPARATOR + LINE_PREFIX));
            output += (m.replace("\n", LINE_SEPARATOR));
        }
        print(output);
        //System.out.print(DIVIDER.replace("\n", LINE_SEPARATOR));
    }

    private void print(String input) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(input, dukeImage));
    }
}
