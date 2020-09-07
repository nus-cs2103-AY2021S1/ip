import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main Duke class.
 */
public class Duke {
    private static final String FILEPATH = "data/duke.txt";

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException | DukeException e) {
            ui.getError(e);
            ui.say("It seems like you have no saved files! Creating one now...");
            this.tasks = new TaskList();
        }
    }

    private void run() {
        ui.greet();
        boolean isBye = false;
        while (!isBye) {
            String input = ui.receiveInput();
            try {
                Parser.parseInput(input, ui, tasks, storage);
            } catch (DukeException | IOException e) {
                ui.getError(e);
            } finally {
                isBye = Parser.isBye(input);
            }
        }
        ui.goodbye();
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            System.exit(0);
        }
        try {
            return Parser.parseInput(input, ui, tasks, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}