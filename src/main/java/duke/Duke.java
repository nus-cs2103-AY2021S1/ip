package duke;

import java.util.Scanner;

/**
 * Runs the chatbot.
 */
public class Duke {
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    //    public static void main(String[] args) {
//        Duke.run();
//    }
//
//    /**
//     * Starts the chatbot.
//     */
//    public static void run() {
//        boolean toContinue;
//        Ui.showWelcome();
//        Scanner sc = new Scanner(System.in);
//        TaskList taskList = new TaskList();
//        do {
//            String answer = sc.nextLine();
//            toContinue = Parser.understandText(answer, taskList);
//        } while (toContinue);
//    }
//
//    @Override
//    public void start(Stage stage) {
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
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
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
//        //Part 3. Add functionality to handle user input.
////        sendButton.setOnMouseClicked((event) -> {
////            handleUserInput();
////        });
////
////        userInput.setOnAction((event) -> {
////            handleUserInput();
////        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     *
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    public String getResponse(String input) {
//        return "Duke heard: " + input;
//    }

}
