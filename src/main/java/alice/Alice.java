package alice;

import alice.command.Command;
import alice.command.InvalidCommandException;

import alice.parser.Parser;
import alice.ui.Ui;

import alice.storage.Storage;
import alice.task.TaskList;

public class Alice {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Alice(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Read stored data
            tasks = new TaskList(storage.load());
            ui.displayLoadSuccess();
        } catch (AliceException ex) {
            tasks = new TaskList();
            ui.displayLoadError(filePath);
        }
    }

    public void run() {
        ui.displayWelcomeMsg();
        ui.displayLine();
        boolean isGoodbye = false;

        while (!isGoodbye) {
            try {
                String fullCommand = ui.readUserInput();
                ui.displayLine();
                Command c = Parser.parseCommand(fullCommand);
                c.process(tasks, ui, storage);
                isGoodbye = c.isExitCommand();
            } catch (InvalidCommandException ex) {
                ui.displayWarning(ex.getMessage());
            } catch (AliceException ex) {
                ui.displayError(ex.getMessage());
            } finally {
                ui.displayLine();
            }
        }
    }

    public static void main(String[] args) {
        new Alice("data/tasks.txt").run();
    }
}
