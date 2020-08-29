import java.io.File;
import java.io.IOException;

/**
 * Class which represents the duke bot.
 * @author vanGoghhh
 */

public class Duke {

    private Storage dukeStorage;
    private TaskList dukeTaskList;
    private UI dukeUI;
    private Parser dukeParser;

    /**
     * Initiates the bot.
     * @throws DukeException
     * @throws IOException
     */
    public Duke() throws IOException {
        dukeStorage = new Storage("data" + File.separator + "duke.txt");
        dukeTaskList = new TaskList(dukeStorage.loadData());
        dukeUI = new UI();
        dukeParser = new Parser();

        //dukeUI.greetUser();

//        boolean isExit = false;
//
//        while (!isExit) {
//            String userCommand = dukeUI.readCommand();
//            Command cmd = dukeParser.parseCommand(userCommand);
//            cmd.execute(dukeTaskList, dukeUI);
//            ixsExit = cmd.isExit();
//            storage.writeData(dukeTaskList);
//        }
    }

    public String getResponse(String input) {
        try {
            String userCommand = input;
            Command cmd = dukeParser.parseCommand(userCommand);
            String dukeResponse = cmd.execute(dukeTaskList, dukeUI);
            dukeStorage.writeData(dukeTaskList);
            return dukeResponse;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}