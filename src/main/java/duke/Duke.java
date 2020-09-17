package duke;

import duke.task.Task;

import java.io.IOException;

import java.util.ArrayList;

/**
 * Represents the duke object which is a Personal Assistant Chatbot
 * that helps a person keep track of various things.
 */
public class Duke {
    public Ui ui;
    private Storage storage;
    private Parser parser;
    public TaskList tasks;

    private final static String DATA_FILE_PATH = "src/main/data/dukeData.txt";

    public Duke() {
        ui = new Ui();

        System.out.println("Initializing...");

        storage = new Storage(DATA_FILE_PATH);
        parser = new Parser();
        try {
            ArrayList<Task> existingTasks = storage.loadExistingData();
            assert existingTasks != null;
            tasks = new TaskList(existingTasks);
            ui.printWelcome(tasks);
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            new Ui("An error occurred while retrieving the data.").printMessage();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input, tasks, storage, ui);
    }
}