package rogue;

import rogue.ui.Ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String DEFAULT_SAVE_FILE_PATH = "./data/tasks.txt";

    @Override
    public void start(Stage stage) {
        Rogue rogue = new Rogue(DEFAULT_SAVE_FILE_PATH);
        Ui.init(stage, rogue);
    }
}
