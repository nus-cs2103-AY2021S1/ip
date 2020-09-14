package logic;

import command.Command;
import exception.DukeException;
import exception.InvalidSaveFileException;
import tasks.TaskList;

/**
 * Represents a logic.Duke object. Handles all the logic running
 * of logic.Duke
 */

public class Duke {

    private String fileLocation;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private String saveFileErrorMsg = "";

    /**
     * Constructor to create a Duke object.
     * @param fileLocation the location to store save files.
     */
    public Duke(String fileLocation) {
        this.fileLocation = fileLocation;
        this.ui = new Ui();
        this.storage = new Storage(fileLocation);
        try {
            this.taskList = new TaskList(this.storage.readFile());
        } catch (InvalidSaveFileException e) {
            saveFileErrorMsg = e.getMessage();
            taskList = new TaskList();
        }
        this.taskList.createDuplicateChecker();
    }

    public Duke() {
    }

    protected String getResponse(String input) {
        try {
            Command fullCommand = Parser.parse(input);
            String dukeResponse = fullCommand.execute(taskList, ui, storage);
            assert !dukeResponse.isBlank() : "Response from Duke is never empty!";
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    protected String showGreeting() {
        String result = "";
        if (!saveFileErrorMsg.isBlank()) {
            result += saveFileErrorMsg;
            result += "\n";
        }
        return result += ui.showGreeting();
    }
}
