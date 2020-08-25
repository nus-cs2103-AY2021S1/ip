public class CheckCommand extends Command {
    private final String target;

    public CheckCommand(String detail) {
        this.target = detail;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showCheck();
        for (Task task : list.getList()) {
            if (task.getDate().equals(this.target)) {
                System.out.println(task);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
