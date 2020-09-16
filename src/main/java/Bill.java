import java.io.IOException;
import java.util.List;


public class Bill {


    private static final String filePath = "./data/bill.txt";
    private static List<Task> listOfContents;
    private static Parser parse;
    private static boolean isEnded = false;

    Bill() throws IOException {
        Ui ui = new Ui(filePath);
        ui.welcome_message();
        Storage storage = new Storage(filePath);
        storage.checkFile(filePath);
        listOfContents = storage.getListOfContents();
        TaskList taskList = new TaskList(listOfContents);
        parse.initiateTaskList(taskList);
    }

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
    public static boolean isEnded() {
        return isEnded;
    }
}

