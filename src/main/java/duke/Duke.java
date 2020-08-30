package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.Parser;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke {
//    private final Ui ui;
//    private final DukeFileHandler fileHandler;
//    private TaskList tasks;
//
//
//    private Duke(String path) {
//        ui = new Ui();
//        ui.welcome();
//
//        fileHandler = new DukeFileHandler(path);
//
//        try {
//            tasks = new TaskList(fileHandler.readFile());
//            if (!tasks.isNull()) {
//                ui.displayList(tasks.getList());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            tasks = new TaskList(new ArrayList<>());
//        }
//
//        run();
//    }
//
//
//    private void run() {
//
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            String input = scanner.nextLine().trim();
//
//            try {
//                Command command = Parser.parse(input);
//                command.execute(tasks, ui, fileHandler);
//
//                if (command instanceof ExitCommand) {
//                    break;
//                }
//
//            } catch (DukeException ex) {
//                ui.displayThis(ex.getMessage());
//            }
//
//        }
//    }


//    public static void main(String[] args) {
//        new Duke("data/dukeData.txt");
//    }

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));


//    @Override
//    public void start(Stage primaryStage) throws Exception {
////        Label helloWorld = new Label("Hello World");
////        Scene scene = new Scene(helloWorld);
////
////        primaryStage.setScene(scene);
////        primaryStage.show();
//
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        // more code to be added here later
//
//        //Step 1. Formatting the window to look as expected.
//
//        //...
//
//        //Step 2. Formatting the window to look as expected
//        primaryStage.setTitle("Duke");
//        primaryStage.setResizable(false);
//        primaryStage.setMinHeight(600.0);
//        primaryStage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // more code to be added here later
//
//
////        //Step 3. Add functionality to handle user input.
////        sendButton.setOnMouseClicked((event) -> {
////            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
////            userInput.clear();
////        });
////
////        userInput.setOnAction((event) -> {
////            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
////            userInput.clear();
////        });
//
//
//        // Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//
//    }


//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     *
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}
