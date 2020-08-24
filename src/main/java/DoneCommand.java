package main.java;

public class DoneCommand extends Command {
    int taskNumberToMark;

    public DoneCommand(int taskNumberToMark) {
        this.taskNumberToMark = taskNumberToMark;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumberToMark > tasks.getSize() | taskNumberToMark < 1) {
            throw new DukeException("There is no such task number.");
        } else {
            Task taskToMark = tasks.markTask(this.taskNumberToMark);
            ui.showMessage("Nicely done. I've marked this task as done:" + "\n" + taskToMark);
            storage.save(tasks);
        }
    }
}