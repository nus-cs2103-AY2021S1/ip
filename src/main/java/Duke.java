public class Duke {
    private Ui ui;

    Duke(String filepath) {
        Storage storage = new Storage(filepath);
        this.ui = new Ui(new TaskList(storage.load()), storage);
    }

    /**
     * Runs the Duke bot to process input from user and output the response.
     */
    public void run() throws DukeException {
        ui.getInput();
    }

    public String getResponse(String input) {
        return "Test";
    }

    public static void main(String[] args) throws DukeException {
        new Duke("ip_data.txt").run();
    }
}
