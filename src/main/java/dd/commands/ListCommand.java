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

    /**
     * Class Constructor.
     *
     * @param command Command given.
     * @param item Empty string.
     */
    public ListCommand(String command, String item) {
        super(command, item);
    }

    private void list(ArrayList<Task> tasks) {
        int curr = 0;

        while (curr < tasks.size()) {
            ui.printTask(curr + 1, tasks.get(curr));
            curr += 1;
        }
    }

    public void checkDate() throws DukeException {
        boolean isValidInput = dth.isValidInput(item);

        if (isValidInput && item.length() == 10) {
            // valid
            ArrayList<Task> tasksOnDate = dth.filterDate(item, tasks.getTaskList());

            if (tasksOnDate.isEmpty()) {
                throw new DukeException().emptyCheckDate(item);
            } else {
                ui.startCheckDate(item);
                list(tasksOnDate);
            }
        } else {
            // not valid date
            throw new DukeException().invalidCheckDate();
        }
    }

    private void checkDesc() throws DukeException {
        ArrayList<Task> tasksWithDesc = new ArrayList<>();

        for (Task t : tasks.getTaskList()) {
            String taskDesc = t.getDescription();

            if (taskDesc.contains(item)) {
                tasksWithDesc.add(t);
            }
        }

        if (tasksWithDesc.isEmpty()) {
            throw new DukeException().emptyCheckDesc(item);
        } else {
            ui.startCheckDesc(item);
            list(tasksWithDesc);
        }
    }

    /**
     * Executes the appropriate method based on command.
     *
     * @param taskList Current TaskList to modify.
     * @param u Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @throws DukeException If invalid date string is given to a check command,
     * or if no tasks match given date or description in check and find commands.
     */
    @Override
    public void execute(TaskList taskList, Ui u, DataStorage ds) throws DukeException {
        tasks = taskList;
        ui = u;
        this.dth = new DateTimeHandler();

        if (command.equals("list")) {
            list(tasks.getTaskList());
        } else if (command.equals("check")) {
            checkDate();
        } else if (command.equals("find")) {
            checkDesc();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
