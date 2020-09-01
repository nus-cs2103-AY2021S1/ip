public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyList(taskManager);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
