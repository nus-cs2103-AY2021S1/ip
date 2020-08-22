public class DeleteCommand implements Command {

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.delete(command);
    }
}
