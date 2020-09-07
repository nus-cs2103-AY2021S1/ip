package duke.task;

/**
 * Encapsulates the chat bot program.
 * Yoo is a chat bot program you can use to record tasks.
 * You can add three types of tasks: todo, deadline and event.
 * You can add, find, and delete tasks using commands.
 * Terminate the program using the bye command.
 *
 * @author Jace Tan
 * @version 0.1
 * @since 2020-08-27
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the chat bot program.
     * @param filePath Path to the file that stores tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui(tasks);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String result;
        try {
            result = Parser.parse(input, tasks, ui, storage);
        } catch (YooException e) {
            result = e.getMessage();
        }
        return result;
    }
}
