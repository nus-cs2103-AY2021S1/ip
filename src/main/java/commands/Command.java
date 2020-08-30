package commands;

import java.util.Scanner;

/**
 * commands.Command is the abstract base class for all
 * commands
 */
public abstract class Command {
    TaskManager tm;
    Ui ui;
    Scanner sc;

    /**
     * Sets utility tools that will be used by child command classes.
     *
     * @param tm the task manager.
     * @param ui the ui object that will output the user interface.
     * @param sc the scanner object to receive user inputs.
     */
    public void setUtility(TaskManager tm, Ui ui, Scanner sc) {
        this.tm = tm;
        this.ui = ui;
        this.sc = sc;
    }

    /**
     * Abstract method to be implemented by child command classes.
     * Different child command classes will have different behaviours
     * when the method <code>execute</code> is called.
     * @return a boolean value indicating whether or not the program should continue running.
     * @throws DukeException if an exception has occured while executing the command.
     */
    public abstract boolean execute() throws DukeException;
}