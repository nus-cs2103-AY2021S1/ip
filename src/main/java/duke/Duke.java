package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The main program of Duke.
 */

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
    }

    public void start() {
        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.load());
        new Alert(Alert.AlertType.NONE, ui.welcome(), ButtonType.OK).showAndWait();
    }

    /**
     * Starts the main Duke program.
     * Receive and process user input until user keys "bye".
     */
    public void run() {
        storage = new Storage("data/duke.txt");
        System.out.println(ui.welcome());
        tasks = new TaskList(storage.load());
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            ui.line();
            try {
                System.out.println(parser.command(str, tasks, storage));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            ui.line();
            str = sc.nextLine();
        }
        sc.close();
        System.out.println(ui.exit());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    protected String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.exit();
        } else {
            try {
                return parser.command(input, tasks, storage);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}


