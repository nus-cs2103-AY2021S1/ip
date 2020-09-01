import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * main class
 */
public class Duke extends Application {

    // Initialise my variables for duke backend data handling
    Ui myDukeBot = new Ui();
    Storage myStorage = new Storage();
    Parser myParser = new Parser();
    TaskList myTaskList = new TaskList();

    // Added 2 image items for use later in duke
    private Image user = new Image(this.getClass().getResourceAsStream("/images/SeanDuke.JPG"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/JARVIS.JPG"));

    // Initialise items to be used in FX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {

        //  create a directory if not present. If directory
        //  already has tasks, populate my tasklist
        myStorage.createDirectory("ToDo");
        myStorage.populateList(myTaskList);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(79, 141, 151), CornerRadii.EMPTY, Insets.EMPTY)));

        userInput = new TextField();
        sendButton = new Button("Send");
        userInput.setBackground(new Background(new BackgroundFill(Color.rgb(145, 134, 126), CornerRadii.EMPTY, Insets.EMPTY)));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

         //Formatting the window to look as expected
        stage.setTitle("Jarvis");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.rgb(79, 141, 151), CornerRadii.EMPTY, Insets.EMPTY)));

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

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



        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }


    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String userIn = getResponse(userInput.getText());
        String response = myParser.listenerForUI(myTaskList, myDukeBot,userIn);

        Label dukeText = new Label(getResponse(response));

        DialogBox userDialog =  DialogBox.getUserDialog(userText, new ImageView(user));
        userDialog.setBackground(new Background(new BackgroundFill(Color.rgb(190, 190, 190), CornerRadii.EMPTY, Insets.EMPTY)));
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
        dukeDialog.setBackground(new Background(new BackgroundFill(Color.rgb(79, 141, 151), CornerRadii.EMPTY, Insets.EMPTY)));

        dialogContainer.getChildren().addAll(
            userDialog,
            dukeDialog
        );
        myStorage.updateDirectory(myTaskList);
        userInput.clear();
    }


    private String getResponse(String input) {
        return  input;
    }

    // Method to enable fast creation of dialogue box
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }



}
