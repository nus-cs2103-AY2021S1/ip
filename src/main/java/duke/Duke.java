package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates the Duke object.
     */
    public Duke() {
        String filePath = "data/duke.TaskList.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            startNewFile();
        }
    }

    /**
     * Starts a new file when the file does not contain any information or
     * does not exist.
     */
    public void startNewFile() {
        this.tasks = new TaskList();
        try {
            storage.newStorage();
        } catch (DukeException ex) {
            ui.printError(ex.getMessage());
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Returns a response to be displayed.
     *
     * @param input User input string.
     * @return The response.
     */
    public String getResponse(String input) {
        String output = null;
        try {
            Command command = Parser.parse(input);
            output = command.execute(ui, tasks, storage);

            if (command.isExit()) {
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        System.exit(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        } catch (DukeException e) {
            output = ui.printError(e.getMessage());
        }
        return output;
    }
}
