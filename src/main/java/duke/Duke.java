package duke;

import com.sun.source.util.TaskListener;
import duke.Command.Command;
import duke.task.Task;
import javafx.application.Application;

import java.io.IOException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Duke class is the main class and drives the program
 */

public class Duke{

    private duke.Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a new driver system
//     * @param filePath  the file path of the schedule list
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/duke/text/todo.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(ui, tasks, storage);
        } catch (DukeException | IOException e) {
            output = (e.getMessage());
        }
        return output;
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

}

