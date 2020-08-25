package main.java;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIndexOutOfBoundsException {
        ui.printOutList(tasks);
    }
}
