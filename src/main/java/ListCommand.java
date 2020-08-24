public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
