import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main Duke class.
 */
public class Duke {
    private static final String FILEPATH = "data/tasks.txt";

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
            ui.sayErrorMessage(e);
            ui.informFileNotFound();
            this.tasks = new TaskList();
        }
    }

    private void run() {
        ui.sayGreetings();
        boolean isBye = false;
        while (!isBye) {
            String userInput = ui.receiveUserInput();
            try {
                Parser.parseInput(userInput, ui, tasks, storage);
            } catch (DukeException | IOException e) {
                ui.sayErrorMessage(e);
            } finally {
                isBye = Parser.isBye(userInput);
            }
        }
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

    public String getGreetings() {
        String greetings = ui.sayGreetings();
//        greetings += ui.showGreetings();
        return greetings;
    }
}