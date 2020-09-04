package duke;

import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {
    private TaskList tasks;
    // Variables for JavaFX
    private ScrollPane scrollPane; // msg get too long
    private VBox vBox; // Lays out children in a vertical column
    private TextField userInput; // Receive input
    private Button sendButton; // the button that says Send for user
    private Scene scene; // The final scene that gets shown

    public Duke() {
        this.tasks = new TaskList();
    }

    public TaskList getTasks() {
        return tasks;
    }
    /**
     * op() is the main driver of Duke operations,
     * with a while loop that only terminates when user types bye;
     * Invoked in main()
     */
    public void op() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);

        while (!end) {
            String input = Ui.getInput(sc);
            String outputMsg = "";
            if (Parser.isBye(input)) {
                end = true;
            } else if (Parser.isList(input)) {
                outputMsg = getTasks().summarize();
            } else if (Parser.isDone(input)) {
                outputMsg = getTasks().markDone(Parser.getIndex(input));
            } else if (Parser.isDelete(input)) {
                outputMsg = getTasks().deleteTask(Parser.getIndex(input));
            } else if (Parser.isFind(input)) {
                outputMsg = getTasks().findTasksWith(Parser.getKeyword(input));
            } else {
                Task taskInput;
                try {
                    taskInput = Parser.parseTask(input); // catch duke exception from getTask(input)
                } catch (Exception e) {
                    System.out.println(Formatter.formatResponse(e.getMessage()));
                    continue;
                }
                outputMsg = getTasks().addTask(taskInput);
            }
            if (!end) {
                FormatPrinter.print(outputMsg);
            }
        }
        System.out.println(Formatter.formatResponse("Bye. Hope to see you again soon!\n"));
        sc.close();
    }

    /**
     * start() initiates the whole staging process.
     * @param stage Stage object
     */
    @Override
    public void start(Stage stage) {
        // Init / Setting up required components (aka Nodes)

        // Create scrollPane for scrolling content of chat
        // Input empty now. Can play around with input
        this.scrollPane = new ScrollPane();
        this.vBox = new VBox();
        scrollPane.setContent(vBox); // enables vBox scrolling

        this.userInput = new TextField();
        this.sendButton = new Button("Send"); // txt for button

        AnchorPane mainLayout = new AnchorPane(); // Initialize what will be shown
        // Add all nodes as children
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        this.scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * main() reads prevTasks.txt file and create
     * a Duke object with the getTasks() previously saved
     * Invokes op() function
     * After all are done, save undeleted getTasks() and exit.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String logo =
            "   __ _____   __  ___  ___  ___  ___\n"
            + "  / // / _ | / / / _ \\/ _ \\/ _ \\/ _ \\\n"
            + " / _  / __ |/ /__\\_, / // / // / // /\n"
            + "/_//_/_/ |_/____/___/\\___/\\___/\\___/\n";

        // Intro message
        System.out.println(logo);
        FormatPrinter.print(
            "Hello! I'm Hal9000\nWhat can I do for you?\n"
        );

        Duke hal9000 = new Duke();
        File prevTasks = FileOpener.openFile("prevTasks.txt");
        TaskLoader.loadTasks(prevTasks, hal9000.getTasks());
        hal9000.op();
        TaskStorage.saveTask(prevTasks, hal9000.getTasks());
    }

}
