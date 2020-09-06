package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object with filePath of data\tasks.csv
     */
    public Duke() {
        this.storage = new Storage("data/tasks.csv");
        this.tasks = new TaskList(this.storage.read());
        this.ui = new Ui();
    }

    /**
     * Constructs a Duke object with the filePath specified
     * @param filePath specifies the directory of the csv file to read from/write to
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.read());
        this.ui = new Ui();
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                assert !input.isEmpty() || !input.equals("");
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns a String that represents Duke's response to user's input
     * @param input user's input
     * @return String that represents Duke's response
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            assert !ui.getMessage().equals("") || !ui.getMessage().isEmpty();
            response += ui.getLine();
            response += ui.getMessage();
            response += ui.getLine();
            ui.clearMessage();
            assert !response.equals("");
        } catch (DukeException e) {
            assert !e.getMessage().equals("");
            ui.showError(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.csv").run();
    }
}
