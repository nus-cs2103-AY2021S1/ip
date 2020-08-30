import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * The main class that directs the required actions to the respective classes.
 */
public class Duke extends Application {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this("data/duke.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/nobita.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/doraemon.png"));
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
        dialogContainer.setSpacing(10);
        //dialogContainer.setBackground(new Background(new BackgroundFill(Color.rgb(255,250,205),
        //        CornerRadii.EMPTY, Insets.EMPTY)));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    //ignore hi and bye first, this takes in one input and churn a response
    private String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            String[] processedCommand = parser.parse(input);
            switch (processedCommand[0].toLowerCase()) {
            case "list":
                response.append(ui.showListTasks(tasks.getAllTasks()));
                break;
            case "print":
                response.append(ui.showRequiredTasks(tasks.getSameDateTasks(processedCommand[1])));
                break;
            case "done":
                Task doneTask = tasks.markDone(Integer.parseInt(processedCommand[1]));
                response.append(ui.showDoneTask(doneTask));
                break;
            case "delete":
                Task deletedTask = tasks.deleteTask(Integer.parseInt(processedCommand[1]));
                response.append(ui.showDeleteTask(deletedTask));
                break;
            case "todo":
                Task todoTsk = new Todo(processedCommand[1]);
                tasks.addTask(todoTsk);
                response.append(ui.showAddTask(todoTsk));
                break;
            case "deadline":
                Task deadlineTsk = new Deadline(processedCommand[1],
                        LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")));
                tasks.addTask(deadlineTsk);
                response.append(ui.showAddTask(deadlineTsk));
                break;
            case "event":
                Task eventTsk = new Event(processedCommand[1],
                        LocalDate.parse(processedCommand[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.parse(processedCommand[3], DateTimeFormatter.ofPattern("HHmm")),
                        LocalTime.parse(processedCommand[4], DateTimeFormatter.ofPattern("HHmm")));
                tasks.addTask(eventTsk);
                response.append(ui.showAddTask(eventTsk));
                break;
            case "find":
                response.append(ui.showRequiredTasks(tasks.getTasksWithKeyWord(processedCommand[1])));
                break;
            default:
                break;
            }
            response.append("\n" + ui.showTotalTasks(tasks.getNumTasks()));
            storage.updateFile(tasks.toString()); //reload the file
        } catch (DukeException e) {
            response.append(ui.showDukeError(e));
        } catch (IOException e) {
            response.append(ui.showLoadingError());
        }
        return response.toString();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        dialogContainer.setPadding(new Insets(10,10,10,10));
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Doraemon");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setPrefWidth(55.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
}
