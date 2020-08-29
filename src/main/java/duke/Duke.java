package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duke.ui.DialogContainer;
import duke.ui.SendButton;
import duke.ui.UserInputArea;
import duke.ui.ScrollContainer;

import java.io.IOException;

public class Duke extends Application {

    private Storage store = new Storage();
    private TaskList taskList;
    private Ui ui;
    private Parser parser = new Parser();

    private ScrollContainer scrollPane;
    private DialogContainer dialogContainer;
    private UserInputArea userInput;
    private SendButton sendButton;
    private Scene scene;


    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollContainer();
        dialogContainer = new DialogContainer();
        scrollPane.setContent(dialogContainer);
        userInput = new UserInputArea();
        sendButton = new SendButton("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    public void start() {
        ui = new Ui();
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                store.initializeStorage();
                taskList = new TaskList(store.getTasks());
                Command c = parser.parse(ui.readCommand());
                c.execute(taskList, ui, store);
                isRunning = !c.isExit();
            } catch (IOException e) {
                System.out.println("Error connecting to storage, actions made will not be saved");
            } catch (DukeException e) {
                ui.printMessage(e.getFriendlyMessage());
            }
        }
        ui.stopReading();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
