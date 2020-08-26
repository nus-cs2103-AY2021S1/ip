public abstract class Command {

    public abstract void execute(Storage storage, TaskList listOfTasks, Ui ui) throws InvalidInputException;

    public abstract boolean isExit();

}
