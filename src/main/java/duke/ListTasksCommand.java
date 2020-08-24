package main.java.duke;

class ListTasksCommand extends Command {

    @Override
    protected void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
