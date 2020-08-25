import java.time.LocalDate;

public class ListCommand extends Command {
    private LocalDate date;

    public ListCommand(LocalDate date) {
        this.date = date;
    }

    public ListCommand() {
        this.date = null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayList(taskList, date);
    }
}
