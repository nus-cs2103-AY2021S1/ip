import java.io.IOException;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Duke extends Application {
    static String HOME = System.getProperty("user.home");
    static java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    static Scanner sc = new Scanner(System.in);
    static Ui ui = new Ui();
    static Parser parser = new Parser();

    static String s;
    static TaskList tasklist;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) throws IOException {
        initialize();
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

        // step 2 making window look as expected
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

    //Part 3. Add functionality to handle user input.
    sendButton.setOnMouseClicked((event) -> {
        handleUserInput();
    });

    userInput.setOnAction((event) -> {
        handleUserInput();
    });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    private String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return ui.exit();
            } else if (input.equals("list")) {
                return ui.list();
            } else if (input.startsWith("done")) {
                int n = parser.getTaskNumber(input);
                Task t = tasklist.complete(n);
                return ui.complete(t);
            } else if (input.startsWith("delete")) {
                int n = parser.getTaskNumber(input);
                Task t = tasklist.delete(n);
                return ui.delete(t, tasklist.getTotal());
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                Task t = tasklist.add(input);
                return ui.add(t, tasklist.getTotal());
            } else if (input.startsWith("find")) {
                String keyword = parser.getKeyword(input);
                return ui.find(keyword);
            } else { // unknown input
                throw new UnknownInputException();
            }
        } catch (DukeException | IOException e) {
            return ui.handleException(e);
        }
    }


    static void initialize() throws IOException {
        Scanner myReader = new Scanner(PATH);
        int total = myReader.nextInt();
        tasklist = new TaskList(total);
    }

    public static void main(String[] args) throws IOException {
        // use launcher to launch
    }
}
