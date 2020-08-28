package duke.views;

import duke.components.Evaluator;
import duke.tasks.TaskList;
import duke.utils.Storage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App implements Page {
    private Storage storage;

    /**
     * Constructs an App instance that displays the view for the main program.
     *
     * @param storage a Storage object used to deal with data transfer.
     */
    public App(Storage storage) {
        this.storage = storage;
    }

    /**
     * Sets the scene of the given stage to the App scene.
     *
     * @param window the window on which the scene displays.
     */
    @Override
    public void setScene(Stage window) {
        TaskList taskList = storage.getTaskList();

        //create a GridPane container for command input
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(6, 6, 6, 6));
        grid.setVgap(5);
        grid.setHgap(5);

        // define the main text display
        Label textDisplay = new Label();
        textDisplay.setText(taskList.printList());
        GridPane.setConstraints(textDisplay, 0, 0);


        // define the command text field
        TextField commands = new TextField();
        commands.setPromptText("Enter your commands");
        commands.setPrefColumnCount(10);
        GridPane.setConstraints(commands, 0, 1);

        // define the Submit button
        Button submit = new Button("Submit");
        Evaluator evaluator = new Evaluator(storage, textDisplay, window);
        submit.setOnAction(e -> {
            evaluator.handle(commands.getText());
            commands.setText("");
        });
        GridPane.setConstraints(submit, 1, 1);

        // define the back button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            storage.saveDataToFile(textDisplay);
            new Welcome(storage).setScene(window);
        });
        GridPane.setConstraints(backButton, 2, 1);

        // add components to grid
        grid.getChildren().addAll(textDisplay, commands, submit, backButton);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, DEFAULT_PAGE_WIDTH, DEFAULT_PAGE_HEIGHT);
        window.setScene(scene);
        window.show();
    }
}
