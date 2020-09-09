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
            String deletedMessage = tasks.deleteTaskAndGetMessage(parser.getSplitUserInput());
            storage.removeFromFile(tasks.getDeletedTaskIndex());
            return deletedMessage;
        case "conquer":
            String conqueredMessage = tasks.conquerTaskAndGetMessage(parser.getSplitUserInput());
            storage.overwriteInFile(tasks.getConqueredTaskIndex());
            return conqueredMessage;
        case "todo":
            String todoMessage = tasks.addTodoAndGetMessage(parser.getSplitUserInput());
            storage.writeToFile(tasks.getAddedTask(), parser.getSplitUserInput());
            return todoMessage;
        case "deadline":
            String deadlineMessage = tasks.addDeadlineAndGetMessage(parser.getSplitUserInput());
            storage.writeToFile(tasks.getAddedTask(), parser.getSplitUserInput());
            return deadlineMessage;
        case "event":
            String eventMessage = tasks.addEventAndGetMessage(parser.getSplitUserInput());
            storage.writeToFile(tasks.getAddedTask(), parser.getSplitUserInput());
            return eventMessage;
        default:
            return Ui.printWrongInputErrorMessage();
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
