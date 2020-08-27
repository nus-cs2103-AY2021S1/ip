package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.task.TaskList;

/**
 * A personal assistant chat bot to help users keep track of tasks.
 */
public class Duke {
    
    private final UI ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Path filePath;

    /**
     * Creates a new instance of the Duke chat bot.
     * @param filePath File path where Duke should read from and save to.
     */
    public Duke(Path filePath) {
        this.filePath = filePath;
        this.ui = new UI();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
    }
    
    private void run() {

        ui.greet();

        // Try to load from storage.
        try {
            storage.loadFromStorage();
            taskList.loadDataFromStorage(filePath);
        } catch (DukeException e) {
            ui.printToConsole(e.getMessage());
            return;
        }
        
        while (ui.hasNextCommand()) {
            String nextCommand = ui.readCommand();
            Command command = Parser.parse(nextCommand);
            try {
                command.execute(storage, taskList, ui);
            } catch (DukeException e) {
                ui.printToConsole(e.getMessage());
            } catch (IOException e) {
                ui.printToConsole("Error: Task could not be saved.");
            }
        }
    }

    /**
     * Main method.
     * @param args Arguments passed in to application.
     */
    public static void main(String[] args) {
        Path filePath = Paths.get("data", "duke.txt");
        new Duke(filePath).run();
    }
}
