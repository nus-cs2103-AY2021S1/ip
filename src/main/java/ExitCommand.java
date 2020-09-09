/**
 * Represents an exit command. Program will exit after the command is executed.
 */
public class ExitCommand extends Command {

    /**
     * Public constructor.
     */
    public ExitCommand() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "exit";
    }
}
