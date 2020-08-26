import duke.command.Parser;
import duke.exception.InvalidUserCommandException;
import duke.exception.StorageException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a chatbot that maintains a task list for users.
 */
public class Duke {
    Storage storage;
    Ui ui;
    TaskList tasks;

    /**
     * Creates a new Duke object which will create and access the saved task list at the specified 
     * filepath.
     * 
     * @param filePath Specifies the where to create and/or access the saved task list.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = storage.load();
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }
    }

    /**
     * Runs the Duke program to help users maintain a task list.
     */
    private void run() {
        ui.loadTaskList(tasks);
        ui.showGreetings();
        
        while (!Ui.EXIT) {
            String userCommand = ui.readUserCommand();
            try {
                Parser.parseCommands(userCommand, ui, storage);
            } catch (InvalidUserCommandException e) {
                ui.showErrorMessage(e);
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke("data/tasks").run();
    }
}
