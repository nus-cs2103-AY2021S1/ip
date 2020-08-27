package rogue;

import rogue.storage.Storage;
import rogue.model.task.TaskList;
import rogue.ui.Ui;

import rogue.logic.directives.Executable;
import rogue.logic.parser.Parser;
import rogue.logic.Report;

import rogue.commons.exceptions.IncorrectArgumentException;
import rogue.logic.parser.exceptions.UnknownCommandException;
import rogue.storage.exceptions.StorageException;

/**
 * Main driver of {@code Rogue}. Consists of three main elements: the {@code Storage},
 * the {@code TaskList}, and the {@code Ui}.
 */
public class Rogue {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs {@code Rogue}.
     * Loads data from the file at the given path.
     * The data is used to recreate the tasks if it is successfully parsed.
     * Otherwise, an empty {@code TaskList} is used instead.
     *
     * @param filePath The relative or absolute path where persistent information is stored.
     */
    public Rogue(String filePath) {
        storage = Storage.init(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
        ui = new Ui();
    }

    /**
     * Runs {@code Rogue}.
     * Continues to prompt the user for more inputs, parsing and executing them appropriately,
     * until the user initiates the exit sequence (i.e. {@code Action.EXIT}).
     */
    public void start() {
        ui.greet();
        boolean isExit = false;
        do {
            String fullCommand = ui.readCommand();
            try {
                Executable e = Parser.createExe(fullCommand);
                Report r = e.execute(storage, tasks, ui);
                ui.print(r);
                isExit = r.isExit();
            } catch (UnknownCommandException | IncorrectArgumentException | StorageException e) {
                ui.print(e.getMessage());
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        new Rogue("./data/tasks.txt").start();
    }
}
