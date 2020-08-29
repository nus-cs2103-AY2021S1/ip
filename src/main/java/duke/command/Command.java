package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected String task;
    protected Boolean isExit;

    /**
     * Constructs command
     * @param task Task description.
     */
    public Command(String task) {
        this.task = task;
        this.isExit = false;
    }

    /**
     * Constructs command.
     * @param task Task description.
     * @param isExit Exit indicator.
     */
    public Command(String task, Boolean isExit) {
        this.task = task;
        this.isExit = isExit;
    }

    /**
     * Constructs command.
     * @param isExit Exit indicator.
     */
    public Command(Boolean isExit) {
        this.task = null;
        this.isExit = isExit;
    }

    /**
     * Constructs command.
     */
    public Command() {
        this.task = null;
        this.isExit = false;
    }

    /**
     * Determines whether the bot should exit or not.
     * @return true if the command is "exit" and false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the user's command.
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns this command object.
     * @return this command.
     */
    public String getTask() {
        return this.task;
    }

}
