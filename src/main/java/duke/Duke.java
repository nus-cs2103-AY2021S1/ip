package duke;

import util.DukeException;
import util.Parser;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Driver to run the Duke app.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates new Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.readFile());
    }

    /**
     * Runs the Duke app.
     */
    public void run() {

        ui.printWelcome();

        String input = ui.readLine();

        while (!input.equals("bye")) {
            try {
                Parser.parse(input, taskList, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                ui.printLine();
            } finally {
                input = ui.readLine();
            }
        }

        storage.saveFile(taskList.getList());

        ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.printLine();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
