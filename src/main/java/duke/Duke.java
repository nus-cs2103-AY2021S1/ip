package duke;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

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
import java.util.Scanner;
import java.util.List;

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
        //tring dukeHeard = getResponse(userInput.getText());
        String dukeReply = getReply(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                //DialogBox.getDukeDialog(dukeHeard, duke),
                DialogBox.getDukeDialog(dukeReply, user)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "cak heard: " + input;
    }

    public String getReply(String input) {
        return solveInput(input);
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

    public String addNewLines(String cur, String add) {
        cur += add + "\n";
        return cur;
    }

    public String solveInput(String cmd) {
        Reply res = new Reply();
        if(cmd.equals("bye")) {
            res.addNewLines(ui.sayBye());
        }
        else if(cmd.equals("list")) {
            res.addNewLines("Here are the tasks in your list:");
            // list with index
            for(int i = 1; i <= tasks.getSize(); ++i) {
                res.addNewLines(i + "." + tasks.get(i - 1).getStatus());
            }
            // list without index
            // tasks.getArrayList().forEach((n) -> System.out.println(n.getStatus()));
        }
        else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("done")) {
            int c = 0;
            for(int i = 5; i < cmd.length(); ++i) {
                c = c * 10 + cmd.charAt(i) - '0';
            }
            res.addNewLines("Nice! I've marked this task as done:");
            tasks.get(c - 1).done();
            res.addNewLines(tasks.get(c - 1).getStatus());
        }
        else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("todo")) {
            try {
                checkCmd(cmd, 4, "OOPS!!! The description of a todo cannot be empty.");
                String getName = cmd.substring(5);
                assert(!getName.equals(""));
                Todo tmp = new Todo(getName);
                res.addNewLines("Got it. I've added this task: ");
                res.addNewLines("  " + tmp.getStatus());
                tasks.add(tmp);
                res.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
            }
            catch (DukeException ex) {
                res.addNewLines(ex.getMessage());
            }
        }
        else if(cmd.length() >= 8 &&cmd.substring(0, 8).equals("deadline")) {
            try {
                checkCmd(cmd, 8, "☹ OOPS!!! The description of a deadline cannot be empty.");
                String getName = parser.getNameBy(cmd);
                assert(!getName.equals(""));
                String getDeadline = parser.getDeadlineBy(cmd);
                getDeadline = formatDate(getDeadline);
                Deadline tmp = new Deadline(getName, getDeadline);
                res.addNewLines("Got it. I've added this task: ");
                res.addNewLines("  " + tmp.getStatus());
                tasks.add(tmp);
                res.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
            }
            catch (DukeException ex) {
                res.addNewLines(ex.getMessage());
            }
        }
        else if(cmd.length() >= 5 &&cmd.substring(0, 5).equals("event")) {
            try {
                checkCmd(cmd, 5, "☹ OOPS!!! The description of a event cannot be empty.");
                String getName = parser.getNameAt(cmd);
                assert(!getName.equals(""));
                String getTime = parser.getDeadlineAt(cmd);
                getTime = formatDate(getTime);
                Event tmp = new Event(getName, getTime);
                res.addNewLines("Got it. I've added this task: ");
                res.addNewLines("  " + tmp.getStatus());
                tasks.add(tmp);
                res.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
                //
            }
            catch (DukeException ex) {
                res.addNewLines(ex.getMessage());
            }
        }
        else if(cmd.length() >= 6 && cmd.substring(0, 6).equals("delete")){
            int c = 0;
            for(int i = 7; i < cmd.length(); ++i) {
                c = c * 10 + cmd.charAt(i) - '0';
            }
            res.addNewLines("Noted. I've removed this task: ");
            res.addNewLines(tasks.get(c - 1).getStatus());
            tasks.remove(c - 1);
            res.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
        }
        else if(cmd.length() >= 4 && cmd.substring(0, 4).equals("find")) {
            String tmp = cmd.substring(5);
            res.addNewLines("Here are the matching tasks in your list:");
            for(int i = 1; i <= tasks.getSize(); ++i) if(tasks.get(i - 1).description.contains(tmp)){
                res.addNewLines(i + "." + tasks.get(i - 1).getStatus());
            }
            // find without index
            List<Task> containsList = tasks.getArrayList().stream().filter(n -> n.description.contains(tmp))
                    .collect(Collectors.toList());
        }
        else if(cmd.length() >= 8 && cmd.substring(0, 8).equals("dowithin")) {
            //void parse(String cmd) {
            String getName = "";
            String from = "";
            String to = "";
            for(int i = 9; i < cmd.length(); ++i) {
                if(cmd.substring(i, i + 10).equals(" /between ")) {
                    getName = cmd.substring(9, i);
                    for(int j = i + 10; j < cmd.length(); ++j) {
                        if(cmd.substring(j, j + 6).equals(" /and ")) {
                            from = cmd.substring(i + 10, j);
                            to = cmd.substring(j + 6);
                            break;
                        }
                    }
                    break;
                }
            }
            DoWithinPeriodTasks tmp = new DoWithinPeriodTasks(getName, from, to);
            res.addNewLines("Got it. I've added this task: ");
            res.addNewLines("  " + tmp.getStatus());
            tasks.add(tmp);
            res.addNewLines("Now you have " + tasks.getSize() + " tasks in the list.");
            //}
        }
        else res.addNewLines("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        storage.updateDataFile(tasks.getArrayList());
        return res.toString();
    }

    public void run() {
        ui.sayHi();
        Scanner myScanner = new Scanner(System.in);
        while(true) {
            String cmd = myScanner.nextLine();
            solveInput(cmd);
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
