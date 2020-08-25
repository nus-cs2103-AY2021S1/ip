package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public String task;
    public Boolean isExit;

    public Command(String task) {
        this.task = task;
        this.isExit = false;
    }

    public Command(String task, Boolean isExit) {
        this.task = task;
        this.isExit = isExit;
    }

    public Command(Boolean isExit) {
        this.task = null;
        this.isExit = isExit;
    }

    public Command() {
        this.task = null;
        this.isExit = false;
    }

    /**
     * Determines whether the bot should exit or not.
     *
     * @return true if the command is "exit" and false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the user's command.
     *
     * @param taskList List of tasks.
     * @param ui       UI of the bot.
     * @param storage  Storage managing the file in hard disk.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns this command object.
     *
     * @return this command.
     */
    public String getTask() {
        return this.task;
    }

}