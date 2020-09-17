import java.io.IOException;
import java.util.List;

/**
 * Represents Bill class, which is the main control for the Bill application such as the logic of Bill.
 */
public class Bill {


    private static final String filePath = "./data/bill.txt";
    private static List<Task> listOfContents;
    private static Parser parse;
    private static boolean isEnded = false;

    /**
     * Initialize a Bill instance.
     */
    Bill() throws IOException {
        Ui ui = new Ui(filePath);
        ui.welcome_message();
        Storage storage = new Storage(filePath);
        storage.checkFile(filePath);
        listOfContents = storage.getListOfContents();
        TaskList taskList = new TaskList(listOfContents);
        parse.initiateTaskList(taskList);
    }

    /**
     * Generates a response according to the user command.
     *
     * @param input User command.
     * @return Response string.
     */
    public String getResponse(String input) {
        try {
            String response = parse.processInput(input);
            if (Parser.isEnded()) {
                this.isEnded = true;
            }
            return response;
        } catch (InvalidException e) {
            return Ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Checks if the application window should be close by checking
     * whether the users have choose to close it using bye command
     *
     * @return true if the application window can be closed, false otherwise.
     */
    public static boolean isEnded() {
        return isEnded;
    }
}

