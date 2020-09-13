package rogue;

import rogue.ui.Ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The entry point of {@code Rogue} to initialize the GUI.
 */
public class Main extends Application {
    /** The path of the file to which {@code Rogue} will save its data. */
    private static final String DEFAULT_SAVE_FILE_PATH = "./data/tasks.txt";

    @Override
    public void start(Stage stage) {
        Rogue rogue = new Rogue(DEFAULT_SAVE_FILE_PATH);
        stage.setTitle("Rogue"); // Sets title for the window
        Ui.init(stage, rogue);
    }
}
