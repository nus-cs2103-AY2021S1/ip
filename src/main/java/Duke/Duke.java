package main.java.Duke;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import main.java.Duke.Commands.Command;
import main.java.Duke.DukeException.DukeException;
import main.java.Duke.Task.Task;
import main.java.Duke.Task.TaskList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



import java.io.IOException;
import java.util.ArrayList;

class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}

public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/aang.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/shiba.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    boolean isExit = false;

    public Storage storage;
    public TaskList tasklist;
    public Ui ui;

    public Duke () {
        this.storage = new Storage("duke.txt");
        this.tasklist = new TaskList(storage.arr);
        this.ui = new Ui();
    }

    public static void main(String[] args) throws DukeException, IOException {
        boolean isExit = false;
        Duke duke = new Duke();
        ArrayList<Task> arrList = duke.tasklist.list;
        Parser parser = new Parser(duke);

        duke.ui.greetingMessage();
        String userinput = "";

        while (!isExit) {
            duke.ui.prompt();
            userinput = duke.ui.readCommand();
            Command command = parser.parse(userinput, duke.tasklist);
            command.execute();
            isExit = command.isExit;
        }
        duke.storage.saveTasks(arrList);
        System.out.println("Bye. Hope to see you again soon!");
    }


    @Override
    public void start(Stage stage) throws Exception {
        Duke duke = new Duke();

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(duke);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(duke);
        });
    }
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    private void handleUserInput(Duke duke) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(),duke));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input,Duke duke) {
        Parser parser = new Parser(duke);
        duke.ui.prompt();
        //userinput = duke.ui.readCommand();
        Command command = parser.parse(input, duke.tasklist);
        String string =command.execute();
        isExit = command.isExit;
        if(!this.isExit){
        return string;
        }else {
            duke.storage.saveTasks(duke.tasklist.list);
            return "Bye. Hope to see you again soon!";
        }
    }


}
