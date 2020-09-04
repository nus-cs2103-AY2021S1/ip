import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main Class here.
 */
public class Duke extends Application {
//    public static void main(String[] args) {
//        ChatBot.start();
//    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage){
        //Top header title
        stage.setTitle("Duke");
        //Ability to change the size of window
        stage.setResizable(false);
        stage.setMinHeight(600);
        stage.setMinWidth(400);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane,userInput,sendButton);
        mainLayout.setPrefSize(400.0,600.0);
        scrollPane.setPrefSize(385,535);
        //Horizontal scrolling disallowed
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //Sets scroll position of the scroll pane
        scrollPane.setVvalue(1.0);
        //Giving horizontal flex
        scrollPane.setFitToHeight(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        //Controls are extensions of the Region class
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        //Setting Constraints, directly comparable with the XML files that I did the other time for koitlin
        AnchorPane.setTopAnchor(scrollPane,1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);

        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput,1.0);
        AnchorPane.setLeftAnchor(dialogContainer,1.0);
        AnchorPane.setRightAnchor(dialogContainer,1.0);

        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));

//        sendButton.setOnAction((event -> {
//            handleUserInput();
//        }));

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput(){
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input){
        return "Duke echoes: " + input;
    }


}