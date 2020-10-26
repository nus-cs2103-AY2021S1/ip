package duke;

import duke.command.Command;
import duke.util.AliasMap;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents Duke, a virtual task management system and personal assistant.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final AliasMap aliasMap;
    private boolean isQuitting;

    /**
     * Initializes a newly created Duke program with a saved file path.
     *
     * @param filePath path of the saved tasks file.
     */
    public Duke(String filePath) {

        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isQuitting = false;

        this.aliasMap = new AliasMap();
        this.aliasMap.populate();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String input) {

        assert input != null;
        try {
            Command c = Parser.parse(input, this.aliasMap);
            return c.execute(this.tasks, this.ui, this.storage, this.aliasMap);
        } catch (DukeException e) {
            return "Ex: " + e.getMessage();
        }
    }

    public String getGreeting() {
        return this.ui.greet();
    }
}
