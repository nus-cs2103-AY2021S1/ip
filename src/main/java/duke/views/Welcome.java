package duke.views;

import duke.components.DataSaver;
import duke.utils.Storage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Encapsulates a scene that displays the welcome page.
 */
public class Welcome implements Page {
    private Storage storage;

    /**
     * Constructs a Welcome instance that displays a welcome view.
     *
     * @param storage a storage object used to read and store data.
     */
    public Welcome(Storage storage) {
        this.storage = storage;
    }

    /**
     * Sets the scene of the given stage to the welcome scene.
     *
     * @param window the window on which the scene displays.
     */
    @Override
    public void setScene(Stage window) {
        // set up components
        Button startButton = new Button("Start Duke!");
        Button closeButton = new Button("Exit");
        Label logo = new Label(Messenger.LOGO);

        // read in file and print outcome
        Label storageText = new Label();

        // set up layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(logo, storageText, startButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        // set up scene
        Scene scene = new Scene(layout, DEFAULT_PAGE_WIDTH, DEFAULT_PAGE_HEIGHT);

        // redirect
        startButton.setOnAction(e -> new App(storage).setScene(window));
        closeButton.setOnAction(e -> {
            DataSaver.save(storage);
            if (DataSaver.isQuitting()) {
                window.close();
            }
        });
        window.setScene(scene);
        window.show();
        storage.readSavedFile(storageText);
    }
}
