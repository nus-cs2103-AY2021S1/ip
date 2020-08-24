package main.java.duke;

class DeleteTaskCommand extends Command {

    final int taskIndex;

    DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.deleteTaskAt(this.taskIndex);
        if (task != null) {
            ui.printDeleteTask(tasks, task);
        } else {
            ui.printInvalidTaskIndex();
        }
    }
}
