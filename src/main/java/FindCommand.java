public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList foundTasks = tasks.findTasks(keyword);

        if (foundTasks.getSize() == 0) {
            throw new DukeException("There are no matching tasks in your list!\n"
                    + "Can you try a different keyword?");
        }

        ui.showFind();
        foundTasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
