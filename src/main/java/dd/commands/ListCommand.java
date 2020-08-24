package commands;

import datetimehandler.DateTimeHandler;
import storage.DataStorage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

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
            System.out.println((curr + 1) + ". " + tasks.get(curr));
            curr += 1;
        }
    }

    public void checkDate() {
        boolean validInput = dth.checkInput(item);

        if (validInput && item.length() == 10) {
            // valid
            ArrayList<Task> tasksOnDate = dth.filterDate(item, tasks.getTaskList());

            if (tasksOnDate.isEmpty()) {
                System.out.println("No tasks found on " + item + "!");
            }
            else {
                System.out.println("Here is your list of task(s) on " + item + ":");
                list(tasksOnDate);
            }
        }
        else {
            // not valid date
            System.out.println("I don't understand :( Please input date as DD-MM-YYYY\n"
                    + "Example: 31-12-2020");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui u, DataStorage ds) {
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
