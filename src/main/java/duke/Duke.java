package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a task manager bot named Duke.
 */
public class Duke {

    private duke.Storage storage;
    private duke.TaskList tasks;
    private Ui ui;

    public Duke(String filepath) throws IOException, duke.DukeException {
        ui = new Ui();
        storage = new duke.Storage(filepath);
        try {
            tasks = new duke.TaskList(storage.loadTask());
        } catch (duke.DukeException e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
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
}
