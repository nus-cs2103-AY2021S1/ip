package duke.gui;

import java.io.IOException;

import duke.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ExitWindow {

    private static boolean answer;
    private static boolean stillExit;
    private static Stage stage = new Stage();
    private static boolean isLoaded = false;

    /**
     * Displays an ExitWindow asking if the user want to save current tasks.
     * @return the answer of the user (Y/N).
     * @throws IOException
     */
    public static boolean display() throws IOException {
        if (!isLoaded) {
            loadLayout();
        }

        stage.showAndWait();

        return answer;
    }

    private static void loadLayout() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/view/ExitWindow.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        scene.getStylesheets().add("view/ExitWindowStyle.css");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Exit");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            stillExit = false;
        });

        isLoaded = true;
    }

    /**
     * Gets if the user still want to exit the program.
     * @return
     */
    public static boolean isStillExit() {
        return stillExit;
    }

    /**
     * Handles the event of yesButton being clicked.
     */
    @FXML
    public void yesButtonClicked() {
        stage.close();
        answer = true;
        stillExit = true;
    }

    /**
     * Handles the event of noButton being clicked.
     */
    @FXML
    public void noButtonClicked() {
        stage.close();
        answer = false;
        stillExit = true;
    }
}
