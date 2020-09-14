import java.io.IOException;



public class Bill {


    private static final String filePath = "./data/bill.txt";
    Bill() throws IOException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        ui.welcome_message();
        Storage storage = new Storage(filePath);
        Parser.initiateTaskList(taskList);
    }

    public String getResponse(String input) {
        try {
            return Parser.processInput(input);
        } catch (InvalidException e) {
            return Ui.getErrorMessage(e.getMessage());
        }
    }
}

