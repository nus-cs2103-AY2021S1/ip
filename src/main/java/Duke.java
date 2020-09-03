import duke.TaskList;
import duke.Ui;
import duke.Parser;
import duke.NoResponseException;
import duke.EmptyInputException;
import duke.Storage;

import java.io.IOException;

public class Duke {
    protected Storage storage;
    protected Ui ui;
    private TaskList tasks;

    /**
     * Returns a Duke which has a reference to storage, tasks and user response.
     * It is a constructor that takes in the arguments of the file path and file name (data and duke.txt)
     *
     * @return duke;
     */
    public Duke(String path, String fileName) {
        this.storage = new Storage(path, fileName);
        this.tasks = storage.load();
        this.ui = new Ui(this.tasks);
    }

    /**
     * Returns a string response according to the input by the user
     * The response comes from user interaction class
     * When the input is bye, the storage class will write all the newly added tasks into the duke.txt
     *
     * @return String which a response from ui and will be shown on the GUI;
     */
    protected String getResponse(String input) throws EmptyInputException, NoResponseException, IOException {
        Parser parser = new Parser(this.ui, this.tasks);
        String response = parser.parse(input);
        if (input.equals("bye")) {
            storage.storeFile(this.tasks);
        }
        return response;
    }



}
