package duke;

import java.io.File;
import java.util.Scanner;

import duke.controllers.DialogBox;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {
    private TaskList tasks;
    // Variables for JavaFX
    @FXML
    private ScrollPane scrollPane; // msg get too long
    @FXML
    private VBox dialogContainer; // Lays out children in a vertical column
    @FXML
    private TextField userInput; // Receive input
    @FXML
    private Button sendButton; // the button that says Send for user
    private Scene scene; // The final scene that gets shown
    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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

    // start() method moved to class MainGUI
    // And also handleUserInput()

    public String getResponse(String userText) {
        return "Haha " + userText + " makes Hal9000 go brrrrr";
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
