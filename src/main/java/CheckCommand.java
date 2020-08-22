public class CheckCommand implements Command {

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.checkDate(command.substring(5));
    }
}
