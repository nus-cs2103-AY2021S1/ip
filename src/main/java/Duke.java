package duke;

import duke.Task;
import duke.TaskType;
import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;
import duke.Command;
import duke.Parser;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Class contains main method of the Duke application.
 * Duke manages, stores and track tasks as specified by the user.
 */
public class Duke {
    private static String TASKS_PATHNAME = "data/tasks.txt";
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * main method of Duke
     * @param args
     */
    public static void main(String[] args) {
        new Duke(TASKS_PATHNAME).run();
    }

    /**
     * Constructor which takes in file path of the storage file.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}