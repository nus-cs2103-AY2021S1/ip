import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A chat bot reacting on tasks.
 */

public class Duke extends Application{
    private static Storage storage;
    private static TaskList tasks;
    private static UI ui;
    private static Parser newParser;

    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) {
        storage.createDirectory("FileSaver1");
        storage.updateList(tasks);
        newParser.run(tasks, ui);
        storage.updateDirectory(tasks);
        tasks.showList();
        System.out.println("Thank you for using! See you next time!");
    }

    @Override
    public void start(Stage stage) {
        storage.createDirectory("FileSaver2");
        storage.updateList(tasks);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);
        dialogContainer = new VBox();

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.show();


        //Formatting and Style
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        userInput.setPrefWidth(325.0);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

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

        // Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String userInputText = userInput.getText();
        System.out.println("user input:" + userInputText);

        String response = newParser.uiResponse(tasks, ui,userInputText);
        System.out.println("response:" + response);
        Label dukeText = new Label(getResponse(response));

        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        storage.updateDirectory(tasks);
        userInput.clear();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


}
