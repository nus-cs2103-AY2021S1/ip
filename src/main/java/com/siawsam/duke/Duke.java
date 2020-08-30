package com.siawsam.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A Duke program.
 */
public class Duke extends Application {
    /**
     * The file path for saving tasks.
     */
    static final String FILE_PATH = "savedTasks.txt";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        primaryStage.show();
    }

    //CHECKSTYLE:OFF
    public static void main(String[] args) {
        //CHECKSTYLE:ON
        Application.launch(args);
        Ui.showWelcomeMessage();

        Storage storage = new Storage(FILE_PATH);
        try {
            Parser parser;
            
            if (storage.doesExist()) {
                TaskList savedList = storage.load();
                parser = new Parser(storage, savedList);
                Ui.showSuccessfulLoad();
            } else {
                Ui.showNoExistingSave();
                parser = new Parser(storage);
            }
            
            Ui.readUserInput(parser);
        } catch (IOException e) {
            Ui.showErrorMessage("An exception occurred:", e);
        } catch (ClassNotFoundException e) {
            Ui.showErrorMessage("The file specified doesn't store a TaskList object.", e);
        }
    }
}
