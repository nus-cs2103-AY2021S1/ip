package main.java;

/**
 * ExitCommands tell the chatbot to exit the main loop
 *
 * @author Lio
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
