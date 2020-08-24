package dd.commands;

import dd.datetimehandler.DateTimeHandler;
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
        System.out.println("Ok, To-do added:\n  " + tasks.getLastTask());

        ui.printTasksSize(tasks.getTaskSize());
    }

    public void addDeadline() {
        String[] temp = item.split(" /by "); // create array of [task desc, task date]

        if (temp.length == 2) {
            boolean validInput = dth.checkInput(temp[1]);

            if (validInput) {
                // valid
                String formattedDate = dth.categorizeInput(temp[1]);
                tasks.addTask(new Deadline(temp[0], formattedDate));
                System.out.println("Ok, Deadline added:\n  " + tasks.getLastTask());

                ui.printTasksSize(tasks.getTaskSize());
            }
            else {
                // not valid date
                System.out.println("I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                        + "Example: 31-12-2020 or 31-12-2020 2359");
            }
        }
        else {
            // no date input
            System.out.println("Due date not detected, try again!\n"
                    + "Please input deadline as 'deadline (title) /by (date)'\n"
                    + "Example: deadline return book /by 31-12-2020");
        }
    }

    public void addEvent() {
        String[] temp = item.split(" /at "); // create array of [task desc, task date]

        if (temp.length == 2) {
            boolean validInput = dth.checkInput(temp[1]);

            if (validInput) {
                // valid
                String formattedDate = dth.categorizeInput(temp[1]);
                tasks.addTask(new Event(temp[0], formattedDate));
                System.out.println("Ok, Event added:\n  " + tasks.getLastTask());

                ui.printTasksSize(tasks.getTaskSize());
            }
            else {
                // not valid date
                System.out.println("I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                        + "Example: 31-12-2020 or 31-12-2020 2359");
            }
        }
        else {
            // no date input
            System.out.println("Event date not detected, try again!\n"
                    + "Please input event as 'event (title) /at (date)'\n"
                    + "Example: event group meeting /at 31-12-2020");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui u, DataStorage ds) {
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
