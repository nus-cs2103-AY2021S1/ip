package main.java.duke;

class InvalidCommand extends Command {

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printInvalidInput();
    }
}
