public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyFind(taskManager, taskManager.findTaskThatHasKeyword(keyword));
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
