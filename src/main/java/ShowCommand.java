import java.time.LocalDate;

public class ShowCommand extends Command {
    private final LocalDate date;

    ShowCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMessage(tasks.showTasksOnDate(date));
    }
}
