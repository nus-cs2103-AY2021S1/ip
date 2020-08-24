public class ExitCommand extends Command {

    public void execute(TaskList list, Storage storage) {
        storage.save(list);
    }

    public void printFeedback(Ui ui) {
        ui.exit();
    }

    public boolean isExit() {
        return true;
    }
}
