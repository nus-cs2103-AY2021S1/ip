package seedu.duke;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Runs the Duke program, where user types in commands to add, delete, and mark tasks as done.
 */

public class Duke extends Application {

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);
    protected Ui ui;
    protected Storage storage;
    protected TaskList tasklist;
    private Image userProfile = new Image(("images/user.png"));
    private Image dukeProfile = new Image(("images/duke.png"));

    public Duke() {
        this.ui = new Ui();
        this.storage =  new Storage();
        try {
            this.tasklist = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showLoadingError();
            this.tasklist = new TaskList();
        }
    }

    /**
     * Runs the actual program.
     */
    public void run() {
        this.ui.start();

        boolean isExit = false;

        while(!isExit) {
            try {
                String line = this.ui.getUserCommand();
                this.ui.linePrinter();
                Command c = new Parser().parse(line);
                c.execute(this.tasklist, this.ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            } catch (DateTimeException e) {
                this.ui.showError("Can't read your date man. Put it like this ok? --> 25 Mar 2020 1930");
            } finally {
                if (! isExit) {
                    this.ui.linePrinter();
                }
            }
        }

        try {

            String tasks = "";

            for (Task t : this.tasklist.getList()) {
                String check = "";
                if (t.isComplete()) {
                    check = "\u2713";
                } else {
                    check = "\u2718";
                }
                String toAdd = t.getType() + "*" + check + "*" + t.toString();
                String addition = "";
                if (t.getTime() == null) {
                    addition = "\n";
                } else {
                    addition = "*" + t.getTime().format(FORMATTER) + "\n";
                }
                tasks = tasks + toAdd + addition;
            }

            this.storage.save(tasks);
        } catch (IOException e) {
            this.ui.showError("Whoops! Some kind of error :/ see here: " + e.getMessage());
        }
        this.ui.bye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        ByteArrayOutputStream str = new ByteArrayOutputStream();
        PrintStream printStr = new PrintStream(str);
        PrintStream oldOut = System.out;
        System.setOut(printStr);

        this.ui.start();

        System.out.flush();
        System.setOut(oldOut);

        Label welcomeText = new Label(str.toString());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeText, new ImageView(dukeProfile))
        );

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setTitle("Duke Task Scheduler :s");
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
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        AnchorPane.setRightAnchor(sendButton , 10.0);
        AnchorPane.setBottomAnchor(sendButton, 5.0);
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
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userProfile)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeProfile))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        ByteArrayOutputStream str = new ByteArrayOutputStream();
        PrintStream printStr = new PrintStream(str);
        PrintStream oldOut = System.out;
        System.setOut(printStr);

        boolean isExit = false;

        try {
            this.ui.linePrinter();
            Command c = new Parser().parse(input);
            c.execute(this.tasklist, this.ui);
            isExit = c.isExit();
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        } catch (DateTimeException e) {
            this.ui.showError("Can't read your date man. Put it like this ok? --> 25 Mar 2020 1930");
        } finally {
            if (!isExit) {
                this.ui.linePrinter();
            }
        }

        if (isExit) {
            try {

                String tasks = "";

                for (Task t : this.tasklist.getList()) {
                    String check = "";
                    if (t.isComplete()) {
                        check = "\u2713";
                    } else {
                        check = "\u2718";
                    }
                    String toAdd = t.getType() + "*" + check + "*" + t.toString();
                    String addition = "";
                    if (t.getTime() == null) {
                        addition = "\n";
                    } else {
                        addition = "*" + t.getTime().format(FORMATTER) + "\n";
                    }
                    tasks = tasks + toAdd + addition;
                }

                this.storage.save(tasks);
            } catch (IOException e) {
                this.ui.showError("Whoops! Some kind of error :/ see here: " + e.getMessage());
            }
            this.ui.bye();
        }

        System.out.flush();
        System.setOut(oldOut);
        return str.toString();
    }

}
