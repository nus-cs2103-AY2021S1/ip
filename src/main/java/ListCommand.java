package main.java;

public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.showMessage("You have no tasks currently :)");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int n = 1; n <= tasks.getSize(); n++) {
                ui.showMessage(n + "." + tasks.getTask(n - 1));
            }
        }
    }
}
