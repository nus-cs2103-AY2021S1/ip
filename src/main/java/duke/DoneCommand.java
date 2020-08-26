package duke;


public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task completedTask = tasks.get(taskNumber);
            completedTask.markAsDone();
            storage.save(tasks);
            ui.display("Nice! I've marked this task as done:\n" + "  "
                    + completedTask.toDisplayString());
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
