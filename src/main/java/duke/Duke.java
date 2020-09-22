package duke;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.task.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;



public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    private boolean isFinished = false;


    public String getResponse(String echo) {
        Command command = null;
        try {
            command = Parser.parseCommand(echo, tasks);
        } catch (DukeException | ParseException | DateTimeParseException e) {
            return e.getMessage();
        }
        String output = command.execute(tasks);
        // System.out.println(tasks);
        // storage.save(tasks);
        return output;
    }


}
