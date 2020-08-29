package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class to run Ui object and listen for user input
 */
public class Duke extends Application {
//    private TaskList tasks;
//    private Ui ui;
//    private Storage storage;
//
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.loadData());
//        } catch (Exception e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
//    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

//    public void run() throws Exception {
//        this.ui.initialise(tasks, storage);
//    }

//    public static void main(String[] args) throws Exception {
//        // remember to change filepath to "../../../data/duke.ser" during jar build
//        Duke duke = new Duke("data/duke.ser");
//        duke.run();
//    }
}
