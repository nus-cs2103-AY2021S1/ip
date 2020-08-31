public class DeadlineCommand extends Command {
    private String taskName;
    private String by;

    public DeadlineCommand(String taskName, String by) {
        super();
        this.cmd = CMD.DEADLINE;
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("I PUT NEW TING IN DA LIST\n  " + taskList.addDeadline(this.taskName, this.by)
                + "\nNAO U HAS " + taskList.getNumberOfTasks() + " FINGS IN DA LIST LULZIES");
        storage.save(taskList);
    }
}
