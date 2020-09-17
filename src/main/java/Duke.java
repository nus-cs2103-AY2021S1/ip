package main.java;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Scanner;

/**
 * Represents a robot who can help the user to make todo list.
 * A <code>Duke</code> object is an instance of such robots.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().
            getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().
            getResourceAsStream("/images/DaDuke.png"));

    Scanner sc = new Scanner(System.in);

    public Duke() {
        ui = new Ui();
        storage = new Storage("./command.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * launch the Duke application, initialize the robot.
     */
    public void run() {
        ui.start();
        boolean flag = true;
        Parser parser = new Parser(tasks, true);
        while(flag) {
            String input = sc.nextLine();
            System.out.println(parser.handleCommand(input));
            flag = parser.getFlag();
        }
    }

    /**
     * Main method of the project, launch the project.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./command.txt").run();
    }


    public String getResponse(String input) {
        Parser parser = new Parser(tasks, true);
        return parser.handleCommand(input);
    }
}

