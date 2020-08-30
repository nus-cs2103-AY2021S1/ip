import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Encapsulates the main Duke program.
 */
public class Duke extends Application {

    /**
     * Member variables that make up the main Duke program.
     */
    private String savePath = "data/duke.txt";
    private DukeSaver saver;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    /**
     * Constructor for the Duke object.
     */
    public Duke() {
        saver = new DukeSaver(savePath);
        taskList = new TaskList();
        ui = new UI();
        parser = new Parser(ui, taskList, saver);

        saver.loadData(taskList);
    }


    /**
     * Starts the GUI using JavaFX.
     *
     * @param stage Primary stage.
     */
    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();

        stage.setTitle("DUKE"); // Window title.

        stage.setResizable(false); // Fix window size.

        BorderPane borderPane = new BorderPane(); // FOr layout of elemeents.
        borderPane.setMinSize(400, 400); // Size of GUI.

        Label outputDisplay = new Label(duke.ui.greet()); // Main output display.
        outputDisplay.setFont(new Font(16)); // Font size.
        duke.ui.setOutput(outputDisplay); // Sends this display to the UI system for printing text.

        TextField inputField = new TextField(); // User input text field.
        inputField.setPrefWidth(350); // Width of text field.

        Button submitButton = new Button("SEND"); // Submit button.
        submitButton.setOnAction(e -> { // Event handling for submit button click. Sends text value to parser.
            try {
                String input = inputField.getText();
                inputField.setText("");
                duke.parser.handleResponse(input);
            } catch (DukeException ex) {
                duke.ui.print(ex.toString());
            }
        });

        HBox inputHbox = new HBox(); // Horizontal box to align text field and submit button.
        inputHbox.setPadding(new Insets(15, 12, 15, 12));
        inputHbox.setSpacing(10);

        inputHbox.getChildren().addAll(inputField, submitButton); // Adding field and button to Hbox.

        borderPane.setCenter(outputDisplay); // Places display at center.
        borderPane.setBottom(inputHbox); // Places user inputs at bottom.

        Scene scene = new Scene(borderPane); // Attach pane to scene.

        stage.setScene(scene); // Setting the stage to show our screen.
        stage.show(); // Render the stage.
    }

    /**
     * Holds the main driver code.
     */
    private void run() {
        ui.greet();
        while (true) {
            String response = ui.prompt();
            try {
                parser.handleResponse(response);
            } catch (DukeException ex) {
                ui.print(ex.toString());
            }
        }
    }
}
