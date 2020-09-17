import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Ui {

    protected static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final String LINE = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    private static final String INTRO = "Greetings, I am the Dragon Of The West, otherwise known as Iroh, "
            + "how may I serve you today? The date and time now is: ";
    private static Image userPic;
    private static Image dukePic;
    private static TextField userInput;
    private static VBox dialogContainer;
    private static MainWindow mainWindow;

    /**
     * Takes in inputs, and passes them to the Parser to perform actions.
     * @param nextInput The input in string form, to be parsed by the parser.
     * @param taskList The taskList that performs the appropriate functions based on the input.
     */
    public static void processInput(String nextInput, TaskList taskList) {

        String[] inputParts = nextInput.split(" ", 2);
        String inputPrefix = inputParts[0];
        String inputSuffix = inputParts.length == 1 ? "" : inputParts[1];
        try {
            Parser.handleInput(inputPrefix, inputSuffix, taskList, mainWindow);
        } catch (DukeException dukeException) {
            printWithLines(dukeException + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts the text from the dialogueContainer that will be used as the input.
     * @param taskList The taskList that performs the appropriate functions based on the input.
     */
    public static void startInput(TaskList taskList) {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userPic));
        processInput(input, taskList);
        userInput.clear();
    }

    /**
     * Displays the desired output with decorative lines in the GUI.
     * @param output The output to be displayed in string form.
     */
    public static void printWithLines(String output) {
        System.out.println(LINE + "\n" + output + LINE);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(LINE + "\n" + output + LINE, dukePic));
    }

    /**
     * Checks if a given string only has spaces, or if it's an empty string.
     * @param string The string to be checked for blanks.
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

    /**
     * Initialises the class by assigning objects to the static variables.
     * @param userInput The textField to be assigned.
     * @param dialogContainer The vBox to be assigned.
     * @param mainWindow The mainWindow to be assigned.
     * @param user The image to be assigned that will represent the user.
     * @param duke The image to be assigned that will represent the bot.
     */
    public static void initialise(TextField userInput,
                                  VBox dialogContainer,
                                  MainWindow mainWindow,
                                  Image user,
                                  Image duke) {
        Ui.userInput = userInput;
        Ui.dialogContainer = dialogContainer;
        Ui.mainWindow = mainWindow;
        userPic = user;
        dukePic = duke;
        printWithLines(INTRO + LocalDateTime.now().format(dateTimeFormat) + "\n");
    }

}
