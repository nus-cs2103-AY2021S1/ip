package main.java;

class CompleteTaskCommand extends Command {

    final int taskIndex;

    CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        if (tasks.completeTaskAt(this.taskIndex)) {
            // TODO Ui: task completed successfully
        } else {
            // TODO Ui: out of range
        }
    }
}
