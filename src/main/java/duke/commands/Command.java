package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the user command. */
public abstract class Command {

    /** Duke's reply to the user input. */
    protected String dialog;

    /** Executes the command. The child classes will implement this method.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /** Gets Duke's reply to the user input.
     * @return Duke's reply to the user input.
     */
    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    /** Indicates whether the program is terminating. */
    public boolean isExit() {
        return false;
    }
}
