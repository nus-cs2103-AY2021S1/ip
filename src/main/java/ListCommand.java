public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        taskList.listAllTasks();
    }
}
