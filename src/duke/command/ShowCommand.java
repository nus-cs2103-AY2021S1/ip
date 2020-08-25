package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.CalendarException;

public class ShowCommand extends Command {

    public ShowCommand(String task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            processShow(this.task, taskList, ui, storage);
        } catch (CalendarException cal) {
            System.out.println(cal.getMessage());
        }
    }

    public void processShow(String date, TaskList taskList, Ui ui, Storage storage) throws CalendarException {
        taskList.showDate(date);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ShowCommand) {
            ShowCommand showCommand = (ShowCommand) other;
            return this.task.equals(showCommand.getTask());
        }
        return false;
    }
}
