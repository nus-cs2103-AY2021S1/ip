public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task t = tasks.get(idx);
            t.setDone();
            ui.showDone(t);
        } catch (IndexOutOfBoundsException iooob) {
            throw new DukeException("I cannot check this element: " + idx);
        }
    }
}
