package duke;

import java.io.IOException;

import command.Command;
import exception.DukeException;

/**
 * Represents a <code>Duke</code> object which is a ChatBot that can keep track of several kinds of tasks.
 */
public class Duke {
    private static final String DATA_DIRECTORY = "data";
    private static final String SAVED_FILE_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_DIRECTORY, SAVED_FILE_PATH);
        try {
            tasks = new TaskList(storage.loadSavedFile());
        } catch (IOException e) {
            ui.sayException(e);
        }
    }

//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    public String getResponse(String input) {
//        return "Duke heard: " + input;
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String toPrint = "";
        try {
            toPrint += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            toPrint += ui.sayException(e);
        }
        return toPrint;
    }

    /**
     * Runs the Duke.
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the <code>Duke</code> object.
     */
    public void run() {
        ui.hello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("------")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.sayException(e);
            } finally {
                ui.showLine();
            }
        }
    }
}

