package dd.commands;

import java.util.ArrayList;

import dd.datetimehandler.DateTimeHandler;
import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Task;
import dd.tasks.TaskList;
import dd.ui.Ui;

/**
 * A list command of a certain type and user input goes through the current
 * task list in the system and displays the list of tasks requested.
 */
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

    private String list(ArrayList<Task> tasks) {
        return tasks.stream().reduce("",
                (string, currTask) -> string + printTaskInList(currTask, tasks),
                (prev, next) -> prev + next);
    }

    private String printTaskInList(Task task, ArrayList<Task> tasks) {
        return ui.printTask(tasks.indexOf(task) + 1, task) + "\n";
    }

    private String checkDate() throws DukeException {
        boolean isValidInput = dth.isValidInput(item);

        if (isValidInput && item.length() == 10) {
            // valid
            ArrayList<Task> tasksOnDate = dth.filterDate(item, tasks.getTaskList());

            if (tasksOnDate.isEmpty()) {
                throw new DukeException().emptyCheckDate(item);
            } else {
                return ui.startCheckDate(item) + "\n" + list(tasksOnDate);
            }
        } else {
            // not valid date
            throw new DukeException().invalidCheckDate();
        }
    }

    private String checkDesc() throws DukeException {
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
            return ui.startCheckDesc(item) + "\n" + list(tasksWithDesc);
        }
    }

    /**
     * Executes the appropriate method based on command.
     *
     * @param taskList Current TaskList to modify.
     * @param u Ui used to print statements.
     * @param ds DataStorage used to load or write data.
     * @return String to indicate resulting tasks from list.
     * @throws DukeException If invalid date string is given to a check command,
     * or if no tasks match given date or description in check and find commands.
     */
    @Override
    public String execute(TaskList taskList, Ui u, DataStorage ds) throws DukeException {
        tasks = taskList;
        ui = u;
        this.dth = new DateTimeHandler();
        String output = "";

        switch (command) {
        case "list":
            output = u.startList() + "\n" + list(tasks.getTaskList());
            break;
        case "check":
            output = checkDate();
            break;
        case "find":
            output = checkDesc();
            break;
        default:
            assert false : "invalid command: " + command;
        }

        return output;
    }
}
