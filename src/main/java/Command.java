public abstract class Command {
    public abstract void execute(TaskList list) throws DukeException;

    public boolean isExit(){
        return false;
    }
}
