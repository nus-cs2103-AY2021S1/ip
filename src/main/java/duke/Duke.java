package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a task manager bot named Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Gui gui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() throws IOException {
        gui = new Gui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            gui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/tasks.txt").runBot();
    }

    public void runBot() throws DukeException, FileNotFoundException {
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

    public String getResponseGui(String input) throws FileNotFoundException, DukeException {
        GuiParser guiParser = new GuiParser();
        return guiParser.interpretGui(input, tasks, storage);
    }
}
