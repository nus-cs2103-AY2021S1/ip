public abstract class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException;
    abstract public boolean isExit();
}
