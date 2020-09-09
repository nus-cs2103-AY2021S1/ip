package duke;

import duke.command.Command;

/**
 * Represents a Personal Assistant Chatbot, Duke, that helps a person to keep track of various tasks.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Creates a new Duke Chatbot.
     * If there is an existing file in the filepath, previous tasks (if any) will be loaded.
     *
     * @param filePath the filepath where tasks are loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        System.out.println("\nLoading previous tasks....");
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.generateErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke Chatbot.
     * If an Exit command is entered, Duke terminates.
     */
    public void run() {
        System.out.println(ui.generateDividers(ui.generateWelcomeMessage()));
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                System.out.println(ui.generateDividers(c.executeCommand(tasks, ui, storage)));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.generateDividers(ui.generateErrorMessage(e)));
            }
        }
    }

    /**
     * Retrieves Duke's Response for the GUI based on the specified input.
     *
     * @param input the specified input.
     * @return Duke's response.
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return new Response(c.executeCommand(tasks, ui, storage), c.isExit());
        } catch (DukeException e) {
            return new Response(ui.generateErrorMessage(e), false);
        }
    }

    /**
     * Runs the program via CLI.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
