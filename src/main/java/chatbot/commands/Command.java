package chatbot.commands;

import chatbot.data.TaskList;
import chatbot.exception.ChatbotException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

public abstract class Command {
    /**
     * Returns true if this command instructs the program to exit.
     * @return if program should exits
     */
    public abstract boolean isExit();

    /**
     * Executes the command.
     * @param taskList the TaskList object for handling tasks
     * @param ui the UI object for text-ui display
     * @param storage the Storage object for persistence
     * @return String response text from executing the command
     * @throws ChatbotException if execution unsuccessful
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException;
}
