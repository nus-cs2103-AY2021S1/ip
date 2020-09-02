

/**
 * Represents a to do list chatbot called Duke.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class Duke  {
    /**
     * Storage object that handles reading and writing to local hard disk.
     */
    private Storage storage;
    /**
     * Task list that stores tasks.
     */
    private TaskList tasks;
    /**
     * UI object that handles interactions with the user.
     */
    private Ui ui;

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/icebear.jpg"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/grizz.jpg"));

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            tasks = storage.load();
        } catch (DukeException de) {
            ui.printLoadingError(de);
            tasks = new TaskList();
        }
    }
    /**
     * Duke constructor.
     * @param filePath The path of the local copy where Duke saves list to.
     */
    public Duke(final String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException de) {
            ui.printLoadingError(de);
            tasks = new TaskList();
        }
    }

    /**
     * This method runs Duke the chatbot by taking in commands from the user.
     */
    public void run() {
        ui.printGreeting();

        String input;
        boolean isEnd = false;
        while (!isEnd) {
            input = ui.readCommand();
            isEnd = Parser.execute(tasks, ui, storage, input).equals("bye then");
        }
    }

//    @Override
//    public void start(Stage stage) {
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
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//        //Step 2. Formatting the window to look as expected
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
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.getChildren().add(
//                DialogBox.getDukeDialog("Yooo, I'm Grizz.\nWhat can I do for you today?\n"
//                        + "Please enter dates and times like this: yyyy-mm-dd hhmm", duke)
//        );
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
//    private void handleUserInput() {
//        String userText = userInput.getText();
//        String dukeText = getResponse(userInput.getText());
//
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, user),
//                DialogBox.getDukeDialog(dukeText, duke)
//        );
//        userInput.clear();
//    }
//
    protected String getResponse(String input) {
        return Parser.execute(tasks, ui, storage, input);
    }

    /**
     * This is the main method which makes use of the run method.
     * @param args Unused
     */
    public static void main(final String[] args) {
        new Duke("data/duke.txt").run();
    }
}
