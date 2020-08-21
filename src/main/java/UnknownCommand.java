package main.java;

public class UnknownCommand extends Command {
    public UnknownCommand(String[] command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new CommandException();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
