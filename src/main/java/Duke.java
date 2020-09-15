import java.io.IOException;

import duke.command.Command;
import duke.error.IncorrectFormat;
import duke.error.UnknownAction;
import duke.parts.Parser;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

/**
 * Represents Duke object which handles the terminal non gui duke bot.
 *
 * @author Roger Lim
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    Duke() {
        String filePath = "data/data.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isExit = false;
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    String getResponse(String input) throws UnknownAction, IOException, IncorrectFormat {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}
