package dd.commands;

import dd.datetimehandler.DateTimeHandler;
import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Task;
import dd.tasks.TaskList;
import dd.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    private DateTimeHandler dth;
    private TaskList tasks;
    private Ui ui;

    public ListCommand(String command, String item) {
        super(command, item);
    }

    public void list(ArrayList<Task> tasks) {
        int curr = 0;

        while (curr < tasks.size()) {
            ui.printTask(curr + 1, tasks.get(curr));
            curr += 1;
        }
    }

    public void checkDate() throws DukeException {
        boolean validInput = dth.checkInput(item);

        if (validInput && item.length() == 10) {
            // valid
            ArrayList<Task> tasksOnDate = dth.filterDate(item, tasks.getTaskList());

            if (tasksOnDate.isEmpty()) {
                throw new DukeException().emptyCheckDate(item);
            }
            else {
                ui.startCheckDate(item);
                list(tasksOnDate);
            }
        }
        else {
            // not valid date
            throw new DukeException().invalidCheckDate();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui u, DataStorage ds) throws DukeException {
        tasks = taskList;
        ui = u;
        this.dth = new DateTimeHandler();

        if (command.equals("list")) {
            list(tasks.getTaskList());
        }
        else if (command.equals("check")) {
            checkDate();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
