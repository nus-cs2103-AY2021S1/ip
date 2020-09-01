package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {

    }

    /**
     * Creates the Duke object.
     * @param filePath File Path for the saved tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.newStorage();
            } catch (DukeException ex) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.TaskList.txt").run();
    }

    public void run() {
        ui.printGreetings();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInputs();
                Command command = Parser.parse(input);
                command.execute(ui, tasks, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        ui.close();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
