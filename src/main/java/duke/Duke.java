package duke;

import duke.Command.Command;

import java.io.IOException;
import java.text.ParseException;

/**
 * The Duke class is the main class and drives the program
 */

public class Duke {
    private duke.Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a new driver system
     * @param filePath  the file path of the schedule list
     */
    public Duke(String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }


    private void run() throws IOException, DukeException {
        this.ui.showWelcome();
        String output = "";
        while (ui.sc.hasNext()) {
            ui.showLine();
            Command c = new Parser().parse(ui.sc.next());
            try {
                output = c.execute(ui, tasks, storage);
                System.out.println(output);
                if (output == "See you later alligator!") {
                    System.exit(0);
                }
            } catch (DukeException e) {
                output = e.getMessage();
                System.out.println(output);
            }
            ui.showLine();
        }
    }

    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(ui, tasks, storage);
        } catch (DukeException | IOException e) {
            output = (e.getMessage());
        }
        return output;
    }

    public static void main(String[] args) throws IOException, ParseException, DukeException {
        new Duke("src/main/java/duke/resources/todo.txt").run();
    }
}
