package duke;

//package duke;

/**
 * Main class that drives the application.
 */
public class Duke {

    /**
     * Deals with input output of files.
     */
    private Storage storage;
    /**
     * Task list.
     */
    private TaskList tasks;
    /**
     * Deals with user input output.
     */
    private Ui ui;

    /**
     * Constructor for the main driver.
     *
     * @param filePath File path to load history, and to save history.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
      * Generate a response to user input.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        }  catch (DukeException e) {
            return ui.showError(e);
        }
       // return "Duke heard: " + input;
    }

    /**
     * Creates new Duke object to start operations.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}



//public class Duke extends Application {
//
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//
//    @Override
//    public void start(Stage stage) {
//        // Step 1. Setting up required components
//
//        // Scroll pane is container for the dialog container, which is the content of the chat to scroll.
//        // Use vbox as chat is appended vertically
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        // Container for scroll pane (for chat), user input and send button
//        // Anchor pane used to fix positions of the components inside it
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout); // Setting the scene to be our Layout
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//
//        // Step 2. Formatting the window to look as expected
//
//        stage.setTitle("Duke"); // title of popup box
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535); // why width = 385 < 400?
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // don't show left right scrollbar
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE); // computed using scroll pane and "scroll bar" size
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0); // anchor scroll pane to top
//
//        // anchor send button to bottom right
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        // anchor send button to bottom left
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        // Step 3. Add functionality to handle user input.
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        // On action is for user keying "Enter" on keyboard.
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * Unused now, as we have more advanced control in Dialog Box
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2 + 3:
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
//
//    /**
//     * Generate a response to user input.
//     */
//    String getResponse(String input) {
//        return "Duke heard: " + input;
//    }
//
//}
