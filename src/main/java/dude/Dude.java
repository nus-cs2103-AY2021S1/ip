package dude;

import java.io.FileNotFoundException;

import dude.command.Command;
import dude.ui.DialogWrapper;
import dude.ui.Ui;
import dude.util.CommandException;
import dude.util.CorruptedFileException;
import dude.util.InvalidArgumentException;
import dude.util.InvalidCommandException;
import dude.util.Storage;
import dude.util.TaskList;

/**
 * The main class containing the key functionality of the bot.
 */
public class Dude {
    private static final String FILEPATH = "./data/tasks.txt";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructor for Dude class.
     */
    public Dude() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.read());
        } catch (FileNotFoundException e) {
            ui.showFileNotFound();
            tasks = new TaskList();
        } catch (CorruptedFileException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Get a response from Dude.
     *
     * @param input input from user.
     * @return DialogWrapper based on the command executed.
     */
    public DialogWrapper getResponse(String input) {
        try {
            Command inputCommand = Parser.parse(input);
            inputCommand.execute(tasks, ui, storage);
            return new DialogWrapper(ui.getMessage(), inputCommand.getExitStatus(), false);
        } catch (CommandException | InvalidArgumentException | InvalidCommandException e) {
            return new DialogWrapper(e.getMessage(), false, true);
        }
    }

    /**
     * Starts Dude bot by accepting user input.
     */
    public void run() {
        ui.greet();
        boolean willExit = false;
        while (!willExit) {
            try {
                String input = ui.readCommand();
                Command inputCommand = Parser.parse(input);
                inputCommand.execute(tasks, ui , storage);
                willExit = inputCommand.getExitStatus();
                ui.showMessage();
            } catch (CommandException | InvalidArgumentException | InvalidCommandException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
