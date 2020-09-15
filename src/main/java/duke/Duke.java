package duke;

import duke.commands.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Main class to initialise and run the chatbot.
 */
public class Duke {

    static final String DIR_NAME = "data";
    static final String FILE_NAME = "duke_data.txt";

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    /**
     * Creates a Duke and initializes the individual components.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(DIR_NAME, FILE_NAME);
        taskList = storage.init(ui);
        ui.welcome();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Main running loop (non-GUI)
     */
    public void run() {
        boolean keepGoing = true;
        Command command;
        String input;
        while (keepGoing) {
            input = ui.readInput();
            try {
                command = parser.processInput(input);
                keepGoing = command.run(taskList, storage, ui);
            } catch (DukeException de) {
                ui.writeOutput(de.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.processInput(input);
            assert command != null : "Parser returned null";

            return command.runGUI(taskList, storage, ui);
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }
}
