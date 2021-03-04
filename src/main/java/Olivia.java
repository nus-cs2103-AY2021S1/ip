import olivia.logic.Parser;
import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.ui.Ui;
import olivia.util.OliviaException;

/**
 * Olivia is a bot that functions as a user's task manager.
 */

public class Olivia {

    private static final String FILEPATH = "./data/olivia.txt";

    private final Parser parser;
    private final Wrapper wrapper;

    /**
     * Constructor that creates a Olivia object.
     */

    public Olivia() {
        Storage storage = new Storage(FILEPATH);
        TaskList tasks = null;
        Ui ui = new Ui();
        try {
            tasks = TaskList.parse(storage.load());
            ui.showLoaded(tasks);
        } catch (OliviaException e) {
            tasks = new TaskList();
        } finally {
            this.wrapper = new Wrapper(storage, tasks, ui);
            this.parser = new Parser(wrapper);
        }
    }

    /**
     * Generates a welcome message for the user, where its content depends on
     * whether a text file can be found.
     * @return output String containing the welcome message.
     */

    public String welcome() {
        String ret = wrapper.getUi().welcome();
        try {
            TaskList tasks = TaskList.parse(wrapper.getStorage().load());
            ret += wrapper.getUi().showLoaded(tasks);
        } catch (OliviaException e) {
            ret += wrapper.getUi().showError(e);
        }
        return ret;
    }

    /**
     * Checks if Olivia should terminate.
     * @return a boolean determining if Olivia should terminate.
     */

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
