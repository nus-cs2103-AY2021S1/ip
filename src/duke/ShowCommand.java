package duke;

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
}
