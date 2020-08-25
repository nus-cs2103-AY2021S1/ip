package commands;

import data.TaskList;
import exception.ChatbotException;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    /**
     * Returns true if this command instructs the program to exit.
     * @return if program should exits
     */
    abstract public boolean isExit();

    /**
     * Executes the command.
     * @param taskList the TaskList object for handling tasks
     * @param ui the UI object for text-ui display
     * @param storage the Storage object for persistence
     * @throws ChatbotException if execution unsuccessful
     */
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException;
}
