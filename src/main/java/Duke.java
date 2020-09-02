import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a chat-bot named Duke.
 */
public class Duke  {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Duke Chat-bot.
     */
    public Duke() {
        this.storage = new Storage("data", "data/duke.txt");
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

//    /**
//     * Runs the main logic of the Chat-bot.
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    /**
     *
     * @param input user's input
     * @return reply by Duke
     */
    String getResponse(String input) {

        try {
            Command c = Parser.parse(input);
            return "Duke:\n" + c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
