package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is the name of this program. It is an CLI app that reads and save
 * the user Tasks. The user can use it to keep tabs on their tasks and
 * and can mark them as done when they deem fit.
 */
public class Duke{

    private final static String DIRECTORY =  System.getProperty("user.dir");
    private Storage storage;
    private static TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object
     */

    public Duke(){
        storage = new Storage(this.DIRECTORY);
        ui = new Ui();
        this.tasks = new TaskList();
        storage.loadTasks(tasks);

    }
    /**
     * Runs the programme, using a Duke Object
     */
//    public void run() throws DukeException {
//        ui.startMessage();
//        storage.loadTasks(tasks);
//
//        Scanner scanner = new Scanner(System.in);
//
//        while  (scanner.hasNext()) {
//            try {
//                String input = scanner.nextLine();
//                Command executable = Parser.parse(input);
//                String output = executable.execute(tasks, ui, storage);
//                if (executable.isComplete()){
//                    break;
//                }
//                ui.lineFormatter(output);
//            } catch (DukeException e){
//                ui.lineFormatter(e.getMessage());
//            }
//        }
//
//    }

    public String getResponse(String input) throws DukeException{
        try {
            Command executable = Parser.parse(input);
            String output = executable.execute(tasks, ui, storage);
            if (executable.isComplete()){
                System.exit(0);

            }
//            ui.lineFormatter(output);
            return output;
        } catch (DukeException e){
//            ui.lineFormatter(e.getMessage());
            return e.getMessage();
        }

    }
}


