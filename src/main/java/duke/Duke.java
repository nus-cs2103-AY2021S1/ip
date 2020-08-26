package duke;

import duke.exec.Executable;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    // instance variables
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // private execution helper method
    /**
     * Runs the command-line interface for the Duke program.
     */
    private void execute() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        ui.greeting();

        while (!isExit) {
            try {
                String input = sc.nextLine().trim();
                Executable e = Parser.parse(input);
                e.run(tasks, ui, storage);
                isExit = input.equalsIgnoreCase("bye");
            } catch (DukeException e) {
                ui.display(e.getMessage());
            }
        }
    }

    // constructor
    public Duke(String directory, String path) {
        ui = new Ui();
        storage = new Storage(directory, path);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke("data", "./data/duke.txt").execute();
    }
}
