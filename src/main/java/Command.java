abstract public class Command {

    public abstract void execute(Tasklist tasklist, UserInterface ui) throws DukeListException, DukeIndexException;

}
