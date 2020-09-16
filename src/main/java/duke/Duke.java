package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Duke can take in command and manage tasks.
 */
public class Duke {

    public Ui ui;
    private boolean canClose = false;

    /**
     * Construct a duke object.
     */
    public Duke(){
        ui = new Ui();
        TaskList taskList = Storage.read();
        Parser.setTaskList(taskList);
    }

    /**
     * The main method in Duke class.
     * Create an Ui object to print the lists of tasks.
     * @param args Unused.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        TaskList taskList = Storage.read();
        Parser.setTaskList(taskList);
        Ui ui = new Ui();
        ui.run();
    }

    /**
     * Get the response.
     * @param input User input
     * @return The response we get from the parser
     */
    public String getResponse(String input) {
        try {
            String response = Parser.processCommand(input);
            Storage.saveDataToFile(Parser.taskList);
            if (Parser.canClose()) {
                this.canClose = true;
            }
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     *
     * @return
     */
    public boolean canClose() {
        return canClose;
    }
}
