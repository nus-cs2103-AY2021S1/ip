package sceneplayground.components;

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
import sceneplayground.components.dialog.DialogBox;

public class App extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dog.jpg"));


    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        ScrollPane scrollPane = new ScrollPane();
        VBox dialog = new VBox();
        scrollPane.setContent(dialog);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(layout);

        stage.setScene(scene); // Setting the stage to show our screen

        // modifying stage and node values
        stage.setTitle("Hello");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        layout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Adding functionality
        sendButton.setOnMouseClicked((event -> {
            Label text = getDialogLabel(userInput.getText());
            dialog.getChildren().addAll(
                    new DialogBox(text, new ImageView(user)),
                    new DialogBox(text, new ImageView(duke))
            );
            userInput.clear();
        }));

        userInput.setOnAction(event -> {
            Label text = getDialogLabel(userInput.getText());
            Label duketext = getDialogLabel(userInput.getText());
            dialog.getChildren().addAll(
                    new DialogBox(text, new ImageView(user)),
                    new DialogBox(duketext, new ImageView(duke))
            );
            userInput.clear();
        });
        // scroll down with inputs
        dialog.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        stage.show(); // Render the stage.
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}
