package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main class for running bot.
 */
public class Duke extends Application {
    private static Label outputLabel;
    private static final String FILE_PATH = (System.getProperty("user.dir").endsWith("text-ui-test")
        ? System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length() - 13)
            + "/data/duke.txt"
        : System.getProperty("user.dir") + "/data/duke.txt");

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Duke instance with the specified file name.
     * @param filePath String of file's path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Returns output label.
     * @return the output label.
     */
    public static Label getOutputLabel() {
        return outputLabel;
    }

    /**
     * Runs the main logic of the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method of programme.
     * @param args Command line arguments. Not needed for this programme
     */
    public static void main(String ... args) {
        new Duke(FILE_PATH).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Todo List");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        String instructions = "Hello! I'm duke.Duke\n"
                + "Send me a task in one of the following formats and I'll store it for you.\n"
                + "\tTodo: \"todo <description>\"\n"
                + "\tDeadline: \"deadline <description> /by <YYYY-MM-DD>\"\n"
                + "\tEvent: \"event <description> /at <YYYY-MM-DD>\"\n"
                + "Send \"list\" to see all tasks.\n"
                + "Send \"find <string of choice>\" to see all related tasks.\n"
                + "Send \"done <item number>\" to mark an item as done\n"
                + "Send \"delete <item number>\" to delete and item from the list\n"
                + "Send \"bye\" to end our conversation.";

        Label instructionsLabel = new Label(instructions);
        GridPane.setConstraints(instructionsLabel, 0, 0, 3, 1);

        Label commandLabel = new Label("Command:");
        GridPane.setConstraints(commandLabel, 0, 1);

        outputLabel = new Label();
        GridPane.setConstraints(outputLabel, 0, 2, 3, 3);

        Button submitButton = new Button("Submit");
        TextField commandInput = new TextField();

        submitButton.setOnAction(e -> {
            try {
                String fullCommand = commandInput.getText();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    stage.close();
                }
            } catch (DukeException ex) {
                outputLabel.setText(ex.getMessage());
            } finally {
                commandInput.clear();
            }
        });
        GridPane.setConstraints(submitButton, 2, 1);

        commandInput.setMinWidth(400);
        commandInput.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                submitButton.fire();
            }
        });
        GridPane.setConstraints(commandInput, 1, 1);

        grid.getChildren().addAll(instructionsLabel, commandLabel, commandInput, submitButton, outputLabel);

        Scene scene = new Scene(grid, 600, 600);
        stage.setScene(scene);
        stage.show();
    }
}
