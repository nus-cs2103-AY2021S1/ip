package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private duke.Storage storage;
    private duke.TaskList tasks;
    private Ui ui;

    public Duke(String filepath) throws IOException, duke.DukeException {
        ui = new Ui();
        storage = new duke.Storage(filepath);
        try {
            tasks = new duke.TaskList(storage.load());
        } catch (duke.DukeException e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    public static void main(String[] args) throws duke.DukeException, IOException {
        new Duke("data/duke.txt").run();
    }

    public void run() throws duke.DukeException, FileNotFoundException {
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
