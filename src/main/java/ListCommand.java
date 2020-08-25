import java.time.LocalDate;

/**
 * Represents a command to list tasks.
 */
public class ListCommand extends Command {
    private LocalDate date;

    public ListCommand() {}

    public ListCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Lists tasks that can be filtered by the date if initialized with it.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (date != null) {
            ui.showTasksOnDate(tasks.getListOfTasks(date), date);
        } else {
            ui.showAllTasks(tasks.getListOfTasks());
        }
    }
}
