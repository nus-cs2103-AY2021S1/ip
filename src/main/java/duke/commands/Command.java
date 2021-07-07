package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;
import duke.DukeException;

/**
 * Command is the abstract base class for all commands.
 * Each command will have different stages in its life cycle and will always
 * be initialised with zero.
 * Each stage will have a different response to be displayed to the user.
 */
public abstract class Command {
    protected TaskManager tm;
    protected Ui ui;
    protected int stage = 0;
    protected boolean isDone = false;
    private String response;

    /**
     * Sets utility tools that will be used by child command classes.
     *
     * @param tm the task manager.
     * @param ui the ui object that will output the user interface.
     */
    public void setUtility(TaskManager tm, Ui ui) {
        this.tm = tm;
        this.ui = ui;
    }

    /**
     * Executes the command.
     * Different child command classes will have different behaviours
     * when the method <code>execute</code> is called.
     * @param input the user input.
     * @return <code>true</code>
     * @throws DukeException if an exception has occured while executing the command.
     */
    public abstract boolean execute(String input) throws DukeException;

    /**
     * Initialises the command.
     * Different child command classes will have different behaviours.
     * @param tm the task manager.
     * @param ui the ui.
     */
    public abstract void init(TaskManager tm, Ui ui);

    protected void incrementStage() {
        stage++;
    }

    protected void setResponse(String response) {
        this.response = response;
    }

    /**
     * Returns the value of <code>isDone</code>.
     * @return a boolean value.
     */
    public boolean isDone() {
        return isDone;
    }

    protected void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        assert (response != null) : "response has not been initialised";
        return response;
    }
}