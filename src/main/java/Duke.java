import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is an application that stores a user's tasks.
 */
public class Duke extends Application {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/pirate.png"));
    
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(".//.//.//savedTasks.txt");
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
    }
    /**
     * Constructor that creates a Duke object.
     * @param filePath the filepath in which the application's data will be saved.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
        this.parser = new Parser(ui, taskList, storage);
    }
    

//    public static void main(String[] args) {
//        new Duke(".//.//.//savedTasks.txt").run();
//    }
    public Ui getUi() {
        return this.ui;
    }
    
    public TaskList getTaskList() {
        return taskList;
    }
    
    public Storage getStorage() {
        return storage;
    }
    

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

        // more code to be added here later

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Spongebob To Do List");
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        storage.load(taskList, ui);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.loadTasks(), duke)
        );
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showTasks(taskList), duke)
        );
    }
    
    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parseInput(input);
    }
    
}