package duke;

import duke.commands.Command;
import duke.exception.InvalidCommand;
import duke.parser.Parser;

/**
 * Represents bot that responds with appropriate functions calls to process user's input.
 *
 */
public class Duke {
    private Storage listStorage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Creates and initialises a bot with the a storage space to store the list, and UI object needed.
     *
     * @param fileName Storage filename.
     */
    private Duke(String fileName) {
        this.listStorage = new Storage(fileName);
        this.taskList = new TaskList();
        this.ui = new Ui(this.listStorage, this.taskList);
    }

    /**
     * Creates a Duke object with dataFile.txt as the storage file name.
     *
     */
    public Duke() {
        this("dataFile.txt");
    }

    /**
     * Returns appropriate response to user's input.
     *
     * @param input User's input.
     * @return Message response to user's input.
     */
    protected String getResponse(String input) {
        String uiMessage = "";
        try {
            Command c = Parser.parse(input);
            assert c != null : "Something went wrong!";
            if (c != null) {
                uiMessage = c.execute(this.ui, this.listStorage, this.taskList);
            }
        } catch (InvalidCommand ex) {
            uiMessage = Ui.commandError(ex);
        }
        return uiMessage;
    }

    /**
     * Returns welcome message when bot has started.
     *
     * @return Welcome message.
     */
    public String saysWelcome() {
        return this.ui.printWelcome();
    }

    /**
     * Returns storage loading messages when bot has started.
     *
     * @return Storage loading messages.
     */
    public String loadStorage() {
        String uiMessage = "";
        try {
            uiMessage = this.ui.loadStorage(this.listStorage.loadData(this.taskList));
        } catch (InvalidCommand ex) {
            Ui.commandError(ex);
        }
        return uiMessage;
    }

    /**
     * Returns storage successfully loaded message.
     *
     * @return Storage successfully loaded message.
     */
    public String loadedStorage() {
        return this.ui.loadFile();
    }

}
