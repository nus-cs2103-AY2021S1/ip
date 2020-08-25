import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddCommand that = (AddCommand) o;
        return type == that.type &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time);
    }

    @Override public int hashCode() {
        return Objects.hash(type, name, date, time);
    }
}
