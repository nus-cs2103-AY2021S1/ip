package Duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Represents a todo manager bot.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Constructs a Duke bot.
     * @param filePath the path of the file which store the previous data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts running Duke.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String res;
        ui.welcome();
        while (true) {
            try {
                res = sc.nextLine();
                if (res.equalsIgnoreCase(Operations.BYE.name())) {
                    break;
                } else if (res.equalsIgnoreCase(Operations.LIST.name())) {
                    ui.printList(tasks.getData());
                } else if (res.startsWith(Operations.DONE.name().toLowerCase())) {
                    tasks.done(res, ui, storage);
                } else if (res.startsWith(Operations.DELETE.name().toLowerCase())) {
                    tasks.delete(res, ui, storage);
                } else if (res.startsWith(Operations.DURATIONTASK.name().toLowerCase())) {
                    tasks.durationTask(res, ui, storage);
                } else if (res.startsWith(Operations.TODO.name().toLowerCase())) {
                    tasks.todo(res, ui, storage);
                } else if (res.startsWith(Operations.DEADLINE.name().toLowerCase())) {
                    tasks.deadline(res, ui, storage);
                } else if (res.startsWith(Operations.EVENT.name().toLowerCase())) {
                    tasks.event(res, ui, storage);
                } else if (res.startsWith(Operations.FIND.name().toLowerCase())) {
                    tasks.find(res, ui);
                } else {
                    // Exception: eg. ???
                    throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NumberFormatException e) {
                // Exception: eg. done some words
                // Exception: eg. delete some words
                ui.showNumberFormatError();
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (FileNotFoundException e) {
                ui.showFileNotFoundError();
            }
        }
        ui.bye();
    }

    /**
     * Starts creating an Duke bot object.
     * @param args no need here.
     */
    public static void main(String[] args) {
        new Duke("./log.txt").run();
    }

    /**
     * Starts Duke in GUI.
     * @param stage is a necessary component for setting GUI.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input by using Lambdas.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String res = "";
        try {
            if (input.equalsIgnoreCase(Operations.BYE.name())) {
                res = ui.bye();
            } else if (input.equalsIgnoreCase(Operations.LIST.name())) {
                res = ui.printList(tasks.getData());
            } else if (input.startsWith(Operations.DONE.name().toLowerCase())) {
                res = tasks.done(input, ui, storage);
            } else if (input.startsWith(Operations.DELETE.name().toLowerCase())) {
                res = tasks.delete(input, ui, storage);
            }  else if (input.startsWith(Operations.DURATIONTASK.name().toLowerCase())) {
                res = tasks.durationTask(input, ui, storage);
            } else if (input.startsWith(Operations.TODO.name().toLowerCase())) {
                res = tasks.todo(input, ui, storage);
            } else if (input.startsWith(Operations.DEADLINE.name().toLowerCase())) {
                res = tasks.deadline(input, ui, storage);
            } else if (input.startsWith(Operations.EVENT.name().toLowerCase())) {
                res = tasks.event(input, ui, storage);
            } else if (input.startsWith(Operations.FIND.name().toLowerCase())) {
                res = tasks.find(input, ui);
            } else {
                res = "      OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (NumberFormatException e) {
            // Exception: eg. done some words
            // Exception: eg. delete some words
            res = ui.showNumberFormatError();
        } catch (DukeException e) {
            res = ui.showDukeError(e);
        } catch (FileNotFoundException e) {
            res = ui.showFileNotFoundError();
        }
        return res;
    }
}