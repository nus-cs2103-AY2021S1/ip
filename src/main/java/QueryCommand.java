public class QueryCommand extends Command {
    private CommandType commandType;

    public QueryCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
            String[] lines = taskList.getTasks();
            ui.showTasks(lines);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
