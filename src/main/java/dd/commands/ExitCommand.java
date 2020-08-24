package commands;

import storage.DataStorage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String command, String item) {
        super(command, item);
    }

    @Override
    public void execute(TaskList tasks, Ui u, DataStorage ds) {
        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
        ds.writeData(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
