public class SortCommand extends Command {
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTasksByDate();
        return ui.showSortedList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
