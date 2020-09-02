package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Duke main class
 */
public class Duke{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor
     *
     *
     */
    public Duke() {
        storage = new Storage("src/main/data.txt");
        tasks = new TaskList(storage.getTasks());
        ui = new Ui();
    }


    /**
     * Runs the Duke program
     */
    public void run() {
        ui.showInfo();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public String getResponse(String input) {
        String op;
        try {
            Command c = Parser.parse(input);
            op = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            op = (e.getMessage());
        }
        return op;
    }

    public String welcomeMessage() {
        return "Hello, I'm DukeQ\n"
                + "todo then your instructions e.g. todo read book\n"
                + "deadline then your instructions e.g. deadline by 2020-09-01\n"
                + "type 'event' followed by the description,\n"
                + "then '/at', then timing in yyyy-MM-dd format\n"
                + "then '/by', then due date (yyyy-MM-dd) \n"
                + "done followed by the task number to marked it as done\"\n"
                + "type list to see the tasklists\n"
                + "type find followed by keywords to search for tasks \n"
                + "type bye to exit DukeQ\n";
    }

}
