package main.java;

public class DoNothingCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
