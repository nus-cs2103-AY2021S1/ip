package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a task manager bot named Duke.
 */
public class Duke{

    private duke.Storage storage;
    private duke.TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/ethan.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/anotherEthan.png"));

    public Duke(String filepath) throws IOException {
        ui = new Ui();
        storage = new duke.Storage(filepath);
        try {
            tasks = new duke.TaskList(storage.loadTask());
        } catch (duke.DukeException e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    public Duke() {

    }

    public static void main(String[] args) throws duke.DukeException, IOException {
        new Duke("data/tasks.txt").runBot();
    }

    public void runBot() throws duke.DukeException, FileNotFoundException {
        ui.introduce();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Parser parser = new Parser();
            parser.interpret(input, tasks, storage);
            input = sc.nextLine();
        }
        ui.printExit();
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
