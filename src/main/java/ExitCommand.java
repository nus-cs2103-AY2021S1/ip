package main.java;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobException {

    }
    @Override
    public boolean isExit(){
        return true;
    }
}
