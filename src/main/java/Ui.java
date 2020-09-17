import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    private static final Scanner INPUT = new Scanner(System.in);
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static Image userPic;
    private static Image dukePic;
    private static TextField userInput;
    private static VBox dialogContainer;
    private static Duke dukeApp;

    /**
     * Takes in inputs, and passes them to the Parser to perform actions.
     */
    public static void processInput(String nextInput, TaskList taskList) {

        String[] inputParts = nextInput.split(" ", 2);
        String inputPrefix = inputParts[0];
        String inputSuffix = inputParts.length == 1 ? "" : inputParts[1];
        try {
            Parser.handleInput(inputPrefix, inputSuffix, taskList, dukeApp);
        } catch (DukeException dukeException) {
            printWithLines(dukeException + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startInput(TaskList taskList) {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userPic));
        processInput(input, taskList);
        userInput.clear();
    }

    /**
     * Prints the desired output with decorative lines.
     */
    public static void printWithLines(String output) {
        System.out.println(LINE + "\n" + output + LINE);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(LINE + "\n" + output + LINE, dukePic));
    }

    /**
     * Checks if a given string only has spaces, or if it's an empty string.
     */
    public static boolean isBlankString(String string) {
        if (string.length() != 0) {
            for (char c : string.toCharArray()) {
                if (c != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void initialise(TextField newUserInput,
                                  VBox newDialogContainer,
                                  Duke newDuke,
                                  Image user,
                                  Image duke) {
        userInput = newUserInput;
        dialogContainer = newDialogContainer;
        dukeApp = newDuke;
        userPic = user;
        dukePic = duke;
        printWithLines("Hello! My name is Duketh Puketh III, but you can call me\n" + LOGO
                + "\n How may I help you today? :)\n The date and time is now "
                + LocalDateTime.now().format(Duke.dateTimeFormat) + "\n");
    }
}
