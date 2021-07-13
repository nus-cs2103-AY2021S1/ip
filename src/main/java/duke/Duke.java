package duke;

import duke.command.Command;

/**
 * Represents a Personal Assistant Chat bot, Duke, that helps a person to keep track of various tasks.
 */
public class Duke {
    /** The Storage used for loading and saving tasks */
    private final Storage storage;
    /** The Ui used for user interaction */
    private final Ui ui;
    /** The list of tasks */
    private TaskList tasks;

    /**
     * Creates a new Duke Chat bot.
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
            System.out.println(ui.generateErrorMessage(e));
        }
    }

    /**
     * Runs the Duke Chat bot.
     * If an Exit command is entered, Duke terminates.
     */
    public void run() {
        String welcomeMessage = ui.generateDividers(
                ui.generateWelcomeMessage());
        System.out.println(welcomeMessage);
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();

            try {
                Command c = Parser.parse(fullCommand);
                String message = ui.generateDividers(
                        c.executeCommand(tasks, ui, storage));
                System.out.println(message);
                isExit = c.isExit();
            } catch (DukeException e) {
                String errorMessage = ui.generateDividers(
                        ui.generateErrorMessage(e));
                System.out.println(ui.generateDividers(
                        errorMessage));
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
            return new Response(c.executeCommand(tasks, ui, storage),
                    c.isExit());
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
