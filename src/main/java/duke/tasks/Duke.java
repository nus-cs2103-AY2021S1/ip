package duke.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the Duke Program. Helps manage user's tasks and keeps them in check.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private Parser parser;

    /**
     * Creates the Duke object with its attributes.
     * @throws FileNotFoundException File containing list of task may not be found.
     */
    public Duke() throws FileNotFoundException {
        ui = new UI();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage = new Storage(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) throws IOException, DukeException {
        Command resultCommand = parser.parse(input);
        return resultCommand.execute(tasks, ui, storage);
    }



    public static void main(String[] args) throws DukeException, IOException {
    }


}
