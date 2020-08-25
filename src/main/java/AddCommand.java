import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command {
    private final TaskType type;
    private final String name;
    private final LocalDate date;
    private final LocalTime time;

    public AddCommand(TaskType type, String name, LocalDate date, LocalTime time) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BlankTaskException, IOException {
        tasks.add(type, name, date, time);
        ui.showAdd(tasks.size(), tasks.getLastTask());
        storage.updateMemory(tasks.getList());
    }
}
