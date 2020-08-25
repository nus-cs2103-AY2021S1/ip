package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String dataDirectory, String filePath) {
        ui = new Ui();
        storage = new Storage(dataDirectory, filePath);
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Duke("data", "./data/duke.txt").run();
    }

    /**
     * Runs the command-line interface for the Duke program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String fullCommand;
        boolean isExit = false;

        ui.greet();
        while(!isExit) {
            try {
                fullCommand = sc.nextLine().trim();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = fullCommand.equalsIgnoreCase("bye");
            } catch(DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }
}

