package duke.task;

/**
 * Encapsulates the chat bot program.
 * Yoo is a chat bot program you can use to record tasks.
 * You can add three types of tasks: todo, deadline and event.
 * You can add and delete tasks using commands.
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
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chat bot program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                isExit = Parser.parse(command, tasks, ui, storage);
            } catch (YooException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}