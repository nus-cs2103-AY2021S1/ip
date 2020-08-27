import duke.backend.Storage;
import duke.task.TaskList;
import duke.frontend.Parser;
import duke.frontend.Ui;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    
    private TaskList taskList;
    private Parser parser;
    
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
            parser = new Parser(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not created. " + e);
        }
    }
    
    public void run() {
        Ui.greet();
        this.parser.parseInputCommands();
        this.storage.save(this.taskList);
        Ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/saved-tasks.txt").run();
    }
}
