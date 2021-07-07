package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.note.NoteList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Dukenizer program implements a Task Manager application. It performs task manipulations
 * based on user commands. It consists of a TaskList object to store your tasks, a Ui object
 * to handle user interactions and a Storage object to save and retrieve tasks in a list.
 */
public class Duke {

    private Storage storage;
    private Storage noteStorage;
    private NoteList notes;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Constructs a Duke object from a specified filePath. If a valid .txt file containing
     * a TaskList is found, it will be loaded. Otherwise, a new TaskList object is created
     * to store the tasks.
     *
     * @param taskStoragePath Relative filepath for storing task list from project source.
     */
    public Duke(String taskStoragePath, String noteStoragePath) {

        isExit = false;

        //initialize User interface
        ui = new Ui();

        //Initialize Storage location
        storage = new Storage(taskStoragePath);
        noteStorage = new Storage(noteStoragePath);

        //Initialize TaskList
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            storage.writeToFile(storage.getPath().toString(), "");
        }

        //Initialize notes
        try {
            notes = new NoteList(noteStorage.load());
        } catch (DukeException e) {
            notes = new NoteList();
            noteStorage.writeToFile(noteStorage.getPath().toString(), "");
        }

    }


    /**
     * Returns Greeting message to user.
     *
     * @return Greeting message.
     */
    public String getGreeting() {
        return ui.printGreeting();
    }

    /**
     * Returns a String response to the user's input.
     *
     * @param input User input.
     * @return String response from program.
     */
    public String getResponse(String input) {

        String output = "";

        //execute correct command
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            output += c.execute(tasks, notes, ui, storage, noteStorage);
        } catch (DukeException e) {
            output += ui.showError(e.getMessage());
        }
        return output;
    }

    /**
     * Returns the exit status of Dukenizer.
     *
     * @return Exit status.
     */
    public boolean getExitStatus() {
        return this.isExit;
    }
}
