package main.java;

/**
 * Commands are executed by the chatbot
 *
 * @author Lio
 */
abstract class Command {
    /**
     * Executes the command
     *
     * @param tasks the chatbot's TaskList
     * @param ui the chatbot's Ui
     * @param storage the chatbot's Storage
     * @throws Exception Depending on the task
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Returns true if the chatbot is supposed to exit after this command
     */
    public boolean isExit() {
        return false;
    }
}
