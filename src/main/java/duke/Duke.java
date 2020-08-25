package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private final UI ui;
    private final TaskList taskList;
    private final Storage storage;

    private Duke(UI ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    public static void newDuke(Path filePath) {

        UI ui = new UI();
        TaskList taskList = new TaskList();
        Storage storage;
        
        try {
            storage = Storage.loadStorage(filePath);
            taskList.loadDataFromStorage(filePath);
            new Duke(ui, taskList, storage).run();
        }  catch (IOException e) {
            ui.printToConsole("File System Error");
        } catch (DukeException e) {
            ui.printToConsole("Stored file format is invalid\n" + e.getMessage());
        }
    }
    
    public void run() {
        ui.greet();

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

    public static void main(String[] args) {
        Path filePath = Paths.get("data","duke.txt");
        Duke.newDuke(filePath);
    }
}
