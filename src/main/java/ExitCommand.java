package main.java;

class ExitCommand extends Command {
    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        storage.resetFile();
        storage.saveTaskList(tasks);
        ui.printExit();
    }
}
