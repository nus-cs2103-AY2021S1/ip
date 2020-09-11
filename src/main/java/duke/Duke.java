package duke;

/**
 * Main class that drives the application.
 */
class Duke {

    /**
     * Deals with input output of files.
     */
    private Storage storage;
    /**
     * Task list.
     */
    private TaskList tasks;
    /**
     * Client list.
     */
    private ClientList clients;
    /**
     * Deals with user input output.
     */
    private Ui ui;

    /**
     * Constructor for the main driver.
     *
     * @param filePath File path to load history, and to save history.
     */
    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            clients = new ClientList();
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
            clients = new ClientList();
        }
    }

    String getWelcome() {
        return ui.showWelcome();
    }

    /**
      * Generate a response to user input.
     */
    String getResponse(String input) {
        try {
            boolean isTaskCommand = Parser.isTaskCommand(input);
            if (isTaskCommand) {
                Command command = Parser.parseTaskCommand(input);
                return command.execute(tasks, ui, storage);
            } else {
                ClientCommand clientCommand = Parser.parseClientCommand(input);
                return clientCommand.execute(clients, ui, storage);
            }
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    boolean isExit(String input) {
        try {
            boolean isTaskCommand = Parser.isTaskCommand(input);
            if (isTaskCommand) {
                Command command = Parser.parseTaskCommand(input);
                return command.isExit();
            } else {
                ClientCommand clientCommand = Parser.parseClientCommand(input);
                return clientCommand.isExit();
            }
        } catch (DukeException e) {
            return false;
        }
    }
}
