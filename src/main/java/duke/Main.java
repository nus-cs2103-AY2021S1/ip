package src.main.java.duke;
import java.io.IOException;

import src.main.java.duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private src.main.java.duke.data.Duke duke = new src.main.java.duke.data.Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//
///**
// * Entry point of the Best2103Bot
// * Initializes the application and starts the interaction with user.
// */
//public class Main {
//
//    private TextUi ui;
//    private StorageFile storage;
//    private Duke duke;
//
//    public static void main(String... launchArgs) {
//        new Main().run(launchArgs);
//    }
//
//    /**
//     * Run the program until termination
//     */
//    public void run(String[] launchArgs) {
//        start (launchArgs);
//        runCommandLoopUntilExitCommand();
//        exit();
//    }
//
//    /**
//     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
//     *
//     * @param launchArgs arguments supplied by the user at program launch
//     *
//     */
//    private void start(String[] launchArgs) {
//        try {
//            this.ui = new TextUi();
//            this.storage = initializeStorage(launchArgs);
//            this.duke = storage.load();
//            // Show welcome message and file path
//            ui.showWelcomeMessage();
//
//        } catch (InvalidStorageFilePathException | StorageOperationException e) {
//            ui.showInitFailedMessage();
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** Prints the Goodbye message and exits. */
//    private void exit() {
//        ui.showGoodbyeMessage();
//        System.exit(0);
//    }
//
//    /** Reads the user command and executes it, until the user issues the exit command.  */
//    private void runCommandLoopUntilExitCommand() {
//        Command command;
//        do {
//            String userCommandText = ui.getUserCommand();
//            command = new Parser().parseCommand(userCommandText);
//            CommandResult result = executeCommand(command);
//            ui.showResultToUser(result);
//
//        } while (!ExitCommand.isExit(command));
//    }
//
//    /**
//     * Executes the command and returns the result.
//     *
//     * @param command user command
//     * @return result of the command
//     */
//    private CommandResult executeCommand(Command command) {
//        try {
//            command.setData(duke);
//            CommandResult result = command.execute();
//            storage.save(duke);
//            return result;
//        } catch (Exception e) {
//            ui.showToUser(e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
//     * @param launchArgs arguments supplied by the user at program launch
//     * @throws InvalidStorageFilePathException if the target file path is incorrect.
//     */
//    private StorageFile initializeStorage(String[] launchArgs) throws InvalidStorageFilePathException {
//        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
//        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
//    }
//}
