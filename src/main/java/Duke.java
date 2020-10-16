import java.time.DateTimeException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

import java.io.IOException;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {
    private boolean isClosed = false;

    private Storage db;
    private Parser parser;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() throws IOException {
        try {
            db = new Storage();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }

        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Returns List of tasks.
     *
     * @return List of tasks
     */
    public List<Task> getToDoLst() {
        return db.getToDoLst();
    }

    /**
     * Returns description of total number of items in todo list.
     *
     * @return description of total number of items in todo list.
     */
    public String getTotalItemsDescription() {
        return db.getTotalItemsDescription();
    }

    /**
     * Returns description of total number of items in todo list.
     *
     * @param i index of Task in todo list
     * @param bool new isDone status to set in task
     *
     * @return updated task
     */
    public Task setToDoItemStatus(int i, boolean bool) {
        Task task = getToDoLst().get(i);

        task.setStatus(bool);

        return task;
    }

    /**
     * Add new task to todo list.
     *
     * @param type type of task
     * @param todo description of task
     * @param date deadline of task (LocalDate)
     *
     * @return new task
     */
    public Task addToDoItem(String type, String todo, LocalDate date) {
        Task newTask = null;

        if (type.equals("todo")) {
            newTask = new Task("T", todo, null,false);
        } else if (type.equals("deadline")) {
            newTask = new Task("D", todo, date, false);
        } else if (type.equals("event")) {
            newTask = new Task("E", todo, date, false);
        }

        boolean hasDuplicates = db.addToDoItem(newTask);

        if (hasDuplicates) {
            showDuplicateTaskMessage(newTask);
        }

        return newTask;
    }

    /**
     * Remove task from todo list.
     *
     * @param i index of Task in todo list
     *
     * @return deleted task
     */
    public Task removeToDoItem(int i) {
        Task deletedTask = db.removeToDoItem(i);

        return deletedTask;
    }

    /**
     * Save tasks to storage.
     *
     * @throws IOException
     */
    public void save() throws IOException {
        try {
            db.save();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
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

        // Welcome user
        showWelcomeMessage();

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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String line = userInput.getText();
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));


        if (!this.isClosed) {
            if (line.equals("bye")) {
                showByeMessage();
                this.isClosed = true;

                try {
                    save();
                    System.exit(0);
                } catch (IOException e) {
                    showExceptionMessage(e);
                }
            } else if (line.equals("list")) {
                showListItems(getToDoLst(), "list");
            } else if (line.split(" ")[0].equals("find")) {
                String searchTerm = parser.processFind(line);

                List<Task> tasks = db.searchToDoItems(searchTerm);

                showListItems(tasks, "search");
            } else if (parser.isTaskModification(line.split(" ")[0])) {
                String[] processedData = parser.processModification(line);
                String type = processedData[0];
                int i = Integer.valueOf(processedData[1]);

                if (type.equals("done")) {
                    Task updatedTask = setToDoItemStatus(i, true);

                    showDoneMessage(updatedTask);
                } else {
                    Task deletedTask = removeToDoItem(i);

                    showDeleteMessage(deletedTask);
                    showtotalItemsDescription();
                }
            } else {
                String[] lineData = parser.processTaskItem(line);
                String type = lineData[0];

                if (type.equals("todo")) {
                    if (lineData.length == 1) {
                        showErrorMessage();
                    } else {
                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length));

                        Task newTask = addToDoItem(type, line, null);

                        showAddMessage(newTask);
                        showtotalItemsDescription();
                    }
                } else if (type.equals("deadline") || type.equals("event")) {
                    int byIndex = -1;

                    for (int i = 0; i < lineData.length; i++) {
                        if (lineData[i].equals("/by") || lineData[i].equals("/at")) {
                            byIndex = i;
                            break;
                        }
                    }

                    String dateStr = lineData[byIndex + 1];

                    try {
                        LocalDate date = LocalDate.parse(dateStr);

                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, byIndex));

                        Task newTask = addToDoItem(type, line, date);

                        showAddMessage(newTask);
                        showtotalItemsDescription();
                    } catch (DateTimeException e){
                        showWrongInputMessage();
                    }
                } else {
                    showWrongInputMessage();
                }
            }
        }

        userInput.clear();
    }

    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        Label welcomeText = new Label("Hello! I'm Duke\nWhat can I do for you?");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, new ImageView(duke)));
    }

    /**
     * Shows bye message.
     */
    public void showByeMessage() {
        Label welcomeText = new Label("Bye. Hope to see you again soon!");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, new ImageView(duke)));
    }

    /**
     * Shows done message.
     */
    public void showDoneMessage(Task task) {
        Label welcomeText = new Label(String.format("Nice! I've marked this task as done:\n%s", task));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeText, new ImageView(duke)));
    }

    /**
     * Shows delete message.
     */
    public void showDeleteMessage(Task task) {
        Label deleteText = new Label(String.format("Noted. I've removed this task:\n%s", task));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(deleteText, new ImageView(duke)));
    }

    /**
     * Shows number of list items.
     */
    public void showtotalItemsDescription() {
        String totalItemsDescription = getTotalItemsDescription();
        Label totalItemsDescriptionText = new Label(totalItemsDescription);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(totalItemsDescriptionText, new ImageView(duke)));
    }

    /**
     * Shows list items.
     */
    public void showListItems(List<Task> lst, String action) {
        Label listItemsText = new Label(ui.formatListItems(lst, action));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(listItemsText, new ImageView(duke)));
    }

    /**
     * Shows error message.
     */
    public void showErrorMessage() {
        Label listItemsText = new Label("☹ OOPS!!! The description of a todo cannot be empty.");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(listItemsText, new ImageView(duke)));
    }

    /**
     * Shows exception message.
     */
    public void showExceptionMessage(Exception e) {
        Label exceptionText = new Label(String.format("Error: %s", e.getMessage()));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(exceptionText, new ImageView(duke)));
    }

    /**
     * Shows add message.
     */
    public void showAddMessage(Task task) {
        Label listItemsText = new Label(String.format("Got it. I've added this task:\n%s", task));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(listItemsText, new ImageView(duke)));
    }

    /**
     * Shows wrong input message.
     */
    public void showWrongInputMessage() {
        Label listItemsText = new Label("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(listItemsText, new ImageView(duke)));
    }

    /**
     * Shows duplicate task message.
     */
    public void showDuplicateTaskMessage(Task task) {
        Label listItemsText = new Label(String.format("Duplicate task detected: %s", task));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(listItemsText, new ImageView(duke)));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Run.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String line = "";

        ui.showWelcomeMessage();

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    ui.showListItems(getToDoLst(), "list");
                } else if (line.split(" ")[0].equals("find")) {
                    String searchTerm = parser.processFind(line);

                    List<Task> tasks = db.searchToDoItems(searchTerm);

                    ui.showListItems(tasks, "search");
                } else if (parser.isTaskModification(line.split(" ")[0])) {
                    String[] processedData = parser.processModification(line);
                    String type = processedData[0];
                    int i = Integer.valueOf(processedData[1]);

                    if (type.equals("done")) {
                        Task updatedTask = setToDoItemStatus(i, true);

                        ui.showDoneMessage();
                        System.out.println(updatedTask);
                    }  else {
                        Task deletedTask = removeToDoItem(i);

                        ui.showDeleteMessage();
                        System.out.println(deletedTask);
                        System.out.println(getTotalItemsDescription());
                    }
                } else {
                    String[] lineData = parser.processTaskItem(line);
                    String type = lineData[0];

                    if (type.equals("todo")) {
                        if (lineData.length == 1) {
                            ui.showErrorMessage();
                        } else {
                            line = String.join(" ", Arrays.copyOfRange(lineData, 1, lineData.length));

                            Task newTask = addToDoItem(type, line, null);

                            ui.showAddMessage();
                            System.out.println(newTask);
                            System.out.println(getTotalItemsDescription());
                        }
                    } else if (type.equals("deadline") || type.equals("event")) {
                        int byIndex = -1;

                        for (int i = 0; i < lineData.length; i++) {
                            if (lineData[i].equals("/by") || lineData[i].equals("/at")) {
                                byIndex = i;
                                break;
                            }
                        }

                        String dateStr = lineData[byIndex + 1];
                        LocalDate date = LocalDate.parse(dateStr);

                        line = String.join(" ", Arrays.copyOfRange(lineData, 1, byIndex));

                        Task newTask = addToDoItem(type, line, date);

                        ui.showAddMessage();
                        System.out.println(newTask);
                        System.out.println(getTotalItemsDescription());
                    } else {
                        ui.showWrongInputMessage();
                    }
                }
            }

            line = scanner.nextLine();
        }

        try {
            save();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        ui.showByeMessage();
    }

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        Duke duke = null;

        assert duke == null;

        try {
            duke = new Duke();
        } catch (FileNotFoundException e) {
            System.out.println("Data directory or file not found, see error");

            System.out.println(e.getMessage());

            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return;
        }

        duke.run();
    }
}
