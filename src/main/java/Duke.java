import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.ui.DialogBox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    
    // UI variables
    
    
    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        parser = new Parser(taskList, ui);
        Storage.loadTasksFrom("data/duke.txt", taskList);
    }
    
    public void LoadTasks() {
        Storage.loadTasksFrom("data/duke.txt", taskList);
    }
    
    public void saveTasks() {
        Storage.saveTasksTo("data/duke.txt", taskList);
    }
    
    public String parseInput(String input) {
        String output = parser.parse(input);
        System.out.println(output);
        return output;
    }


}
