package duke;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke the best chatbot hehe
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public void start(Stage stage) {
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Init Duke
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.readData());
        parser = new Parser();
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Take in command
     */
    public void run() {
        ui.sayHi();
        Scanner myScanner = new Scanner(System.in);
        while(true) {
            String cmd = myScanner.nextLine();
            if(cmd.equals("bye")) {
                ui.sayBye();
                break;
            }
            else if(cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= tasks.getSize(); ++i) {
                    System.out.println(i + "." + tasks.get(i - 1).getStatus());
                }
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("done")) {
                int c = 0;
                for(int i = 5; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Nice! I've marked this task as done:");
                tasks.get(c - 1).done();
                System.out.println(tasks.get(c - 1).getStatus());
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("todo")) {
                try {
                    checkCmd(cmd, 4, "☹ OOPS!!! The description of a todo cannot be empty.");
                    String getName = cmd.substring(5);
                    Todo tmp = new Todo(getName);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 8 &&cmd.substring(0, 8).equals("deadline")) {
                try {
                    checkCmd(cmd, 8, "☹ OOPS!!! The description of a deadline cannot be empty.");
                    String getName = parser.getNameBy(cmd);
                    String getDeadline = parser.getDeadlineBy(cmd);
                    getDeadline = formatDate(getDeadline);
                    Deadline tmp = new Deadline(getName, getDeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 5 &&cmd.substring(0, 5).equals("event")) {
                try {
                    checkCmd(cmd, 5, "☹ OOPS!!! The description of a event cannot be empty.");
                    String getName = parser.getNameAt(cmd);
                    String getTime = parser.getDeadlineAt(cmd);
                    getTime = formatDate(getTime);
                    Event tmp = new Event(getName, getTime);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + tmp.getStatus());
                    tasks.add(tmp);
                    System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
                    //
                }
                catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(cmd.length() >= 6 && cmd.substring(0, 6).equals("delete")){
                int c = 0;
                for(int i = 7; i < cmd.length(); ++i) {
                    c = c * 10 + cmd.charAt(i) - '0';
                }
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tasks.get(c - 1).getStatus());
                tasks.remove(c - 1);
                System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
            }
            else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("find")) {
                String tmp = cmd.substring(5);
                System.out.println("Here are the matching tasks in your list:");
                for(int i = 1; i <= tasks.getSize(); ++i) if(tasks.get(i - 1).description.contains(tmp)){
                    System.out.println(i + "." + tasks.get(i - 1).getStatus());
                }
            }
            else System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            storage.updateDataFile(tasks.getArrayList());
        }
    }

    /**
     * check if date is yyyy-mm-dd, then format to MMM d yyyy
     */
    public static String formatDate(String str) {
        LocalDate d;
        try {
            d = LocalDate.parse(str);
        } catch (Exception e) {
            return str;
        }
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * check if the command is valid
     */
    public static void checkCmd(String cmd, int len, String Ex) throws DukeException {
        if(cmd.length() == len) throw new DukeException(Ex);
    }

}
