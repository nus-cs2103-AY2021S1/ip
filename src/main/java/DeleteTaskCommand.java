package main.java;

class DeleteTaskCommand extends Command {

    final int taskIndex;

    DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        if (tasks.deleteTaskAt(this.taskIndex)) {
            // TODO Ui: task deleted successfully
        } else {
            // TODO Ui: out of range
        }
    }
}
