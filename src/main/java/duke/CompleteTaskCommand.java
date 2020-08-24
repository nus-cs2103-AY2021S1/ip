package main.java.duke;

class CompleteTaskCommand extends Command {

    final int taskIndex;

    CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.completeTaskAt(this.taskIndex);
        if (task != null) {
            ui.printCompleteTask(task);
        } else {
            ui.printInvalidTaskIndex();
        }
    }
}
