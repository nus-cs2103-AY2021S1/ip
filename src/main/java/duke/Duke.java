package duke;

import java.nio.file.Path;
import javafx.scene.image.Image;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Represents a Duke app.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    public String getResponse(String input) {
        parser.parse(input);
        switch(parser.getSplitUserInput()[0].toLowerCase()) {
            case "assist":
                return Ui.assist();
            case "dismiss":
                new Timer().schedule(new TimerTask() {
                    public void run () { System.exit(0); }
                }, 1000);
                return Ui.dismiss();
            case "scroll":
                return tasks.printAllTasks();
            case "find":
                return tasks.returnSearchedTask(parser.getSplitUserInput());
            case "delete":
                int deleteIndex = tasks.getDeleteTaskIndex(parser.getSplitUserInput());
                storage.removeFromFile(deleteIndex);
                return tasks.getDeletedTaskMessage(parser.getSplitUserInput());
            case "conquer":
                int conquerIndex = tasks.getConquerTaskIndex(parser.getSplitUserInput());
                storage.overwriteInFile(conquerIndex);
                return tasks.getConquerTaskMessage(parser.getSplitUserInput());
            default:
                Task t = tasks.addTask(parser.getSanitisedUserInput());
                storage.writeToFile(t, parser.getSanitisedUserInput());
                return tasks.getAddedTaskMessage(parser.getSanitisedUserInput());
        }
    }
    
    public Duke(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }
    
    public Duke() {
        this(java.nio.file.Paths.get(System.getProperty("user.dir"), "storage", "data.txt"));
    }
}
