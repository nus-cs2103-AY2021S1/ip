package main.java;

class ExitCommand extends Command {
    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        if (storage.resetFile() && storage.saveTaskList(tasks)) {
            // TODO Ui: normal exit
        } else {
            // TODO Ui: error saving file
        }
    }
}
