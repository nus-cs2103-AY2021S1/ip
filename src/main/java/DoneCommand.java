public class DoneCommand implements Command {

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        int taskInd = Integer.parseInt(command.substring(5));
        s.markDone(taskInd - 1);
    }

}
