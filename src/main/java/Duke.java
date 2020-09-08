import duke.logic.Parser;
import duke.logic.Storage;
import duke.ui.Ui;
import duke.resource.TaskList;
import duke.resource.Wrapper;
import duke.util.DukeException;

/**
 * Duke is a bot that functions as a user's task manager.
 */

public class Duke {

    private static final String FILEPATH = "./src/main/data/duke.txt";

    private final Parser parser;
    private final Wrapper wrapper;

    /**
     * Constructor that creates a Duke object.
     */

    public Duke() {
        Storage storage = new Storage(FILEPATH);
        TaskList tasks = null;
        Ui ui = new Ui();
        ui.printWelcome();
        try {
            tasks = TaskList.parse(storage.load());
            ui.printLoaded(tasks);
        } catch (DukeException e) {
            tasks = new TaskList();
            ui.printError(e);
        } finally {
            this.wrapper = new Wrapper(storage, tasks, ui);
            this.parser = new Parser(wrapper);
        }
    }

    public String welcome() {
        String ret = wrapper.getUi().welcome();
        try {
            TaskList tasks = TaskList.parse(wrapper.getStorage().load());
            ret += wrapper.getUi().printLoaded(tasks);
        } catch (DukeException e) {
            ret += wrapper.getUi().showError(e);
        }
        return ret;
    }

    public boolean shouldExit() {
        return wrapper.isShouldExit();
    }

    public Parser getParser() {
        return this.parser;
    }

    public Storage getStorage() {
        return this.wrapper.getStorage();
    }

    public TaskList getTaskList() {
        return this.wrapper.getTaskList();
    }

    public Ui getUi() {
        return this.wrapper.getUi();
    }

}
