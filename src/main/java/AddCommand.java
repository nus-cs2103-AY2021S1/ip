public class AddCommand implements Command {
    Task t;

    AddCommand(Task t) {
        this.t = t;
    }

    public void execute(String command, Storage s, Ui ui) throws DukeException {
        s.save(t);
    }
}
