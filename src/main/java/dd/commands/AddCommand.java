package dd.commands;

import dd.datetimehandler.DateTimeHandler;
import dd.exception.DukeException;
import dd.storage.DataStorage;
import dd.tasks.Deadline;
import dd.tasks.Event;
import dd.tasks.TaskList;
import dd.tasks.Todo;
import dd.ui.Ui;

public class AddCommand extends Command {

    private DateTimeHandler dth;
    private TaskList tasks;
    private Ui ui;

    public AddCommand(String command, String item) {
        super(command, item);
    }

    public void addTodo() {
        tasks.addTask(new Todo(item));

        ui.startAddTodo(tasks.getLastTask());
        ui.printTasksSize(tasks.getTaskSize());
    }

    public void addDeadline() throws DukeException {
        String[] temp = item.split(" /by "); // create array of [task desc, task date]

        if (temp.length == 2) {
            boolean validInput = dth.checkInput(temp[1]);

            if (validInput) {
                // valid
                String formattedDate = dth.categorizeInput(temp[1]);
                tasks.addTask(new Deadline(temp[0], formattedDate));

                ui.startAddDeadline(tasks.getLastTask());
                ui.printTasksSize(tasks.getTaskSize());
            }
            else {
                // not valid date
                throw new DukeException().invalidDate();
            }
        }
        else {
            // no date input
            throw new DukeException().invalidDeadline();
        }
    }

    public void addEvent() throws DukeException {
        String[] temp = item.split(" /at "); // create array of [task desc, task date]

        if (temp.length == 2) {
            boolean validInput = dth.checkInput(temp[1]);

            if (validInput) {
                // valid
                String formattedDate = dth.categorizeInput(temp[1]);
                tasks.addTask(new Event(temp[0], formattedDate));

                ui.startAddEvent(tasks.getLastTask());
                ui.printTasksSize(tasks.getTaskSize());
            }
            else {
                // not valid date
                throw new DukeException().invalidDate();
            }
        }
        else {
            // no date input
            throw new DukeException().invalidEvent();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui u, DataStorage ds) throws DukeException {
        tasks = taskList;
        ui = u;
        this.dth = new DateTimeHandler();

        if (command.equals("todo")) {
            addTodo();
        }
        else if (command.equals("deadline")) {
            addDeadline();
        }
        else if (command.equals("event")) {
            addEvent();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
