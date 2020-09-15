package duke.commands;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.PeriodTask;
import duke.tasks.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {
    protected String type;
    protected String description;
    protected LocalDate[] date;

    /**
     * Constructs an AddCommand object.
     *
     * @param type The type of the task: todo, deadline, event, period-task.
     * @param description Title of the task.
     * @param date An LocalDate array contains related dates.
     */
    public AddCommand(String type, String description, LocalDate[] date) {
        this.type = type;
        this.description = description;
        this.date = date;
    }


    /**
     * Adds specific type of task into tasklist.
     *
     * @param tasks tasklist object.
     * @param ui Ui object.
     * @param storage Storage object.
     * @return a string of addTask message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        switch (type) {
        case "todo":
            Todo todo = new Todo(description);
            tasks.addTask(todo);
            output = ui.displayAddTaskMessage(todo);
            break;
        case "deadline":
            Deadline deadline = new Deadline(description, date[0]);
            tasks.addTask(deadline);
            output = ui.displayAddTaskMessage(deadline);
            break;
        case "event":
            Event event = new Event(description, date[0]);
            tasks.addTask(event);
            output = ui.displayAddTaskMessage(event);
            break;
        case "period-task":
            PeriodTask periodTask = new PeriodTask(description, date[0], date[1]);
            tasks.addTask(periodTask);
            output = ui.displayAddTaskMessage(periodTask);
            break;
        default:
            throw new AssertionError(type);
        }
        return output;
    }

    private boolean areTwoLocalDateArrayEqual(LocalDate[] arr1, Object obj) {
        if (!(obj instanceof LocalDate[])) {
            return false;
        }

        LocalDate[] arr2 = (LocalDate[]) obj;

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].isEqual(arr2[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand command = (AddCommand) obj;
            return (type.equals(command.type)) && (description.equals(command.description))
                    && (areTwoLocalDateArrayEqual(date, command.date));
        } else {
            return false;
        }
    }
}
