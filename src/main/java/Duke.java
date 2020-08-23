import java.io.File;
import java.io.IOException;

/**
 * Class which represents the duke bot
 * @author vanGoghhh
 */

public class Duke {

    /**
     * Initiates the bot
     * @throws DukeException
     * @throws IOException
     */
    protected static void startBot() throws DukeException, IOException {
        Storage storage = new Storage("data" + File.separator +  "duke.txt");
        TaskList dukeTaskList = new TaskList(storage.loadData());
        UI dukeUI = new UI();
        Parser dukeParser = new Parser();

        dukeUI.greetUser();

        boolean isExit = false;

        while (!isExit) {
            String userCommand = dukeUI.readCommand();
            Command cmd = dukeParser.parseCommand(userCommand);
            cmd.execute(dukeTaskList, dukeUI);
            isExit = cmd.isExit();
            storage.writeData(dukeTaskList);
        }
    }

    public static void main(String[] args) throws Exception {
        startBot();
    }
}