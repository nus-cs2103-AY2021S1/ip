package duke;

import duke.command.Command;
import exception.InvalidUsageException;
import exception.StorageException;
import exception.UnknownCommandException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for the Duke application
 */
public class Duke extends Application {
    // current task list
    private TaskList taskList;
    // storage file
    private Storage storage;
    // text ui interface
    private Ui ui;

    /**
     * Constructor for Duke application
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method to start the application
     */
    public void start() {
        try {
            this.taskList = new TaskList(storage.load());
            ui.showWelcomeMessage();
            boolean isExit = false;

            while (!isExit) {
                String input = ui.readCommand();
                ui.buildChatFence();

                // handle commands
                try {
                    Command command = Parser.parseCommand(input);
                    command.execute(taskList, ui, storage);
                    isExit = command.isExit();
                } catch (InvalidUsageException | UnknownCommandException ex) {
                    ui.print(ex.getMessage());
                } finally {
                    ui.buildChatFence();
                }
            }
        } catch (StorageException ex) {
            ex.printStackTrace();
        }
    }
}
