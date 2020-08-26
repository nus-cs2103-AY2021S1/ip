public class AddCommand implements Command {

    private final String type;

    private final String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = TaskList.createTask(type, description);
            tasks.add(task);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("add", task);
            msg += "\n" + ui.getTaskReportMessage(tasks.size());
            ui.sendMessage(msg);
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
        }
    }
}
