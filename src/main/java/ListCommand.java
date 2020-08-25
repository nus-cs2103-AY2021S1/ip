import java.time.LocalDate;

public class ListCommand extends Command {
    private LocalDate date;

    public ListCommand() {}

    public ListCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (date != null) {
            ui.showTasksOnDate(tasks.getListOfTasks(date), date);
        } else {
            ui.showAllTasks(tasks.getListOfTasks());
        }
    }
}
