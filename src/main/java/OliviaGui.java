import olivia.ui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import olivia.logic.Parser;
import olivia.ui.ViewPane;

public class OliviaGui extends Application {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/gowon.png"));
    private final Image oliviaImage = new Image(this.getClass().getResourceAsStream("/images/olivia.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Olivia olivia;

    private void displayIntro() {
        dialogContainer.getChildren().addAll(
                DialogBox.getOliviaDialog(olivia.welcome(), new ImageView(oliviaImage)));
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        Parser parser = olivia.getParser();
        String oliviaText = parser.parse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getOliviaDialog(oliviaText, new ImageView(oliviaImage))
        );
        if (olivia.shouldExit()) {
            ((Stage) userInput.getScene().getWindow()).close();
        }
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {

        scrollPane = new ViewPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Olivia");
        stage.setResizable(false);
        stage.setMinHeight(650.0);
        stage.setMinWidth(420.0);

        mainLayout.setPrefSize(420.0, 650.0);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(345.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked(event -> handleUserInput());

        userInput.setOnAction(event -> handleUserInput());

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        olivia = new Olivia();

        displayIntro();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
