package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * <h1>duke.Duke!</h1>
 * The duke.Duke program implements an application that
 * helps you keep track of your tasks.
 *
 * @author Augustine Kau
 * @version 0.1
 * @since 2020-08-18
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList ls;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for duke.Duke
     * @param filePath For file todolist.txt.
     * @throws IOException On input error.
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        ls = new TaskList(storage.loadFile());
        ui = new Ui();
    }

    public Duke() {
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        //Step 2. Formatting the window to look as expected
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Command that user can input
     */
    enum Command {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE, CHECK, FIND
    }

    /**
     * This method is used to initiate the chat bot.
     * @throws IOException On input error.
     */
    public void run() throws IOException {
        ui.showWelcome();
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        WhileLoop:
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] arr = str.split(" ", 2);

            String upperCaseCmd = Parser.parseCmd(arr[0]);
            Command cmd;
            try {
                cmd = Command.valueOf(upperCaseCmd);
            } catch (IllegalArgumentException ex) {
                ui.invalidInput();
                continue;
            }

            switch (cmd) {
            case BYE:
                ui.showBye();
                // rewrite the file to update latest changes
                Storage.saveFile(this.storage.getFile(), ls);
                break WhileLoop;
            case LIST:
                if (ls.isEmpty()) {
                    ui.showListNoTask();
                } else {
                    ui.showListTask();
                    for (int i = 0; i < ls.size(); i++) {
                        Task task = ls.get(i);
                        int num = i + 1;
                        System.out.println(num + ". " + task.toString());
                    }
                    ui.horizontalDiv();
                }
                break;
            case DONE:
                try {
                    int numToBeMarkedAsDone = Parser.parseInt(str);
                    Task tsk = ls.get(numToBeMarkedAsDone);
                    tsk.markAsDone();
                    ls.set(numToBeMarkedAsDone, tsk);
                    ui.showDoneMsg(tsk.toString());
                } catch (Exception ex) {
                    ui.showDoneError();
                }
                break;
            case TODO:
                try {
                    Task newTask = new Todo(arr[1]);
                    ls.add(newTask);
                    ui.showTodoMsg(ls, newTask);
                } catch (Exception ex) {
                    ui.showTodoError();
                }
                break;
            case DEADLINE:
                try {
                    String[] arrOfStr = Parser.parse(arr, 1);
                    try {
                        Task newTask = new Deadline(arrOfStr[0], arrOfStr[1]);
                        ls.add(newTask);
                        ui.showDeadlineEventMsg(ls, newTask);
                    } catch (Exception ex) {
                        ui.showDeadlineFormatError();
                    }
                } catch (Exception ex) {
                    ui.showDeadlineError();
                }
                break;
            case EVENT:
                try {
                    String[] arrOfStr = Parser.parse(arr, 2);
                    try {
                        Task newTask = new Event(arrOfStr[0], arrOfStr[1]);
                        ls.add(newTask);
                        ui.showDeadlineEventMsg(ls, newTask);
                    } catch (Exception ex) {
                        ui.showEventFormatError();
                    }
                } catch (Exception ex) {
                    ui.showEventError();
                }
                break;
            case DELETE:
                try {
                    int numToBeDeleted = Parser.parseInt(str);
                    Task tsk = ls.get(numToBeDeleted);
                    ls.remove(numToBeDeleted);
                    ui.showDeleteMsg(ls, tsk);
                } catch (Exception ex) {
                    ui.showDeleteError();
                }
                break;
            case CHECK:
                try {
                    String checkDate = arr[1];
                    LocalDate date = LocalDate.parse(checkDate);
                    if (ls.isEmpty()) {
                        ui.showCheckNoTask();
                    } else {
                        ui.horizontalDiv();
                        int counter = 0;
                        for (int i = 0; i < ls.size(); i++) {
                            Task task = ls.get(i);
                            if (task instanceof Event) {
                                if (((Event) task).getAt().equals(date) && !task.isDone()) {
                                    counter += 1;
                                    System.out.println(counter + ". " + task.toString());
                                }
                            } else if (task instanceof Deadline && !task.isDone()) {
                                if (((Deadline) task).getDate().equals(date)) {
                                    counter += 1;
                                    System.out.println(counter + ". " + task.toString());
                                }
                            }
                        }
                        ui.showCheckTask(counter, date);
                    }
                } catch (Exception ex) {
                    ui.showCheckError();
                }
                break;
            case FIND:
                try {
                    String keyword = arr[1];
                    if (ls.isEmpty()) {
                        ui.showCheckNoTask();
                    } else {
                        ui.horizontalDiv();
                        int counter = 0;
                        for (int i = 0; i < ls.size(); i++) {
                            Task task = ls.get(i);
                            if (task.getDescription().contains(keyword)) {
                                counter++;
                                System.out.println(counter + ". " + task.toString());
                            }
                        }
                        ui.showFindTask(counter, keyword);
                    }
                } catch (Exception ex) {
                    ui.showFindError();
                }
                break;
            default:
                ui.invalidInput();
                break;
            }
        }
    }

    /**
     * This is the main method which will create an instance of duke.Duke
     * and call on the method run.
     *
     * @param args Unused.
     * @throws IOException On input error.
     */
    public static void main(String[] args) throws IOException {
        new Duke(Storage.getFilePath()).run();
    }
}
