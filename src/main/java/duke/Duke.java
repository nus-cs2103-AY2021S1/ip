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
            ui.displayLine();
            try {
                System.out.println(parser.command(str.trim(), tasks));
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            ui.displayLine();
            str = sc.nextLine();
        }
        sc.close();
        storage.save(tasks);
        System.out.println(ui.exit());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Returns Duke's response to display in the GUI.
     * @param input user input in the dialog box.
     * @return Duke's reply to the user input
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.save(tasks);
            return ui.exit();
        } else {
            try {
                return parser.command(input.trim(), tasks);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}


