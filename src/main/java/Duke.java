package java;

import javafx.application.Application;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.commands.Command;
import java.exceptions.DukeException;
import java.tasklist.TaskList;
import java.parser.Parser;
import java.storage.Storage;
import java.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (DukeException e) {
            ui.displayErrorMessage(e);
            tasks = new TaskList();
        }
    }


    /**
     * Run a Duke object while isExit is not changed to true
     */
//    public void run() {
//        ui.displayWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.displayErrorMessage(e);
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    public String getResponse(String input){
        try{
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException ex) {
            return ex.getMessage();
        }
    }



//    public static void main(String[] args) {
//        new Duke("/data.txt").run();
//    }

}
