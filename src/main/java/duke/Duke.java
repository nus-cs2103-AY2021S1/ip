package duke;

import duke.commands.Command;
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
    public Duke(String fileName) {
        this.listStorage = new Storage(fileName);
        this.taskList = new TaskList();
        this.ui = new Ui(this.listStorage, this.taskList);
    }

    /**
     * Processes user's input through parsing and executing relevant commands.
     *
     */
    public void run() {
        this.ui.printWelcome();
        this.listStorage.loadData(this.taskList);
        this.ui.loadFile();
        boolean isExit = false;
        while (!isExit) {
            String command = this.ui.getInput();
            Command c = Parser.parse(command);
            if (c != null) {
                c.execute(this.ui, this.listStorage, this.taskList);
                isExit = c.canExit();
            }
        }
    }

    /**
     * Creates a new bot and start to run the bot to accept user's commands.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("dataFile.txt").run();
    }
}
