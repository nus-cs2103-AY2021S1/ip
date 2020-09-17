package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class MainWithUi extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userPic = new Image(this.getClass()
            .getResourceAsStream("/images/DaUser.jpg"));
    private Image dukePic = new Image(this.getClass()
            .getResourceAsStream("/images/DaDuke.jpg"));
    private java.nio.file.Path path = java.nio.file.Paths
            .get(System.getProperty("user.home"), "ip","start.txt");
    private Duke dukeBot = new Duke(path);

    @Override
    public void start(Stage stage) throws Exception {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        dialogContainer.setBackground(new Background(new BackgroundFill(Color
                .rgb(232,223,231), CornerRadii.EMPTY, Insets.EMPTY)));
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setOpacity(0.95);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke - Personal Task Manager (ง ˙o˙)ว");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(390, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(335.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Step 3. Add functionality to handle user input.
        handleAtStart();

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Iteration 2:
     * Creates dialog boxes, containing user's input and Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws Exception {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        Circle userPicHolder = new Circle(40);
        userPicHolder.setFill(new ImagePattern(userPic));

        Circle dukePicHolder = new Circle(40);
        dukePicHolder.setFill(new ImagePattern(dukePic));

        dialogContainer.getChildren().addAll(
                DialogBoxUser.getUserDialog(userText, userPicHolder),
                new Text(""),
                DialogBoxDuke.getDukeDialog(dukeText, dukePicHolder),
                new Text("")
        );
        userInput.clear();
    }

    private void handleAtStart() throws Exception {
        dukeBot.start();
        Label dukeTextHello = new Label(Ui.sayHello());
        Label dukeTextListOfCommands = new Label(Ui.listOfCommands());
        Label dukeTextList = new Label(getResponse("list"));


        Circle dukePicHolder1 = new Circle(40);
        dukePicHolder1.setFill(new ImagePattern(dukePic));

        Circle dukePicHolder2 = new Circle(40);
        dukePicHolder2.setFill(new ImagePattern(dukePic));

        Circle dukePicHolder3 = new Circle(40);
        dukePicHolder3.setFill(new ImagePattern(dukePic));

        dialogContainer.getChildren().addAll(
                DialogBoxDuke.getDukeDialog(dukeTextHello, dukePicHolder1),
                new Text(""),
                DialogBoxDuke.getDukeDialog(dukeTextListOfCommands, dukePicHolder2),
                new Text(""),
                DialogBoxDuke.getDukeDialog(dukeTextList, dukePicHolder3),
                new Text("")
        );
    }

    /**
     * Returns a response to user input.
     * @param input user input string
     * @return a response string
     */
    public String getResponse(String input) throws Exception {
        if (input.equals("bye")) {
            dukeBot.end();
        }
        return new Parser(input).getRespond(dukeBot.getLst());
    }
}
