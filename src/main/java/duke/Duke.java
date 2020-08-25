package duke;

import command.Command;
import command.ExitCommand;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    // file dir
    private static final String DIR = "data";
    
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private Parser parser;
    
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath, DIR);
            tasks = new TaskList(storage.loadData());
            parser = new Parser(tasks);
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            parser = new Parser(tasks);
            System.out.println("----- You have no tasks saved as of yet. Feel free to add tasks and I will track them for you");
        } catch (IOException e) {
            System.out.println("----- Something went wrong, please try again later");
        }
    }
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c instanceof ExitCommand;
                }
            } catch (IllegalArgumentException ex) {
                ui.showError("I can't help you with that request, try something else.");
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/serina.txt").run();
    }
}
