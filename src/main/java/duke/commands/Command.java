package duke.commands;

import duke.Ui;
import duke.tasks.TaskManager;
import duke.DukeException;

import java.util.Scanner;

/**
 * duke.commands.Command is the abstract base class for all
 * duke.commands
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
     * Abstract method to be implemented by child command classes.
     * Different child command classes will have different behaviours
     * when the method <code>execute</code> is called.
     * @return a boolean value indicating whether or not the program should continue running.
     * @throws DukeException if an exception has occured while executing the command.
     */
    public abstract boolean execute(String input) throws DukeException;

    public abstract void init(TaskManager tm, Ui ui);

    protected void incrementStage() {
        stage++;
    }

    protected void setResponse(String response) {
        this.response = response;
    }

    public boolean isDone() {
        return isDone;
    };

    protected void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return response;
    }
}