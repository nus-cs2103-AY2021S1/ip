package luke;

import luke.commands.Command;
import luke.exception.LukeException;

public class Luke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Luke object with the given filepath.
     */
    public Luke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (LukeException e) {
            this.tasks = new TaskList();
        }
        this.ui = new Ui();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(this.storage, this.tasks, this.ui);
        } catch (LukeException e) {
            return ui.showError(e);
        }
    }

    public Ui getUi() {
        return this.ui;
    }
}