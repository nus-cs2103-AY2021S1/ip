public class FindCommand extends Command {
    private final String target;

    public FindCommand(String detail) {
        this.target = detail.trim();
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        boolean hasFound = false;

        int count = 1;
        for (Task task : list.getList()) {
            if (task.getName().contains(this.target)) {
                if (!hasFound) {
                    ui.showFind();
                    hasFound = true;
                }
                ui.showTask(count, task);
                count++;
            }
        }

        if(!hasFound) {
            ui.showNothingFound();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
